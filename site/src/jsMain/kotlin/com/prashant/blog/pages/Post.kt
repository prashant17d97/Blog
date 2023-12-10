package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Id
import com.prashant.blog.model.AuthorModel
import com.prashant.blog.model.AuthorModel.Companion.getEmptyBody
import com.prashant.blog.model.PostComment
import com.prashant.blog.model.PostModel
import com.prashant.blog.navigation.NavigationRoute
import com.prashant.blog.network.rememberNetworkCall
import com.prashant.blog.repo.rememberGlobalRepository
import com.prashant.blog.utils.commonfunctions.CommonFunctions.handleResponse
import com.prashant.blog.utils.commonfunctions.DateTimeUtil.getCurrentTimestamp
import com.prashant.blog.utils.commonfunctions.DateTimeUtil.parseDateString
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.widgets.AuthorNameWithCategory
import com.prashant.blog.widgets.BlogLayout
import com.prashant.blog.widgets.CommentsThread
import com.prashant.blog.widgets.HeadingViewAll
import com.prashant.blog.widgets.HorizontalLikeView
import com.prashant.blog.widgets.PostAuthorView
import com.prashant.blog.widgets.PostComment
import com.prashant.blog.widgets.PostContent
import com.prashant.blog.widgets.VerticalBlogCard
import com.prashant.blog.widgets.VerticalLikeView
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H4

@Page
@Composable
fun Post() {
    val repository = rememberGlobalRepository()
    val networkCall = rememberNetworkCall()
    var postModel: PostModel by remember { mutableStateOf(PostModel(content = "")) }
    var authorModel: AuthorModel by remember { mutableStateOf(getEmptyBody) }
    val topComment by repository.comments.collectAsState()
    var isReplying by remember {
        mutableStateOf(false)
    }
    var postId: String? by remember { mutableStateOf(null) }
    var postModels: List<PostModel> by remember { mutableStateOf(emptyList()) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        if (postId?.isNotEmpty() == true && postId != null) {
            networkCall.retrievePost(postId = postId!!).handleResponse(onLoading = {
            }, onSuccess = { response ->
                postModel = response.data
                delay(50)
                try {
                    js("hljs.highlightAll()") as Unit
                } catch (e: Exception) {
                    println(e.message)
                }
            }, onFailure = {
                console.info("Error: $it")
            })

            postId?.let { repository.getComment(it) }
        }
        networkCall.fetchAllPost().handleResponse(onLoading = {

        }, onSuccess = {
            postModels = it.data
        }, onFailure = {

        })
    }

    LaunchedEffect(postModel) {
        if (postModel.authorId.isNotEmpty()) {
            networkCall.getAuthorById(postModel.authorId).handleResponse(onLoading = {},
                onSuccess = { authorModel = it.data },
                onFailure = {})
        }
    }
    BlogLayout { isBreakPoint, pageContext ->
        postId = pageContext.route.params[Id]
        Column(
            modifier = Modifier.margin(top = 10.px).fillMaxWidth().padding(leftRight = 10.px),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            H1 {
                SpanText(text = postModel.title)
            }
            H4(attrs = Modifier.margin(topBottom = 10.px).toAttrs()) {
                SpanText(text = postModel.subtitle)
            }
            AuthorNameWithCategory(
                modifier = Modifier.margin(top = 20.px),
                author = Pair(postModel.author, postModel.authorId),
                category = Pair(postModel.category, postModel.categoryId)
            )
            SpanText(
                modifier = Modifier.fillMaxWidth().margin(bottom = 20.px)
                    .color(MaterialTheme.colorScheme.text),
                text = postModel.createdAt.parseDateString()
            )
            if (isBreakPoint) {
                SmallScreen(
                    postModel = postModel
                )
            } else {
                LargeScreen(postModel = postModel)
            }

            PostAuthorView(
                author = authorModel,
            )
            HeadingViewAll(
                modifier = Modifier.margin(topBottom = 20.px),
                heading = "You might also like....",
                headingSecond = "More"
            ) {
                pageContext.router.navigateTo(NavigationRoute.New.routeData.route)
            }

            SimpleGrid(
                numColumns(base = 1.takeIf { isBreakPoint } ?: 2),
                modifier = Modifier.fillMaxWidth().margin(bottom = 20.px).gap(10.px)
            ) {
                postModels.forEach {
                    VerticalBlogCard(postModel = it, pageContext = pageContext)
                }
            }

        }

        if (topComment.isNotEmpty()) {
            CommentsThread(
                repository = repository,
                padding = 15,
                widthPercent = 100,
                isBreakPoint = isBreakPoint,
                isReplying = isReplying,
                onClick = { isReplying = it },
                onReplyValueChange = { isReplying = it })
        }
        if (!isReplying) {
            PostComment(
                padding = 15,
                isBreakpoint = isBreakPoint,
                onIsReplyingChange = { isReplying = it }
            ) { comment, name, email ->
                if (comment.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty()) {
                    scope.launch {
                        repository.addComment(
                            PostComment(
                                _id = "",
                                userName = name,
                                userImage = ResourceConstants.FooterSocialIcons.RandomImg,
                                userEmail = email,
                                commentDate = getCurrentTimestamp(),
                                comment = comment,
                                postId = postModel._id,
                                childComments = arrayListOf()
                            )
                        )
                    }
                }
            }
        }

    }
}

@Composable
private fun SmallScreen(
    postModel: PostModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalLikeView(modifier = Modifier.margin(20.px)) {}

        //Post data
        PostContent(
            modifier = Modifier, postContent = postModel
        )

    }

}

@Composable
fun LargeScreen(
    postModel: PostModel
) {

    Column(
        modifier = Modifier.fillMaxWidth().padding(10.px),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().margin(topBottom = 30.px),
            horizontalArrangement = Arrangement.End
        ) {
            //Post data
            PostContent(
                modifier = Modifier, postContent = postModel
            )
            VerticalLikeView(modifier = Modifier.width(100.px)) {}

        }

    }

}