package com.prashant.blog.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SocialLink(
    @SerialName("platform")
    val platform: String,
    @SerialName("platformLink")
    val platformLink: String
)