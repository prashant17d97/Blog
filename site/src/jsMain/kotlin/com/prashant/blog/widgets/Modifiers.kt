package com.prashant.blog.widgets

import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.visibility

fun Modifier.visibility(visibility: Boolean): Modifier {
    return this.visibility(visibility = Visibility.Visible.takeIf { visibility }
        ?: Visibility.Hidden)
}