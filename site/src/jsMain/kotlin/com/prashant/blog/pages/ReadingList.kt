package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.components.composetags.BlogLayout
import com.prashant.blog.components.composetags.widgets.ReadingListItem
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.window
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P

@Page
@Composable
fun ReadingList() {

    var listItemCount by remember {
        mutableStateOf(2)
    }

    BlogLayout(
        columnModifier = Modifier.padding(top = 30.px, leftRight = 10.px),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) { isBreakPoint, pageContext ->

        listItemCount = 1.takeIf { isBreakPoint } ?: 2
        H2 {
            SpanText("All Reading lists")
        }
        P {
            SpanText("Get in-depth insights on web design, freelancing, content management, and more with these series of related reads.")
        }
        SimpleGrid(
            numColumns = numColumns(listItemCount),
            modifier = Modifier.gap(20.px).fillMaxWidth().margin(top = 30.px, bottom = 10.px)
        ) {
            repeat(8) {
                ReadingListItem {
                    pageContext.router.navigateTo("/new?category=latest")
                    console.info("Item clicked index: ${window.history.state}")
                }
            }
        }


    }
}