package com.prashant.blog.sealeds

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResponse<out Generic> {
    @Serializable
    @SerialName("idle")
    data object Idle : ApiResponse<Nothing>()

    @Serializable
    @SerialName("success")
    data class Success<Generic>(
        val response: Generic?,
        val responseMessage: String?,
        val statusCode: Int?,
    ) : ApiResponse<Generic>()

    @Serializable
    @SerialName("error")
    data class Error(
        val errorMessage: String?,
        val statusCode: Int?
    ) : ApiResponse<Nothing>()
}