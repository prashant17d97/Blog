package com.prashant.blog.network

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.CreatePost
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.GetAuthor
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.GetAuthorFromId
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.GetPostFromId
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.GetUsers
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.json
import com.prashant.blog.model.AuthorModel
import com.prashant.blog.model.JSApiResponse
import com.prashant.blog.model.PostModel
import com.prashant.blog.utils.commonfunctions.CommonFunctions.parseData
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.serialization.encodeToString


internal class NetworkCallImpl : NetworkCall {
    override suspend fun getAuthor(author: AuthorModel): JSApiResponse<AuthorModel> {
        return try {
            val result = window.api.tryPost(
                apiPath = GetAuthor,
                body = json.encodeToString(value = author).encodeToByteArray()
            )?.decodeToString()
            if (result != null) {
                JSApiResponse.Success(result.parseData())
            } else {
                JSApiResponse.Error(result.parseData<String>() ?: "Some error occurred")
            }

        } catch (ex: Exception) {
            console.info("Call $ex")
            JSApiResponse.Error(ex.message ?: "Some error occurred")
        }
    }

    override suspend fun getAuthorById(authorId: String): JSApiResponse<AuthorModel>{
        return try {
            val result = window.api.tryGet(
                apiPath = GetAuthorFromId(authorId),
            )?.decodeToString()
            if (result != null) {
                JSApiResponse.Success(result.parseData())
            } else {
                JSApiResponse.Error(result.parseData<String>() ?: "Some error occurred")
            }

        } catch (ex: Exception) {
            console.info("Call $ex")
            JSApiResponse.Error(ex.message ?: "Some error occurred")
        }
    }

    override suspend fun createPost(postModel: PostModel): JSApiResponse<String> {
        return try {
            val result = window.api.tryPost(
                apiPath = CreatePost,
                body = json.encodeToString(value = postModel).encodeToByteArray()
            )?.decodeToString()
            delay(5000)
            if (result != null) {
                JSApiResponse.Success(result.parseData())
            } else {
                JSApiResponse.Error(result.parseData<String>() ?: "Some error occurred")
            }


        } catch (ex: Exception) {
            console.info("Call $ex")
            JSApiResponse.Error(ex.message ?: "Some error occurred")
        }
    }

    override suspend fun getUser(): String? {
        return try {
            val result = window.api.tryGet(
                apiPath = GetUsers,
            )
            result?.decodeToString()
        } catch (ex: Exception) {
            ex.message
        }
    }

    override suspend fun retrievePost(postId: String): JSApiResponse<PostModel> {
        return try {
            val result = window.api.tryGet(
                apiPath = GetPostFromId(postId),
            )?.decodeToString()
            if (result != null) {
                JSApiResponse.Success(result.parseData())
            } else {
                JSApiResponse.Error(result.parseData<String>() ?: "Some error occurred")
            }

        } catch (ex: Exception) {
            console.info("Call $ex")
            JSApiResponse.Error(ex.message ?: "Some error occurred")
        }
    }
}

@Composable
fun rememberNetworkCall(): NetworkCall {
    return remember { NetworkCallImpl() }
}