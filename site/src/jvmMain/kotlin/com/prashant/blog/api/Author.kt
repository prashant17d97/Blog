package com.prashant.blog.api

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Author
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.AuthorPost
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.DateParam
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Id
import com.prashant.blog.model.AuthorModel
import com.prashant.blog.sealeds.JVMApiResponse
import com.prashant.blog.sealeds.MongoResponse
import com.prashant.blog.utils.ApiUtils
import com.prashant.blog.utils.ApiUtils.badMethodRequest
import com.prashant.blog.utils.ApiUtils.decodeFromString
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

/**
 * API handler for fetching author information. Supports both POST and GET methods.
 *
 * @param apiContext The [ApiContext] containing the request and response details.
 */
@Api(Author)
suspend fun getAuthor(apiContext: ApiContext) {

    val response = when (apiContext.req.method) {
        HttpMethod.POST -> apiContext.handleAuthorPostRequest()
        HttpMethod.GET -> apiContext.handleAuthorGetRequestById()
        else -> badMethodRequest()
    }
    apiContext.setBody(response)
}

/**
 * Handles the processing of a GET request to fetch author information by ID.
 *
 * @return A Pair containing the HTTP status code and the corresponding response message.
 */
private suspend fun ApiContext.handleAuthorGetRequestById(): Pair<Int, String> {
    return tryCatchBlock(errorMessage = "Error in fetching Author") {
        val authorId = this.req.params[Id]
        val authorsPosts = this.req.params[AuthorPost]
        val date = this.req.params[DateParam]
        val isRequestsForPost = authorsPosts == "true"

        if (authorId == null) {
            badMethodRequest(errorMessage = "Author ID is required! Bad request")
        } else {
            if (!isRequestsForPost) {
                when (val response = this.mongoDB().getAuthorContentById(authorId)) {
                    is MongoResponse.Error -> saveErrorResponse(
                        errorMessage = response.error ?: "Some error occurred."
                    )

                    is MongoResponse.Success -> Pair(
                        200,
                        Json.encodeToString(
                            JVMApiResponse.Success(
                                response = response.data,
                                responseMessage = "Author details fetched successfully",
                                statusCode = 200
                            )
                        )
                    )
                }
            } else {
                when (val response = this.mongoDB().findAuthorsPosts(authorId, date ?: "")) {
                    is MongoResponse.Error -> saveErrorResponse(
                        errorMessage = response.error ?: "Some error occurred."
                    )

                    is MongoResponse.Success -> Pair(
                        200,
                        Json.encodeToString(
                            JVMApiResponse.Success(
                                response = response.data,
                                responseMessage = "Posts fetched successfully",
                                statusCode = 200
                            )
                        )
                    )
                }
            }
        }
    }
}

/**
 * Handles the processing of a POST request to fetch author information.
 *
 * @return A Pair containing the HTTP status code and the corresponding response message.
 */
private suspend fun ApiContext.handleAuthorPostRequest(): Pair<Int, String> {
    return tryCatchBlock {
        val addNewAuthorRequest = this.req.body?.decodeToString()

        if (addNewAuthorRequest == null) {
            badMethodRequest(
                errorMessage = "Empty author body is not allowed! Bad request"
            )
        } else {
            val newAuthor = addNewAuthorRequest.decodeFromString<AuthorModel>()
                .copy(_id = ObjectIdGenerator().generate().toString())

            if (newAuthor.name.isNotEmpty()) {
                ApiUtils.handleMongoEmptyResponse(
                    mongoResponse = this.mongoDB().addNewAuthor(newAuthor),
                    successMessage = "New Author created Successfully"
                )
            } else {
                badMethodRequest(errorMessage = "Name is not allowed be empty.")
            }
        }
    }
}
