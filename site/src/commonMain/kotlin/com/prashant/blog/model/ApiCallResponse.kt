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

@Serializable
data class ApiErrorCallResponse(
    @SerialName("errorMessage")
    val errorMessage: String = "Some error occurred",

    @SerialName("statusCode")
    val statusCode: Int = 400
)