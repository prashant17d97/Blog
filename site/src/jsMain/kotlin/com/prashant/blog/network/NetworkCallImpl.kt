package com.prashant.blog.network

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.prashant.blog.apiendpoints.ApiEndpointsConstant.GetAuthor
import com.prashant.blog.apiendpoints.ApiEndpointsConstant.GetAuthorFromId
import com.prashant.blog.apiendpoints.ApiEndpointsConstant.GetUsers
import com.prashant.blog.apiendpoints.ApiEndpointsConstant.json
import com.prashant.blog.model.ApiResponseValue
import com.prashant.blog.model.Author
import com.prashant.blog.utils.commonfunctions.CommonFunctions.parseData
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.encodeToString


internal class NetworkCallImpl : NetworkCall {
    override suspend fun getAuthor(author: Author): String {
        return try {
            val result = window.api.tryPost(
                apiPath = GetAuthor,
                body = json.encodeToString(value = author).encodeToByteArray()
            )
            result?.decodeToString() ?: "Some error occurred"

        } catch (ex: Exception) {
            console.info("Call $ex")
            "Some exception occurred: ${ex.message}"
        }
    }

    override suspend fun getAuthorById(authorId: String): ApiResponseValue<Author>? {
        return try {
            window.api.tryGet(
                apiPath = GetAuthorFromId(authorId),
            )?.decodeToString().parseData()

        } catch (ex: Exception) {
            console.info("Call $ex")
            null
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
}

@Composable
fun rememberNetworkCall(): NetworkCall {
    return remember { NetworkCallImpl() }
}