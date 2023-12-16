package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.model.CategoryModel
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P

@Composable
fun ReadingListItem(category: CategoryModel, onItemClick: (categoryId: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .maxHeight(Constants.HomeReadingListImgHeight)
            .boxShadow(
                blurRadius = 10.px,
                color = MaterialTheme.colorScheme.text.copyf(alpha = 0.5f)
            )
            .onClick { onItemClick.invoke(category._id) }
            .cursor(Cursor.Pointer)
            .borderRadius(Constants.borderRadiusLarge)
    ) {
        Img(
            src = category.thumbnail.ifEmpty { ResourceConstants.FooterSocialIcons.ReadingCard },
            attrs = Modifier.classNames(ResourceConstants.CSSIds.cssImgClassId)
                .weight(1f)
                .maxWidth(Constants.HomeReadingListImgWidth)
                .maxHeight(Constants.HomeReadingListImgHeight)
                .borderRadius(Constants.borderRadiusLarge).toAttrs()
        )
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(10.px),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            H3 {
                SpanText(category.category)
            }
            P {
                SpanText(category.description)
            }
        }
    }
}