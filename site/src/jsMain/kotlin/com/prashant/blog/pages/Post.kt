package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.components.composetags.BlogLayout
import com.prashant.blog.components.composetags.Widgets
import com.prashant.blog.components.composetags.Widgets.AuthorNameWithCategory
import com.prashant.blog.components.composetags.Widgets.CommentsThread
import com.prashant.blog.components.composetags.Widgets.HeadingViewAll
import com.prashant.blog.components.composetags.Widgets.HorizontalLikeView
import com.prashant.blog.components.composetags.Widgets.PostAuthorView
import com.prashant.blog.components.composetags.Widgets.PostComment
import com.prashant.blog.components.composetags.Widgets.VerticalLikeView
import com.prashant.blog.components.model.ChildComment
import com.prashant.blog.components.model.TopComment
import com.prashant.blog.repo.GlobalRepository
import com.prashant.blog.repo.rememberGlobalRepository
import com.prashant.blog.utils.CssAttributesUtils.findLastId
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.constants.ResourceConstants.FooterSocialIcons.SuggestionOne
import com.prashant.blog.utils.constants.ResourceConstants.FooterSocialIcons.SuggestionTwo
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H4

@Page
@Composable
fun Post() {
    val repository = rememberGlobalRepository()
    BlogLayout { isBreakPoint, pageContext ->

        Column(modifier = Modifier.margin(top = 10.px).padding(leftRight = 10.px)) {
            H1 {
                SpanText(text = "10 Reason to Build Your Website with US!")
            }
            H4(attrs = Modifier.margin(topBottom = 10.px).toAttrs()) {
                SpanText(text = "People's quest for creating website has easily taken us to a new era of site development. Where, with the availability of robust page building tools, creating websites has become a lot more fun(especially for non-developer)")
            }
            AuthorNameWithCategory(
                modifier = Modifier.margin(topBottom = 20.px),
                author = "Prashant",
                category = "Prashant",
                categoryLink = "Prashant"
            )
        }

        if (isBreakPoint) {
            SmallScreen(isBreakPoint, pageContext, repository)
        } else {
            LargeScreen(pageContext, repository)
        }
    }
}

@Composable
private fun SmallScreen(
    isBreakpoint: Boolean,
    pageContext: PageContext,
    repository: GlobalRepository
) {
    val topComment by repository.comments.collectAsState()
    var isReplying by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.flexGrow(1f)) {

        HorizontalLikeView(modifier = Modifier.margin(20.px)) {}
        //Post data
        PostAuthorView(
            authorImage = SuggestionTwo,
            author = "Prashant",
        )
        HeadingViewAll(
            modifier = Modifier.margin(topBottom = 20.px).padding(leftRight = 10.px),
            heading = "You might also like....",
            headingSecond = "More"
        ) {
            console.log("Click for more")
        }

        Column(modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px)) {
            Widgets.VerticalBlogCard(src = SuggestionOne) {}
            Widgets.VerticalBlogCard(src = SuggestionTwo) {}

        }

        if (topComment.isNotEmpty()) {
            CommentsThread(
                repository = repository,
                padding = 10,
                widthPercent = 100,
                onClick = { isReplying = it }) { parentCommentId ->
                if (isReplying) {
                    PostComment(
                        heading = "Leave a reply",
                        isReply = true,
                        padding = 10,
                        isBreakpoint = isBreakpoint
                    ) { comment, name, email ->
                        console.info("Reply Before Small:---$comment-->, $name---> $email")
                        if (comment.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty()) {
                            repository.addChildComment(
                                ChildComment(
                                    userName = name,
                                    userImage = ResourceConstants.FooterSocialIcons.RandomImg,
                                    userEmail = email,
                                    commentDate = "November 02 2023 at 8:30 AM",
                                    comment = comment
                                ), id = parentCommentId
                            )
                        }
                        console.info("Reply After Small:---$comment-->, $name---> $email")
                        isReplying = false
                    }
                }
            }
        }
        if (!isReplying) {
            PostComment(
                padding = 10,
                isBreakpoint = isBreakpoint,
            ) { comment, name, email ->
                if (comment.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty()) {
                    repository.addComment(
                        TopComment(
                            id = topComment.findLastId() + 1,
                            userName = name,
                            userImage = ResourceConstants.FooterSocialIcons.RandomImg,
                            userEmail = email,
                            commentDate = "November 02 2023 at 8:30 AM",
                            comment = comment,
                            childComments = arrayListOf()
                        )
                    )
                    console.info("$comment-->, $name---> $email")
                }
            }
        }
    }

}

@Composable
fun LargeScreen(pageContext: PageContext, repository: GlobalRepository) {
    val topComment by repository.comments.collectAsState()

    var isReplying by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(10.px), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth().margin(top = 30.px), horizontalArrangement = Arrangement.End) {
            VerticalLikeView(modifier = Modifier.width(100.px)) {}

        }
        PostAuthorView(
            authorImage = SuggestionTwo,
            author = "Prashant",
        )
        HeadingViewAll(
            modifier = Modifier.margin(topBottom = 20.px),
            heading = "You might also like....",
            headingSecond = "More"
        ) {
            console.info("Click for more")
        }

        SimpleGrid(
            numColumns(base = 2), modifier = Modifier.fillMaxWidth().margin(bottom = 20.px)
                .gap(10.px)
        ) {

            Widgets.VerticalBlogCard(src = SuggestionOne) {}
            Widgets.VerticalBlogCard(src = SuggestionTwo) {}

        }

        if (topComment.isNotEmpty()) {
            CommentsThread(
                repository = repository,
                onClick = { isReplying = it }) { parentCommentId ->
                if (isReplying) {
                    PostComment(
                        heading = "Leave a reply",
                        isReply = true,
                        isBreakpoint = true
                    ) { comment, name, email ->
                        console.info("Reply Before:---$comment-->, $name---> $email")

                        repository.addChildComment(
                            childComment = ChildComment(
                                userName = name,
                                userImage = ResourceConstants.FooterSocialIcons.RandomImg,
                                userEmail = email,
                                commentDate = "November 02 2023 at 8:30 AM",
                                comment = comment
                            ),
                            id = parentCommentId
                        )
                        console.info("Reply:---$comment-->, $name---> $email")
                        isReplying = false
                    }
                }
            }
        }
        if (!isReplying) {
            PostComment { comment, name, email ->
                console.info("Before:-----$comment-->, $name---> $email")
                if (comment.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty()) {
                    repository.addComment(
                        TopComment(
                            id = topComment.findLastId() + 1,
                            userName = name,
                            userImage = ResourceConstants.FooterSocialIcons.RandomImg,
                            userEmail = email,
                            commentDate = "November 02 2023 at 8:30 AM",
                            comment = comment,
                            childComments = arrayListOf()
                        )
                    )
                    console.info("After:-----$comment-->, $name---> $email")
                }
            }
        }
    }

}