package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.utils.css.CssAttributesUtils.onHover
import com.prashant.blog.utils.css.CssAttributesUtils.textColor
import com.prashant.blog.utils.css.CssAttributesUtils.textDecor
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.Text


@Composable
fun HeadingViewAll(
    modifier: Modifier = Modifier,
    heading: String,
    headingSecond: String = "View all new",
    onClick: () -> Unit,
) {
    var onMouseHover by remember { mutableStateOf(false) }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        H4 {
            Text(value = heading)
        }
        H5(attrs = Modifier
            .onClick { onClick.invoke() }
            .textColor(onMouseHover)
            .onHover(onHover = { onMouseHover = it })
            .textDecor(onMouseHover)
            .cursor(Cursor.Pointer).toAttrs()
        ) {
            SpanText(text = headingSecond)
        }
    }
}