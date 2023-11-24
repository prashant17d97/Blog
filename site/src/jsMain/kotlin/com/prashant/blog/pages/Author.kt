package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.components.composetags.BlogLayout
import com.prashant.blog.components.composetags.Widgets
import com.prashant.blog.components.composetags.Widgets.Card
import com.prashant.blog.components.composetags.widgets.AuthorPopularRecentPost
import com.prashant.blog.components.composetags.widgets.PaginationCarousel
import com.prashant.blog.utils.constants.ResourceConstants
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H4


@Page
@Composable
fun Author() {
    val breakPoint = rememberBreakpoint()
    val totalPage by remember { mutableStateOf(5) }
    var currentPage by remember { mutableStateOf(0) }

    BlogLayout { isBreakPoint, pageContext ->
        Row(modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px).gap(10.px)) {

            //Left profile & article Column
            Column(modifier = Modifier.weight(1.4f).gap(10.px)) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Widgets.PostAuthorView(
                        authorImage = ResourceConstants.FooterSocialIcons.SuggestionTwo,
                        noActionPerformed = false,
                        author = "Prashant",
                        authorLink = "/author"
                    )
                }

                SimpleGrid(
                    numColumns(breakPoint.getColumnCount()),
                    modifier = Modifier.gap(15.px)
                ) {
                    repeat(3) {
                        Widgets.VerticalBlogCard(src = ResourceConstants.FooterSocialIcons.SuggestionOne) {}
                    }
                }

                PaginationCarousel(totalPages = totalPage, currentPage = currentPage) {
                    currentPage = it
                }
            }

            //Right widget column
            Column(modifier = Modifier.weight(0.6f).gap(10.px)) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(15.px),
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