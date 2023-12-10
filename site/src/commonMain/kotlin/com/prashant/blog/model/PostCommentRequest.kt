package com.prashant.blog.model

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostCommentRequest(
    @SerialName(ApiEndpointConstants.Id)
    val _id: String = "",
    val userName: String,
    val userEmail: String,
    val commentDate: String,
    val comment: String,
    val postId: String = "",
    val childComments: List<ChildComment> = emptyList()
)
