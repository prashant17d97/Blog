package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.navigation.NavigationRoute
import com.prashant.blog.network.rememberNetworkCall
import com.prashant.blog.utils.commonfunctions.CommonFunctions.findKey
import com.prashant.blog.utils.commonfunctions.CommonFunctions.handleResponse
import com.prashant.blog.utils.constants.ResourceConstants
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
    val networkCall = rememberNetworkCall()
    //val loaderVisibility by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        /*networkCall.createNewAuthor(
            AuthorModel(
                _id = "65678d293a7ebc81bf369386",
                name = "Prashant Singh",
                userImage = ""
            )
        ).handleResponse(
            onLoading = {
                console.info("Author by loading: $it")
            }, onSuccess = {
                console.info("Author by body: ${it.responseMessage}")
            }, onFailure = {
                console.info("Author by body failure: $it")
            })*/

        networkCall.retrievePost(
            "656d6488c0baf863a951682b"
        ).handleResponse(onLoading = {
            console.info("AuthorPage: $it")
        }, onSuccess = {
            console.info("AuthorPage: ${it.responseMessage}")
        }, onFailure = {
            console.info("AuthorPage: $it")
        })

    }
    val totalPage by remember { mutableStateOf(18) }
    var currentPage by remember {
        mutableStateOf(1)
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

    BlogLayout { _, pageContext ->

        val pageQuery = pageContext.route
        val page = pageQuery.queryParams.keys.findKey(
            "page"
        )
        currentPage = pageQuery.queryParams[page]?.toInt() ?: 1


        Row(modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px).gap(10.px)) {

            //Left profile & article Column
            Column(modifier = Modifier.weight(1.4f).gap(10.px)) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    PostAuthorView(
                        authorImage = ResourceConstants.FooterSocialIcons.SuggestionTwo,
                        noActionPerformed = false,
                        author = "Prashant",
                        authorLink = "/author"
                    )
                }

                SimpleGrid(
                    numColumns(rememberBreakpoint().getColumnCount()),
                    modifier = Modifier.gap(15.px)
                ) {
                    repeat(3) {
                        VerticalBlogCard(src = ResourceConstants.FooterSocialIcons.SuggestionOne) {
                            pageContext.navigateTo(
                                NavigationRoute.Post
                            )
                        }
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
                    repeat(4) {
                        AuthorPopularRecentPost()
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
                    repeat(4) {
                        AuthorPopularRecentPost()
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