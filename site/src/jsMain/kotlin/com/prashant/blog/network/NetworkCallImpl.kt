package com.prashant.blog.network

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Author
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Category
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.CreatePost
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.GetAuthorFromId
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.GetCategoryFromId
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.GetPostFromId
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.json
import com.prashant.blog.model.AuthorModel
import com.prashant.blog.model.CategoryModel
import com.prashant.blog.model.JSApiResponse
import com.prashant.blog.model.PostModel
import com.prashant.blog.utils.commonfunctions.CommonFunctions.parseData
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.encodeToString


internal class NetworkCallImpl : NetworkCall {
    override suspend fun createNewAuthor(author: AuthorModel): JSApiResponse<String> {
        return try {
            val result = window.api.tryPost(
                apiPath = Author,
                body = json.encodeToString(value = author).encodeToByteArray()
            )?.decodeToString()
            if (result != null) {
                JSApiResponse.Success(result.parseData())
            } else {
                JSApiResponse.Error(result.parseData<String>() ?: "Some error occurred")
            }

        } catch (ex: Exception) {
            console.info("Call ${ex.cause}")
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

    override suspend fun createCategory(category: CategoryModel): JSApiResponse<Boolean> {
        return try {
            val result = window.api.tryPost(
                apiPath = Category,
                body = json.encodeToString(value = category).encodeToByteArray()
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

    override suspend fun retrieveCategoryById(categoryId: String): JSApiResponse<CategoryModel> {
        return try {
            val result = window.api.tryGet(
                apiPath = GetCategoryFromId(categoryId),
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

    override suspend fun retrieveCategories(): JSApiResponse<List<CategoryModel>> {
        return try {
            val result = window.api.tryGet(
                apiPath = Category,
            )?.decodeToString()
            if (result != null) {
                JSApiResponse.Success(result.parseData())
            } else {
                JSApiResponse.Error(result.parseData<String>() ?: "Some error occurred")
            }

        } catch (ex: Exception) {
            console.info("Call $ex")
            JSApiResponse.Error(ex.message ?: "Some error occurred")
        }    }
}

@Composable
fun rememberNetworkCall(): NetworkCall {
    return remember { NetworkCallImpl() }
}