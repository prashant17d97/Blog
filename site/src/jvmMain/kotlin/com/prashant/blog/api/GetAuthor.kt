package com.prashant.blog.api

import com.prashant.blog.sealeds.ApiResponse
import com.prashant.blog.apiendpoints.ApiEndpointsConstant.GetAuthor
import com.prashant.blog.apiendpoints.ApiEndpointsConstant.json
import com.prashant.blog.model.Author
import com.prashant.blog.repo.MongoDB
import com.prashant.blog.repo.MongoResponse
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.HttpMethod
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString

@Api(GetAuthor)
suspend fun getAuthor(apiContext: ApiContext) {

    val response = if (apiContext.req.method == HttpMethod.POST) {
        try {
            val authorRequest =
                apiContext.req.body?.decodeToString()?.let { json.decodeFromString<Author>(it) }
            val author = authorRequest?.let {
                apiContext.data.getValue<MongoDB>().getAuthorContent(it)
            }

            when (author) {
                is MongoResponse.Error -> {
                    Pair(
                        404,
                        json.encodeToString(
                            ApiResponse.Error(
                                errorMessage = author.error,
                                statusCode = 404
                            )
                        )
                    )

                }

                is MongoResponse.Success -> {
                    Pair(
                        200,
                        json.encodeToString(
                            ApiResponse.Success(
                                response = author.data,
                                responseMessage = "Successful",
                                statusCode = 200
                            )
                        )
                    )
                }

                else -> {
                    Pair(
                        400,
                        json.encodeToString(
                            ApiResponse.Error(
                                errorMessage = "Some error occurred.",
                                statusCode = 400
                            )
                        )
                    )

                }
            }

        } catch (e: Exception) {
            apiContext.logger.error("Error call" + e.localizedMessage)
            Pair(
                400,
                json.encodeToString(
                    ApiResponse.Error(
                        errorMessage = "Some error occurred.",
                        statusCode = 400
                    )
                )
            )
        }
    } else {
        Pair(
            400,
            json.encodeToString(
                ApiResponse.Error(
                    errorMessage = "{Bad request method POST}",
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