package com.prashant.blog.api

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Category
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Id
import com.prashant.blog.model.CategoryModel
import com.prashant.blog.sealeds.JVMApiResponse
import com.prashant.blog.sealeds.MongoResponse
import com.prashant.blog.utils.ApiUtils.badMethodRequest
import com.prashant.blog.utils.ApiUtils.decodeFromString
import com.prashant.blog.utils.ApiUtils.handleMongoEmptyResponse
import com.prashant.blog.utils.ApiUtils.mongoDB
import com.prashant.blog.utils.ApiUtils.saveErrorResponse
import com.prashant.blog.utils.ApiUtils.setBody
import com.prashant.blog.utils.ApiUtils.tryCatchBlock
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.HttpMethod
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.codecs.ObjectIdGenerator

@Api(Category)
suspend fun category(apiContext: ApiContext) {
    val response = when (apiContext.req.method) {
        HttpMethod.GET -> apiContext.fetchCategory()
        HttpMethod.POST -> apiContext.createCategory()
        else -> badMethodRequest()
    }
    apiContext.setBody(response)
}

private suspend fun ApiContext.createCategory(): Pair<Int, String> {
    return tryCatchBlock {
        val categoryPostRequest = this.req.body?.decodeToString()

        if (categoryPostRequest == null) {
            badMethodRequest(
                errorMessage = "Empty post body is not allowed! Bad request"
            )
        } else {
            val newCategory = categoryPostRequest.decodeFromString<CategoryModel>()
                .copy(_id = ObjectIdGenerator().generate().toString())

            if (newCategory.validateMembers.first) {
                handleMongoEmptyResponse(
                    mongoResponse = this.mongoDB().createCategory(newCategory),
                    successMessage = "Category created Successfully"
                )
            } else {
                badMethodRequest(errorMessage = "${newCategory.validateMembers.second}. Bad request")
            }
        }
    }
}

private suspend fun ApiContext.fetchCategory(): Pair<Int, String> {
    return tryCatchBlock {
        val categoryGetRequest = this.req.params[Id]

        if (categoryGetRequest.isNullOrEmpty()) {
            when (val mongoResponse = mongoDB().retrieveCategories()) {
                is MongoResponse.Error -> saveErrorResponse(
                    errorMessage = mongoResponse.error ?: "Some error occurred"
                )

                is MongoResponse.Success -> Pair(
                    200, Json.encodeToString(
                        JVMApiResponse.Success(
                            response = mongoResponse.data,
                            responseMessage = "Categories fetched successfully",
                            statusCode = 200
                        )
                    )
                )
            }

        } else {
            when (val mongoResponse = mongoDB().retrieveCategoryById(categoryGetRequest)) {
                is MongoResponse.Error -> saveErrorResponse(
                    errorMessage = mongoResponse.error ?: "Some error occurred"
                )

                is MongoResponse.Success -> Pair(
                    200, Json.encodeToString(
                        JVMApiResponse.Success(
                            response = mongoResponse.data,
                            responseMessage = "Category fetched successfully",
                            statusCode = 200
                        )
                    )
                )
            }
        }
    }
}
