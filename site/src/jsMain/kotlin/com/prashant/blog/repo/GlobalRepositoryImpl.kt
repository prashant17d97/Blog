package com.prashant.blog.repo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.prashant.blog.components.model.ChildComment
import com.prashant.blog.components.model.TopComment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GlobalRepositoryImpl : GlobalRepository {

    private var _comments: MutableStateFlow<List<TopComment>> = MutableStateFlow(emptyList())
    override val comments: StateFlow<List<TopComment>>
        get() = _comments

    override fun addComment(topComment: TopComment) {
        console.info("TopComments: $topComment")
        _comments.tryEmit(_comments.value + topComment)
    }

    override fun addChildComment(childComment: ChildComment, id: Int) {
        var childComments: ArrayList<ChildComment>
        _comments.tryEmit(
            _comments.value.mapIndexed { _, topComment ->
                childComments = topComment.childComments
                childComments.add(childComment)
                if (topComment.id == id) {
                    topComment.copy(
                        childComments = childComments
                    )
                } else {
                    topComment
                }
            }
        )
    }

    override fun updateReplyChatWindow(selectedIndex: Int) {
        _comments.tryEmit(
            _comments.value.mapIndexed { index, topComment ->
                topComment.copy(
                    isReplyingForThisThread = selectedIndex == index
                )
            }
        )
    }


}

@Composable
fun rememberGlobalRepository(
): GlobalRepository {
    return remember {
        GlobalRepositoryImpl()
    }
}