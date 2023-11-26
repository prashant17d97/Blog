package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img

@Composable
fun CategoryViewItem(text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.width(Constants.HomeReadingListItemWidth)
            .onClick { onClick.invoke() },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Img(
            src = ResourceConstants.FooterSocialIcons.ReadingCard,
            attrs = Modifier.classNames(cssImgClassId)
                .minWidth(Constants.HomeReadingListImgWidth)
                .minHeight(Constants.HomeReadingListImgHeight)
                .maxWidth(Constants.HomeReadingListImgWidth)
                .maxHeight(Constants.HomeReadingListImgHeight)
                .borderRadius(Constants.borderRadiusLarge).margin(bottom = 10.px).toAttrs()
        )
        H3 {
            SpanText(text = text)
        }
    }
}