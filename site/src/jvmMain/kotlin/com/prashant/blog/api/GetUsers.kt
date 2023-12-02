package com.prashant.blog.api

import com.prashant.blog.sealeds.ApiResponse
import com.prashant.blog.apiendpoints.ApiEndpointsConstant.GetUsers
import com.prashant.blog.apiendpoints.ApiEndpointsConstant.json
import com.prashant.blog.model.User
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString


val user = listOf(
    User(
        _id = 1,
        name = "Prashant Singh"
    ),
    User(
        _id = 2,
        name = "Android Developer"
    )
)

@Api(GetUsers)
suspend fun getUsers(apiContext: ApiContext) {
    val response = try {
        Pair(
            200, json.encodeToString(
                ApiResponse.Success(
                    response = user,
                    responseMessage = "Successful",
                    statusCode = 200
                )

            )
        )
    } catch (exception: Exception) {
        Pair(
            400, json.encodeToString(
                ApiResponse.Error(
                    errorMessage = exception.message.toString(),
                    statusCode = 400
                )
            )
        )
    }
    apiContext.res.apply {
        status = response.first
        setBodyText(response.second)
    }
}