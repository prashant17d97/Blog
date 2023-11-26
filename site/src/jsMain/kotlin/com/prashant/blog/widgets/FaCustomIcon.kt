package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.Span

@Composable
fun FaCustomIcon(
    modifier: Modifier = Modifier,
    iconName: String = "fa-angle-right",
    style: String = "fa-solid"
) {
    Span(attrs = modifier.toAttrs {
        classes("fa", iconName)
        classes(style)
        classes("fa-lg")
    })


}