package com.prashant.blog.sealeds

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class MongoResponse<out Generic> {
    @Serializable
    @SerialName("success")
    data class Success<Generic>(val data: Generic?) : MongoResponse<Generic>()

    @Serializable
    @SerialName("error")
    data class Error(val error: String?) : MongoResponse<Nothing>()
}