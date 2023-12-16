package com.prashant.blog.model

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Id
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class User(
    @SerialName(Id)
    val _id: Int,
    val name: String
)