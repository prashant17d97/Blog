package com

import com.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResponse {
    @Serializable
    @SerialName("idle")
    data object Idle : ApiResponse()
    @Serializable
    @SerialName("success")
    data class Success(val data: List<User>?) : ApiResponse()
    @Serializable
    @SerialName("error")
    data class Error(val error: String) : ApiResponse()
}
