package com.prashant.blog.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseValue<Generic>(
    @SerialName("response")
    val userData: Generic,

    @SerialName("responseMessage")
    val responseMessage: String,

    @SerialName("statusCode")
    val statusCode: Int
)