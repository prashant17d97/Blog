package com.prashant.blog.api

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.POST
import com.prashant.blog.sealeds.JVMApiResponse
import com.prashant.blog.sealeds.MongoResponse
import com.prashant.blog.utils.ApiUtils.badMethodRequest
import com.prashant.blog.utils.ApiUtils.mongoDB
import com.prashant.blog.utils.ApiUtils.saveErrorResponse
import com.prashant.blog.utils.ApiUtils.setBody
import com.prashant.blog.utils.ApiUtils.tryCatchBlock
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.HttpMethod
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * API handler for fetching post information. Supports only the GET method.
 *
 * @param api The [ApiContext] containing the request and response details.
 */
@Api(POST)
suspend fun post(api: ApiContext) {
    val response = when (api.req.method) {
        HttpMethod.GET -> handlePostRequest(api)
        else -> badMethodRequest()
    }
    api.setBody(response)
}

/**
 * Handles the processing of a GET request to fetch post information by ID.
 *
 * @param api The [ApiContext] containing the request and response details.
 * @return A Pair containing the HTTP status code and the corresponding response message.
 */
private suspend fun handlePostRequest(api: ApiContext): Pair<Int, String> {
    val postId = api.req.params["_id"]
    return tryCatchBlock {
        if (postId != null && postId.isEmpty()) {
            badMethodRequest(errorMessage = "Post ID is required! Bad request")
        } else {
            when (val dbResponse = api.mongoDB().retrievePost(postId!!)) {
                is MongoResponse.Error -> saveErrorResponse(
                    errorMessage = dbResponse.error ?: "Some error occurred"
                )

                is MongoResponse.Success -> Pair(
                    200,
                    Json.encodeToString(
                        JVMApiResponse.Success(
                            response = dbResponse.data,
                            responseMessage = "Post fetched successfully",
                            statusCode = 200
                        )
                    )
                )
            }
        }
    }
}
