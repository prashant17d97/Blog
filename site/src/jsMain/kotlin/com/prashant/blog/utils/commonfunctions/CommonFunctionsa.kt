package com.prashant.blog.utils.commonfunctions

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.json
import com.prashant.blog.model.ApiCallResponse
import com.prashant.blog.model.ApiErrorCallResponse
import com.prashant.blog.model.JSApiResponse
import com.prashant.blog.utils.constants.ResourceConstants
import kotlinx.browser.window
import org.w3c.dom.Element

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
        onSuccess: suspend (ApiCallResponse<Generic>) -> Unit,
        onFailure: (ApiErrorCallResponse) -> Unit
    ) {
        when (this) {
            is JSApiResponse.Error -> this.errorCallResponse?.let { onFailure(it) }
            is JSApiResponse.Success -> this.data?.let { onSuccess(it) }
                ?: onFailure(ApiErrorCallResponse())
        }
    }

    suspend fun <Generic> tryCatchBlock(
        errorMessage: String? = null,
        block: suspend () -> JSApiResponse<Generic>
    ): JSApiResponse<Generic> = try {
        block()
    } catch (ex: Exception) {
        JSApiResponse.Error(ApiErrorCallResponse())
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

    fun Element.gainFocus() {
        val dynElement: dynamic = this
        // https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/focus
        dynElement.focus(focusVisible = false)
    }
}