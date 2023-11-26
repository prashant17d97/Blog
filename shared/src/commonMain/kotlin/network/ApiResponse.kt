package network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import network.model.User

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
