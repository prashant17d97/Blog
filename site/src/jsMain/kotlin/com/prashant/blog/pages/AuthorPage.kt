package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Id
import com.prashant.blog.model.AuthorModel
import com.prashant.blog.model.AuthorModel.Companion.getEmptyBody
import com.prashant.blog.model.PostModel
import com.prashant.blog.navigation.NavigationRoute
import com.prashant.blog.network.rememberNetworkCall
import com.prashant.blog.utils.commonfunctions.CommonFunctions.handleResponse
import com.prashant.blog.utils.navigation.navigateTo
import com.prashant.blog.widgets.AuthorPopularRecentPost
import com.prashant.blog.widgets.BlogLayout
import com.prashant.blog.widgets.Calendar
import com.prashant.blog.widgets.Card
import com.prashant.blog.widgets.PaginationCarousel
import com.prashant.blog.widgets.PostAuthorView
import com.prashant.blog.widgets.VerticalBlogCard
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.datetime.internal.JSJoda.LocalDate
import kotlinx.datetime.internal.JSJoda.ZoneId
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H4


@Page("author")
@Composable
fun AuthorPage() {
    var authorModel: AuthorModel by remember { mutableStateOf(getEmptyBody) }
    var postModel: List<PostModel> by remember { mutableStateOf(emptyList()) }
    val pageContext = rememberPageContext()
    val networkCall = rememberNetworkCall()

    val totalPage by remember { mutableStateOf(18) }
    val currentPage by remember {
        mutableStateOf(1)
    }
    var postModels: List<PostModel> by remember { mutableStateOf(emptyList()) }
    LaunchedEffect(Unit) {
        networkCall.fetchAllPost().handleResponse(onLoading = {

        }, onSuccess = {
            postModels = it.data
        }, onFailure = {

        })
    }
    val localDate = LocalDate.now(clockOrZone = ZoneId.SYSTEM)
    var selectedDate by remember {
        mutableStateOf(
            LocalDate.of(
                localDate.year().toInt(),
                localDate.month().value().toInt(),
                localDate.dayOfMonth().toInt()
            ).toString()
        )
    }

    LaunchedEffect(Unit) {
        val authorId = pageContext.route.params[Id]
        authorId?.let {
            networkCall.getAuthorById(it).handleResponse(
                onLoading = {},
                onSuccess = { authorModelResponse ->
                    authorModel = authorModelResponse.data
                },
                onFailure = {})

            networkCall.getAuthorsPosts(it).handleResponse(
                onLoading = {},
                onSuccess = { postsList ->
                    console.info(
                        "${postsList.responseMessage}," +
                                "${postsList.data}"
                    )
                    postModel = postsList.data
                },
                onFailure = {
                    console.info(it)
                })
        }
    }

    BlogLayout { _, pageContext ->

        Row(modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px).gap(10.px)) {

            //Left profile & article Column
            Column(modifier = Modifier.weight(1.4f).gap(10.px)) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    PostAuthorView(
                        author = authorModel,
                        noActionPerformed = false,
                    )
                }

                SimpleGrid(
                    numColumns(rememberBreakpoint().getColumnCount()),
                    modifier = Modifier.gap(15.px)
                ) {
                    postModel.forEach { postModel ->
                        VerticalBlogCard(postModel, pageContext)
                    }
                }

                PaginationCarousel(
                    totalPages = totalPage, currentPage = currentPage
                ) {
                    pageContext.navigateTo(
                        NavigationRoute.Author.buildUrl {
                            addQueryParam("page", it.toString())
                            addQueryParam("date", selectedDate)
                        }
                    )
                }
            }

            //Right widget column
            Column(modifier = Modifier.weight(0.6f).gap(10.px)) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(15.px).styleModifier {

                    },
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    H4(
                        attrs = Modifier.margin(bottom = 15.px).toAttrs()
                    ) { SpanText("Popular Post") }
                    postModel.forEach {
                        AuthorPopularRecentPost(it)
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth().padding(15.px),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    H4(
                        attrs = Modifier.margin(bottom = 15.px).toAttrs()
                    ) { SpanText("Recent Post") }
                    postModels.forEach {
                        AuthorPopularRecentPost(it)
                    }
                }
                Card {
                    Calendar(localDate) {
                        selectedDate = it.toString()
                        console.info(selectedDate)
                    }
                }
            }
        }
    }
}

private fun Breakpoint.getColumnCount(): Int {
    return when (this) {
        Breakpoint.ZERO -> 0
        Breakpoint.SM, Breakpoint.MD -> 1
        Breakpoint.LG, Breakpoint.XL -> 2
    }
}