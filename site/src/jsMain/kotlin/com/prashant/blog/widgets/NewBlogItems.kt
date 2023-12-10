package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.model.PostModel
import com.prashant.blog.utils.commonfunctions.DateTimeUtil.parseDateString
import com.prashant.blog.utils.css.CssAttributesUtils.onHover
import com.prashant.blog.utils.css.CssAttributesUtils.textColor
import com.prashant.blog.utils.css.CssAttributesUtils.textDecor
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.P

@Composable
fun NewBlogItems(postModel: PostModel, onClick: () -> Unit) {
    var onMouseHover by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().onClick { onClick.invoke() }) {
        H5(attrs = Modifier
            .onClick { onClick.invoke() }
            .textColor(onMouseHover)
            .onHover(onHover = { onMouseHover = it })
            .textDecor(onMouseHover)
            .cursor(Cursor.Pointer).toAttrs()
        ) {
            SpanText(text = postModel.title)
        }
        SpanText(text = postModel.subtitle)
        P(attrs = Modifier.color(MaterialTheme.colorScheme.onContainer).toAttrs()) {
            SpanText(text = postModel.createdAt.parseDateString())
        }
    }
}