package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.authorLink
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.categoryLink
import com.prashant.blog.utils.css.CssAttributesUtils.anchorTextColor
import com.prashant.blog.utils.css.CssAttributesUtils.hoverFilter
import com.prashant.blog.utils.css.CssAttributesUtils.onHover
import com.prashant.blog.utils.css.CssAttributesUtils.textDecor
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color.Rgb
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H6

@Composable
fun AuthorNameWithCategory(
    modifier: Modifier = Modifier,
    author: Pair<String, String>,
    category: Pair<String, String>,
    color: Rgb? = null
) {
    var onAnchorMouseHover by remember { mutableStateOf(false) }
    var onAnchorCategoryMouseHover by remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        H6(
            attrs = Modifier.color(color?:MaterialTheme.colorScheme.text).toAttrs()
        ) {
            SpanText(text = "By ")
        }


        A(
            href = authorLink(author.second),
            attrs = Modifier.anchorTextColor(onAnchorMouseHover)
                .onHover(onHover = { onAnchorMouseHover = it })
                .textDecor(onAnchorMouseHover).toAttrs()
        ) {
            H6 {
                SpanText(text = author.first)
            }
        }
        H6(
            attrs = Modifier.color(color?:MaterialTheme.colorScheme.text).hoverFilter().toAttrs()
        ) {
            SpanText(text = " In ")
        }
        A(
            href = categoryLink(category.second),
            attrs = Modifier.anchorTextColor(onAnchorCategoryMouseHover)
                .onHover(onHover = { onAnchorCategoryMouseHover = it })
                .textDecor(onAnchorCategoryMouseHover).toAttrs()
        ) {
            H6 {
                SpanText(text = category.first)
            }
        }
    }
}