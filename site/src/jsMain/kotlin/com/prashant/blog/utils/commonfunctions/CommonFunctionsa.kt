package com.prashant.blog.utils.commonfunctions

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.json
import com.prashant.blog.model.ApiCallResponse
import com.prashant.blog.model.JSApiResponse

object CommonFunctions {

    fun <E> Set<E>.findKey(key: String): E? {
        return this.find {
            it == key
        }
    }

    fun String.capitalize() =
        this.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

    inline fun <reified T> String?.parseData(): T? {
        return this?.let { json.decodeFromString(it) }
    }

    fun <Generic> JSApiResponse<Generic>.handleResponse(
        onLoading: (Boolean) -> Unit,
        onSuccess: (ApiCallResponse<Generic>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        when (this) {
            is JSApiResponse.Error -> onFailure(this.error)
            JSApiResponse.Idle -> onLoading(true)
            is JSApiResponse.Success -> this.data?.let { onSuccess(it) }
                ?: onFailure("Data is empty")
        }
    }

    suspend fun <Generic> tryCatchBlock(
        errorMessage: String? = null,
        block: suspend () -> JSApiResponse<Generic>
    ): JSApiResponse<Generic> = try {
        block()
    } catch (ex: Exception) {
        JSApiResponse.Error(errorMessage ?: ex.message ?: "Something went wrong")
    }

}