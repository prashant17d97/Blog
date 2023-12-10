package com.prashant.blog.api

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Id
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.POST
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Popular
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Type
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
    val postId = api.req.params[Id]
    val postType = api.req.params[Type]
    return tryCatchBlock {
        if (postType == Popular) {
            when (val dbResponse = api.mongoDB().fetchAllPost()) {
                is MongoResponse.Error -> saveErrorResponse(
                    errorMessage = dbResponse.error ?: "Some error occurred"
                )

                is MongoResponse.Success -> Pair(
                    200,
                    Json.encodeToString(
                        JVMApiResponse.Success(
                            response = dbResponse.data?.filter { it.views > 500 },
                            responseMessage = "Popular Posts fetched successfully",
                            statusCode = 200
                        )
                    )
                )
            }
        } else if (postId.isNullOrEmpty()) {
            when (val dbResponse = api.mongoDB().fetchAllPost()) {
                is MongoResponse.Error -> saveErrorResponse(
                    errorMessage = dbResponse.error ?: "Some error occurred"
                )

                is MongoResponse.Success -> Pair(
                    200,
                    Json.encodeToString(
                        JVMApiResponse.Success(
                            response = dbResponse.data,
                            responseMessage = "Posts fetched successfully",
                            statusCode = 200
                        )
                    )
                )
            }
        } else {
            when (val dbResponse = api.mongoDB().retrievePost(postId)) {
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
