package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.dom.P

@Composable
fun NewBlogItems(onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().onClick { onClick.invoke() }) {
        P(attrs = Modifier.color(MaterialTheme.colorScheme.onContainer).toAttrs()) {
            SpanText(text = "SEP  04  2018")
        }

        SpanText(text = "Our 15 favorite websites from August 2018")
    }
}