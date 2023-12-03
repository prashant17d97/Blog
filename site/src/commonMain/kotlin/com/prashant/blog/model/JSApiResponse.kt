package com.prashant.blog.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class JSApiResponse<out Generic> {
    @Serializable
    @SerialName("idle")
    data object Idle : JSApiResponse<Nothing>()

    @Serializable
    @SerialName("success")
    data class Success<Generic>(val data: ApiCallResponse<Generic>?) : JSApiResponse<Generic>()

    @Serializable
    @SerialName("error")
    data class Error(val error: String) : JSApiResponse<Nothing>()
}