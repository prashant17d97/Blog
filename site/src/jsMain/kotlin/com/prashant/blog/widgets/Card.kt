package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssCardId
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import org.jetbrains.compose.web.css.px

@Composable
fun Card(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.SpaceEvenly,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Column(
        modifier = modifier.classNames(cssCardId).flexGrow(1f)
            .backgroundColor(MaterialTheme.colorScheme.container).boxShadow(
                blurRadius = 10.px, color = MaterialTheme.colorScheme.primary
            ).borderRadius(Constants.borderRadiusLarge)
            .minWidth(276.px).minHeight(169.px),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}