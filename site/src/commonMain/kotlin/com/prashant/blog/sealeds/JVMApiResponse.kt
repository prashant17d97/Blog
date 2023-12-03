package com.prashant.blog.sealeds

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class JVMApiResponse<out Generic> {
    @Serializable
    @SerialName("idle")
    data object Idle : JVMApiResponse<Nothing>()

    @Serializable
    @SerialName("success")
    data class Success<Generic>(
        val response: Generic?,
        val responseMessage: String?,
        val statusCode: Int?,
    ) : JVMApiResponse<Generic>()
    @Serializable
    @SerialName("emptyBodySuccess")
    data class EmptyBodySuccess(
        val responseMessage: String?,
        val statusCode: Int?,
    ) : JVMApiResponse<String>()


    @Serializable
    @SerialName("error")
    data class Error(
        val errorMessage: String?,
        val statusCode: Int?
    ) : JVMApiResponse<Nothing>()
}