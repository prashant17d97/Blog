package com.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName(Id)
    val id: Int,
    val name: String,
)
