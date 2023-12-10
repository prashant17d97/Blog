package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Span

@Composable
fun FaCustomIcon(
    modifier: Modifier = Modifier,
    iconName: String = "angle-right",
    style: String = "solid",
    classname: String = "",
    size: String = "lg",
) {
    Span(attrs = modifier.toAttrs {
        classes("fa-$classname", "fa-$iconName", "fa-$style", "fa-$size")
    })
}