package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssIconId
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.silk.components.icons.fa.FaEye
import com.varabyte.kobweb.silk.components.icons.fa.FaHeart
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.icons.fa.IconStyle
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.P

@Composable
fun VerticalLikeView(
    modifier: Modifier = Modifier,
    onLikeClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FaHeart(
            style = IconStyle.FILLED,
            size = IconSize.LG,
            modifier = Modifier.color(MaterialTheme.colorScheme.onContainer)
                .classNames(cssIconId)
                .onClick { onLikeClick.invoke() }.margin(bottom = 10.px)
        )
        P { SpanText("100 K") }
        FaEye(
            style = IconStyle.FILLED,
            size = IconSize.LG,
            modifier = Modifier.classNames(cssIconId).margin(topBottom = 10.px)
        )
        P { SpanText("100 K") }
    }
}