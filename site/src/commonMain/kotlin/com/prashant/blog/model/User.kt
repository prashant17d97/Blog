package com.prashant.blog.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class User(
    @SerialName("_id")
    val _id: Int,
    val name: String
)