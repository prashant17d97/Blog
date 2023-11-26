package com.prashant.blog.repo

import com.prashant.blog.model.ChildComment
import com.prashant.blog.model.TopComment
import kotlinx.coroutines.flow.StateFlow

interface GlobalRepository {
    val comments: StateFlow<List<TopComment>>
    fun addComment(topComment: TopComment)
    fun addChildComment(childComment: ChildComment, id: Int)
    fun updateReplyChatWindow(selectedIndex: Int)
}