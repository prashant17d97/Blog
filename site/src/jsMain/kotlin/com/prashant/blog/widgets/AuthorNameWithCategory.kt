package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.utils.css.CssAttributesUtils.anchorTextColor
import com.prashant.blog.utils.css.CssAttributesUtils.hoverFilter
import com.prashant.blog.utils.css.CssAttributesUtils.onHover
import com.prashant.blog.utils.css.CssAttributesUtils.textDecor
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H6
@Composable
fun AuthorNameWithCategory(
    modifier: Modifier = Modifier,
    author: String,
    authorLink: String = "/author",
    category: String,
    categoryLink: String,
) {
    var onAnchorMouseHover by remember { mutableStateOf(false) }
    var onAnchorCategoryMouseHover by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        H6(
            attrs = Modifier.color(MaterialTheme.colorScheme.text).toAttrs()
        ) {
            SpanText(text = "By ")
        }


        A(
            href = authorLink,
            attrs = Modifier.anchorTextColor(onAnchorMouseHover)
                .onHover(onHover = { onAnchorMouseHover = it })
                .textDecor(onAnchorMouseHover).toAttrs()
        ) {
            H6 {
                SpanText(text = author)
            }
        }
        H6(
            attrs = Modifier.color(MaterialTheme.colorScheme.text).hoverFilter().toAttrs()
        ) {
            SpanText(text = " In ")
        }
        A(
            href = categoryLink,
            attrs = Modifier.anchorTextColor(onAnchorCategoryMouseHover)
                .onHover(onHover = { onAnchorCategoryMouseHover = it })
                .textDecor(onAnchorCategoryMouseHover).toAttrs()
        ) {
            H6 {
                SpanText(text = category)
            }
        }
    }
}