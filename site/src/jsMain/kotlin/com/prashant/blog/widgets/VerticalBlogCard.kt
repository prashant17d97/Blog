package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.model.PostModel
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.utils.constants.ResourceConstants.contentDescription
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.aspectRatio
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P

@Composable
fun VerticalBlogCard(
    postModel: PostModel,
    onPostClick: (id: String) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth().onClick {
            onPostClick(postModel._id)
        },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Img(
            src = postModel.thumbnail,
            alt = postModel.thumbnail.contentDescription,
            attrs = Modifier.fillMaxWidth()
                .objectFit(ObjectFit.Cover)
                .margin(topBottom = 10.px)
                .classNames(cssImgClassId)
                .maxWidth(775.px)
                .borderRadius(r = 4.px)
                .maxHeight(550.px)
                .styleModifier {
                    aspectRatio(16, 9)
                }
                .toAttrs()
        )
        H5(
            attrs = Modifier.color(
                MaterialTheme.colorScheme.onContainer
            ).toAttrs()
        ) {
            AuthorNameWithCategory(
                author = Pair(postModel.author, postModel.authorId),
                category = Pair(postModel.category, postModel.categoryId),
            )
        }
        H3(attrs = Modifier.cursor(Cursor.Pointer).toAttrs()) {
            SpanText(text = postModel.title)
        }

        P {
            SpanText(postModel.subtitle)
        }

    }
}