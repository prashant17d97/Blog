package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun PostAuthorView(
    modifier: Modifier = Modifier,
    author: String,
    noActionPerformed: Boolean = true,
    authorImage: String,
    authorLink: String = "/author",
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(10.px),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Img(
            src = authorImage,
            attrs = Modifier.margin(20.px).borderRadius(50.percent).size(60.px)
                .classNames(cssImgClassId).toAttrs()
        )

        if (noActionPerformed) {
            Link(path = authorLink) {
                H3(attrs = Modifier.color(MaterialTheme.colorScheme.action).toAttrs()) {
                    Text(author)
                }
            }
        } else {
            H3(attrs = Modifier.color(MaterialTheme.colorScheme.secondary).toAttrs()) {
                Text(author)
            }
        }
        P {
            SpanText(text = "Follow me on my social handles below")
        }
        SocialMediaIcons(modifier = Modifier.margin(10.px)) {}
    }
}