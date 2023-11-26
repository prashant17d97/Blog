package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaEye
import com.varabyte.kobweb.silk.components.icons.fa.FaHeart
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.icons.fa.IconStyle
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.P
@Composable
fun HorizontalLikeView(
    modifier: Modifier = Modifier,
    onLikeClick: () -> Unit,
) {
    Row(
        modifier = modifier.alignItems(AlignItems.Center).gap(10.px),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        FaHeart(style = IconStyle.FILLED,
            size = IconSize.LG,
            modifier = Modifier.color(MaterialTheme.colorScheme.onContainer)
                .onClick { onLikeClick.invoke() })
        P(
            attrs = Modifier.margin(0.px).textAlign(TextAlign.Center).toAttrs()
        ) {
            SpanText(
                "100 K"
            )
        }
        FaEye(
            style = IconStyle.FILLED, size = IconSize.LG, modifier = Modifier.margin(0.px)
        )
        P(
            attrs = Modifier.margin(0.px).textAlign(TextAlign.Center).toAttrs()
        ) {
            SpanText(
                "100 K",
            )
        }
    }
}