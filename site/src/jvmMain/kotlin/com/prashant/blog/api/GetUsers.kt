package com.prashant.blog.api

import com.ApiResponse
import com.model.User
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


val user = listOf(
    User(
        id = 1,
        name = "Prashant Singh"
    ),
    User(
        id = 2,
        name = "Android Developer"
    )
)

@Api("getusers")
suspend fun getUsers(apiContext: ApiContext) {
    try {
        apiContext.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.Success(user)
            )
        )
    } catch (exception: Exception) {
        apiContext.res.setBodyText(
            Json.encodeToString<ApiResponse>(
                ApiResponse.Error(error = exception.message.toString())
            )
        )
    }
}