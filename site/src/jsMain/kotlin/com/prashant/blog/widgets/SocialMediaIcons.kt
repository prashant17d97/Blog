package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssIconId
import com.prashant.blog.utils.constants.ResourceConstants.contentDescription
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Img

@Composable
fun SocialMediaIcons(modifier: Modifier = Modifier, onClick: () -> Unit) {
    var visited by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = modifier.flexGrow(0.7f).gap(10.px),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ResourceConstants.FooterSocialIcons.socialMediaIcons.forEach { icon ->
            A(href = icon) {
                Img(src = icon,
                    alt = icon.contentDescription,
                    attrs = Modifier.size(40.px).onClick { onClick.invoke() }
                        .cursor(Cursor.Pointer).classNames(cssIconId).toAttrs())
            }
        }
    }
}