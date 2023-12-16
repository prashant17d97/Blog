package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.model.PostModel
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.constants.ResourceConstants.contentDescription
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text

@Composable
fun AuthorPopularRecentPost(
    postModel: PostModel,
    onPostClick: (String) -> Unit
) {

    Row(
        Modifier.fillMaxWidth().gap(10.px).onClick { onPostClick.invoke(postModel._id) }
            .cursor(Cursor.Pointer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Img(
            src = postModel.thumbnail,
            alt = postModel.thumbnail.contentDescription,
            attrs = Modifier
                .classNames(ResourceConstants.CSSIds.cssImgClassId)
                .borderRadius(5.px)
                .size(80.px)
                .toAttrs()
        )

        Column(modifier = Modifier.weight(1f).padding(leftRight = 5.px, topBottom = 10.px)) {
            H6 { Text(postModel.category) }

            H6 {
                Text(
                    postModel.title,
                )
            }

        }
    }
}