package com.prashant.blog.api

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.CreatePost
import com.prashant.blog.model.PostModel
import com.prashant.blog.utils.ApiUtils.badMethodRequest
import com.prashant.blog.utils.ApiUtils.decodeFromString
import com.prashant.blog.utils.ApiUtils.handleMongoEmptyResponse
import com.prashant.blog.utils.ApiUtils.mongoDB
import com.prashant.blog.utils.ApiUtils.setBody
import com.prashant.blog.utils.ApiUtils.tryCatchBlock
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.HttpMethod
import org.bson.codecs.ObjectIdGenerator

/**
 * API handler for creating a new post. This function is invoked when a POST request is received.
 *
 * @param api The [ApiContext] containing the request and response details.
 */
@Api(CreatePost)
suspend fun createPost(api: ApiContext) {
    val response = when (api.req.method) {
        HttpMethod.POST -> handlePostRequest(api)
        else -> badMethodRequest()
    }
    api.setBody(response)
}

/**
 * Handles the processing of a POST request to create a new post.
 *
 * @param api The [ApiContext] containing the request and response details.
 * @return A Pair containing the HTTP status code and the corresponding response message.
 */
private suspend fun handlePostRequest(api: ApiContext): Pair<Int, String> {
    return tryCatchBlock {
        val addPostRequest = api.req.body?.decodeToString()

        if (addPostRequest == null) {
            badMethodRequest(
                errorMessage = "Empty post body is not allowed! Bad request"
            )
        } else {
            val newPost = addPostRequest.decodeFromString<PostModel>()
                .copy(_id = ObjectIdGenerator().generate().toString())

            if (newPost.validateMembers.first) {
                handleMongoEmptyResponse(
                    mongoResponse = api.mongoDB().addPost(newPost),
                    successMessage = "Post created Successfully"
                )
            } else {
                badMethodRequest(errorMessage = "${newPost.validateMembers.second}. Bad request")
            }
        }
    }
}
