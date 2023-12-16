package com.prashant.blog.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class JSApiResponse<Generic>(
    val data: ApiCallResponse<Generic>? = null,
    val errorCallResponse: ApiErrorCallResponse? = null,
) {

    @SerialName("success")
    class Success<Generic>(apiCallResponse: ApiCallResponse<Generic>?) : JSApiResponse<Generic>(
        data = apiCallResponse,
    )

    @SerialName("error")
    class Error<Generic>(errorCallResponse: ApiErrorCallResponse?) : JSApiResponse<Generic>(
        errorCallResponse = errorCallResponse,
    )
}