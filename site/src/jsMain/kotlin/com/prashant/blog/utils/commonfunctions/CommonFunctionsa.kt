package com.prashant.blog.utils.commonfunctions

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.json
import com.prashant.blog.model.ApiCallResponse
import com.prashant.blog.model.JSApiResponse
import com.prashant.blog.utils.constants.ResourceConstants
import kotlinx.browser.window

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

    suspend fun <Generic> JSApiResponse<Generic>.handleResponse(
        onLoading: (Boolean) -> Unit,
        onSuccess: suspend (ApiCallResponse<Generic>) -> Unit,
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

    fun String.getSocialIcon(): String {
        val socialMediaIcons = ResourceConstants.FooterSocialIcons.socialMediaIcons

        for (icon in socialMediaIcons) {
            if (icon.contains(this)) {
                return icon
            }
        }
        // If no matching icon is found, you might want to return a default value.
        return ResourceConstants.FooterSocialIcons.SiteIcon
    }

    val String.isEmailValid: Boolean
        get() {
            val regex = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)"
            return regex.toRegex().matches(this)
        }

    fun timeOut(timeOut: Int, onFinished: () -> Unit) {
        window.setTimeout({
            onFinished.invoke()
        }, timeOut)
    }
}