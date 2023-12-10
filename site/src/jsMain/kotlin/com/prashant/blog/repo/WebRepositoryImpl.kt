package com.prashant.blog.repo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.prashant.blog.model.ChildComment
import com.prashant.blog.model.PostComment
import com.prashant.blog.model.PostCommentRequest
import com.prashant.blog.network.NetworkCallImpl
import com.prashant.blog.utils.commonfunctions.CommonFunctions.handleResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WebRepositoryImpl : WebRepository {

    private val networkCall = NetworkCallImpl()

    private var _comments: MutableStateFlow<List<PostComment>> = MutableStateFlow(emptyList())
    override val comments: StateFlow<List<PostComment>>
        get() = _comments

    override suspend fun addComment(postComment: PostComment) {
        console.info("TopComments: $postComment")
        _comments.tryEmit(_comments.value + postComment)
        addComment(
            postCommentRequest = PostCommentRequest(
                _id = "",
                userName = postComment.userName,
                userEmail = postComment.userEmail,
                commentDate = postComment.commentDate,
                comment = postComment.comment,
                postId = postComment.postId,
                childComments = postComment.childComments
            )
        )
    }

    override suspend fun addChildComment(parentIndex: Int, childComment: ChildComment, id: String) {
        _comments.tryEmit(_comments.value.mapIndexed { index, topComment ->
            val childComments = topComment.childComments.toMutableList()
            if (topComment._id == id && parentIndex == index) {
                topComment.copy(
                    childComments = childComments.apply { add(childComment) }.toList()
                )
            } else {
                topComment
            }
        })
        val comment = comments.value[parentIndex]
        updateChildComment(
            postCommentRequest = PostCommentRequest(
                _id = comment._id,
                userName = comment.userName,
                userEmail = comment.userEmail,
                commentDate = comment.commentDate,
                comment = comment.userEmail,
                postId = comment.postId,
                childComments = comment.childComments
            )
        )
    }


    override fun updateReplyChatWindow(selectedIndex: Int) {
        _comments.tryEmit(_comments.value.mapIndexed { index, topComment ->
            topComment.copy(
                isReplyingForThisThread = selectedIndex == index
            )
        })
    }

    override suspend fun addComment(postCommentRequest: PostCommentRequest) {
        networkCall.addComment(postCommentRequest).handleResponse(onLoading = {},
            onSuccess = { console.info(it.responseMessage) },
            onFailure = {
                console.info("Error in adding Comment : $it")
            })
    }

    override suspend fun getComment(postId: String) {
        networkCall.getComment(postId).handleResponse(onLoading = {}, onSuccess = {
            _comments.tryEmit(it.data)
        }, onFailure = {
            console.info("Error in getting Comment : $it")
        })
    }

    override suspend fun updateChildComment(postCommentRequest: PostCommentRequest) {
        networkCall.updateChildComment(postCommentRequest)
            .handleResponse(onLoading = {}, onSuccess = {
                console.info(it.responseMessage)
                getComment(postCommentRequest.postId)
            }, onFailure = {
                console.info("Error in updating Comment : $it")
            })
    }
}

@Composable
fun rememberGlobalRepository(
): WebRepository {
    return remember {
        WebRepositoryImpl()
    }
}