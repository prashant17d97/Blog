package com.prashant.blog.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCallResponse<Generic>(
    @SerialName("response")
    val data: Generic,

    @SerialName("responseMessage")
    val responseMessage: String,

    @SerialName("statusCode")
    val statusCode: Int
)