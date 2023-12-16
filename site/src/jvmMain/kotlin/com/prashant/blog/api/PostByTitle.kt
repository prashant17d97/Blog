package com.prashant.blog.api

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.PostSearch
import com.prashant.blog.sealeds.JVMApiResponse
import com.prashant.blog.sealeds.MongoResponse
import com.prashant.blog.utils.ApiUtils
import com.prashant.blog.utils.ApiUtils.badMethodRequest
import com.prashant.blog.utils.ApiUtils.mongoDB
import com.prashant.blog.utils.ApiUtils.setBody
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.HttpMethod
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Api(PostSearch)
suspend fun postByTitle(apiContext: ApiContext) {
    val response = when (apiContext.req.method) {
        HttpMethod.GET -> handleGetPostByTitleRequest(apiContext)
        else -> badMethodRequest()
    }
    apiContext.setBody(response)
}

private suspend fun handleGetPostByTitleRequest(apiContext: ApiContext): Pair<Int, String> {
    val title = apiContext.req.params[ApiEndpointConstants.Title]

    return ApiUtils.tryCatchBlock {
        if (title?.isNotEmpty() == true) {
            when (val dbResponse = apiContext.mongoDB().findPostsByTitle(title)) {
                is MongoResponse.Error -> ApiUtils.saveErrorResponse(
                    errorMessage = dbResponse.error ?: "Some error occurred"
                )

                is MongoResponse.Success -> Pair(200, Json.encodeToString(JVMApiResponse.Success(
                    response = dbResponse.data?.ifEmpty { emptyList() },
                    responseMessage = "$title Posts found successfully".takeIf { dbResponse.data?.isNotEmpty() == true }
                        ?: "No data found",
                    statusCode = 200)))
            }
        } else {
            badMethodRequest()
        }
    }
}