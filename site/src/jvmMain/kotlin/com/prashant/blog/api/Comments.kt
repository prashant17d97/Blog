package com.prashant.blog.api

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.GetComment
import com.prashant.blog.sealeds.JVMApiResponse
import com.prashant.blog.sealeds.MongoResponse
import com.prashant.blog.utils.ApiUtils
import com.prashant.blog.utils.ApiUtils.badMethodRequest
import com.prashant.blog.utils.ApiUtils.mongoDB
import com.prashant.blog.utils.ApiUtils.setBody
import com.prashant.blog.utils.ApiUtils.tryCatchBlock
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.HttpMethod
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Api(GetComment)
suspend fun comments(apiContext: ApiContext) {
    val response = when (apiContext.req.method) {
        HttpMethod.GET -> handleGetCommentsRequest(apiContext)
        else -> badMethodRequest()
    }
    apiContext.setBody(response)

}

private suspend fun handleGetCommentsRequest(apiContext: ApiContext): Pair<Int, String> {
    return tryCatchBlock {
        val postId = apiContext.req.params[ApiEndpointConstants.Id]
        if (postId != null) {
            when (val dbResponse = apiContext.mongoDB().getComment(postId)) {
                is MongoResponse.Error -> ApiUtils.saveErrorResponse(
                    errorMessage = dbResponse.error ?: "Some error occurred"
                )

                is MongoResponse.Success -> Pair(
                    200,
                    Json.encodeToString(
                        JVMApiResponse.Success(
                            response = dbResponse.data,
                            responseMessage = "Fetched comments successfully",
                            statusCode = 200
                        )
                    )
                )
            }
        } else {
            badMethodRequest(400, errorMessage = "Bad request post id is not allowed to be empty")
        }

    }
}
