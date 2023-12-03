package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.navigation.NavigationRoute
import com.prashant.blog.utils.commonfunctions.CommonFunctions.capitalize
import com.prashant.blog.utils.commonfunctions.CommonFunctions.findKey
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.navigation.navigateTo
import com.prashant.blog.widgets.BlogLayout
import com.prashant.blog.widgets.VerticalBlogCard
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.Element

@Page
@Composable
fun New() {
    var count by remember { mutableStateOf(1) }
    BlogLayout(
        columnModifier = Modifier.padding(top = 30.px, leftRight = 10.px),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) { isBreakPoint, pageContext ->

        val pageQuery = pageContext.route
        val category = pageQuery.queryParams.keys.findKey(
            "category"
        )
        val categoryValue = pageQuery.queryParams[category]
        val categoryFinalValue =
            categoryValue.toString().capitalize()
                .takeIf { category == "category" && categoryValue?.isNotEmpty() == true }
                ?: "New"

        H2 {
            SpanText(categoryFinalValue)
        }
        P {
            SpanText("Our latest web design tips, tricks, insights, and resources, hot off the presses.")

        }
        SimpleGrid(numColumns(base = 1.takeIf { isBreakPoint } ?: 2),
            modifier = Modifier.fillMaxWidth().padding(top = 30.px).gap(10.px)) {
            repeat(count) {
                VerticalBlogCard(src = ResourceConstants.FooterSocialIcons.SuggestionOne) {
                    pageContext.navigateTo(NavigationRoute.Post)
                }
                VerticalBlogCard(src = ResourceConstants.FooterSocialIcons.SuggestionTwo) {
                    pageContext.navigateTo(NavigationRoute.Post)
                }
            }
        }

        Row(
            modifier = Modifier.id("loadMore").fillMaxWidth().margin(top = 60.px),
            horizontalArrangement = Arrangement.Center
        ) {
            H5(
                attrs = Modifier
                    .id("clickableText")
                    .textAlign(TextAlign.Center)
                    .onClick {
                        val targetElement = document.getElementById("loadMore")
                        count += 1
                    }
                    .cursor(Cursor.Pointer).toAttrs()
            ) {
                Text(value = "Load more...")
            }

        }
    }
}

fun Element?.smoothScroll() {
    this?.scrollIntoView(js("{behavior: \"smooth\"}"))
}