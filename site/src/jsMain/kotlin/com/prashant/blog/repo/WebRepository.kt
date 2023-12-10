package com.prashant.blog.repo

import com.prashant.blog.model.ChildComment
import com.prashant.blog.model.PostComment
import com.prashant.blog.model.PostCommentRequest
import kotlinx.coroutines.flow.StateFlow

interface WebRepository {
    val comments: StateFlow<List<PostComment>>
    suspend fun addComment(postComment: PostComment)
    suspend fun addChildComment(parentIndex: Int, childComment: ChildComment, id: String)
    fun updateReplyChatWindow(selectedIndex: Int)
    suspend fun addComment(postCommentRequest: PostCommentRequest)
    suspend fun getComment(postId: String)
    suspend fun updateChildComment(postCommentRequest: PostCommentRequest)
}