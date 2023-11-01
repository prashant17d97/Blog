package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import com.prashant.blog.components.composetags.BlogLayout
import com.prashant.blog.components.composetags.Widgets
import com.prashant.blog.components.composetags.Widgets.AuthorNameWithCategory
import com.prashant.blog.components.composetags.Widgets.HeadingViewAll
import com.prashant.blog.components.composetags.Widgets.HorizontalLikeView
import com.prashant.blog.components.composetags.Widgets.PostAuthorView
import com.prashant.blog.components.composetags.Widgets.VerticalLikeView
import com.prashant.blog.components.constants.ResourceConstants.FooterSocialIcons.SuggestionOne
import com.prashant.blog.components.constants.ResourceConstants.FooterSocialIcons.SuggestionTwo
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H4

@Page
@Composable
fun Post() {
    BlogLayout { isBreakPoint, pageContext ->

        Column(modifier = Modifier.margin(top = 60.px).padding(leftRight = 10.px)) {
            H1 {
                SpanText(text = "10 Reason to Build Your Website with US!")
            }
            H4(attrs = Modifier.margin(topBottom = 10.px).toAttrs()) {
                SpanText(text = "People's quest for creating website has easily taken us to a new era of site development. Where, with the availability of robust page building tools, creating websites has become a lot more fun(especially for non-developer")
            }
            AuthorNameWithCategory(
                modifier = Modifier.margin(topBottom = 20.px),
                author = "Prashant",
                authorLink = "Prashant",
                category = "Prashant",
                categoryLink = "Prashant"
            )
        }

        if (isBreakPoint) {
            SmallScreen()
        } else {
            LargeScreen()
        }
    }
}

@Composable
private fun SmallScreen() {
    Column(modifier = Modifier.flexGrow(1f)) {

        HorizontalLikeView(modifier = Modifier.margin(20.px)) {}
        //Post data
        PostAuthorView(
            authorImage = SuggestionTwo,
            author = "Prashant",
            authorLink = "Prashant"
        )
        HeadingViewAll(
            modifier = Modifier.margin(topBottom = 20.px).padding(leftRight = 10.px),
            heading = "You might also like....",
            headingSecond = "More"
        ) {}

        Column( modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px)) {
            Widgets.VerticalBlogCard(src = SuggestionOne) {}
            Widgets.VerticalBlogCard(src = SuggestionTwo) {}

        }
    }

}

@Composable
fun LargeScreen() {
    Row(modifier = Modifier.fillMaxWidth().margin(top = 30.px)) {
        Column(modifier = Modifier.weight(1f)) {
            PostAuthorView(
                authorImage = SuggestionTwo,
                author = "Prashant",
                authorLink = "Prashant"
            )
            HeadingViewAll(
                modifier = Modifier.margin(topBottom = 20.px),
                heading = "You might also like....",
                headingSecond = "More"
            ) {}

            SimpleGrid(numColumns(base = 2), modifier = Modifier.fillMaxWidth().gap(10.px)) {

                Widgets.VerticalBlogCard(src = SuggestionOne) {}
                Widgets.VerticalBlogCard(src = SuggestionTwo) {}

            }
        }

        VerticalLikeView(modifier = Modifier.margin(20.px)) {}

    }
}