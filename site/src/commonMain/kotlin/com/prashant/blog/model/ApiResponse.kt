package com.prashant.blog.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResponse<out Generic> {
    @Serializable
    @SerialName("idle")
    data object Idle : ApiResponse<Nothing>()

    @Serializable
    @SerialName("success")
    data class Success<Generic>(val data: Generic?) : ApiResponse<Generic>()

    @Serializable
    @SerialName("error")
    data class Error(val error: String) : ApiResponse<Nothing>()
}