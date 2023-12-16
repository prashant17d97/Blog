package com.prashant.blog.api

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.UpdateComment
import com.prashant.blog.model.PostCommentRequest
import com.prashant.blog.utils.ApiUtils
import com.prashant.blog.utils.ApiUtils.decodeFromString
import com.prashant.blog.utils.ApiUtils.mongoDB
import com.prashant.blog.utils.ApiUtils.setBody
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.HttpMethod

@Api(UpdateComment)
suspend fun updateComment(apiContext: ApiContext) {
    val response = when (apiContext.req.method) {
        HttpMethod.POST -> handleUpdateCommentRequest(apiContext)
        else -> ApiUtils.badMethodRequest()
    }
    apiContext.setBody(response)

}


private suspend fun handleUpdateCommentRequest(api: ApiContext): Pair<Int, String> {
    return ApiUtils.tryCatchBlock {
        val addPostRequest = api.req.body?.decodeToString()

        if (addPostRequest == null) {
            ApiUtils.badMethodRequest(
                errorMessage = "Empty post body is not allowed! Bad request"
            )
        } else {
            val newPost = addPostRequest.decodeFromString<PostCommentRequest>()
            ApiUtils.handleMongoEmptyResponse(
                mongoResponse = api.mongoDB().updateChildComment(newPost),
                successMessage = "Post created Successfully"
            )
        }
    }

}