package com.prashant.blog.components.model

data class TopComment(
    val id: Int,
    val userName: String,
    val userImage: String,
    val userEmail: String,
    val commentDate: String,
    val comment: String,
    val isReplyingForThisThread: Boolean = false,
    val childComments: ArrayList<ChildComment>
)

data class ChildComment(
    val userName: String,
    val userImage: String,
    val userEmail: String,
    val commentDate: String,
    val comment: String,
)
