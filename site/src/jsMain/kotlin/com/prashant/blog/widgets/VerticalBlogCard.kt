package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.utils.constants.ResourceConstants.contentDescription
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P

@Composable
fun VerticalBlogCard(src: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().onClick { onClick.invoke() },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Img(
            src = src,
            alt = src.contentDescription,
            attrs = Modifier.fillMaxWidth().margin(topBottom = 10.px).classNames(cssImgClassId)
                /*.maxWidth(775.px)
                .maxHeight(550.px)*/
                .toAttrs()
        )
        H5(
            attrs = Modifier.color(
                MaterialTheme.colorScheme.onContainer
            ).toAttrs()
        ) {
            AuthorNameWithCategory(
                author = "TOMAS LAURINAVICIUS",
                authorLink = "/author",
                category = "RESOURCE",
                categoryLink = ""
            )
        }
        H3(attrs = Modifier.cursor(Cursor.Pointer).toAttrs()) {
            SpanText(text = "Website Downtime: Applicable Tips on How to Prevent It")
        }

        P {
            SpanText("User research is the reality check every project needs. Here’s our guide to why you should be doing it — and how to get started.")
        }

    }
}