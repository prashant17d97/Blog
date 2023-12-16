package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.model.PostModel
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.utils.constants.ResourceConstants.contentDescription
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.CSSFloat
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.float
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.Img

@Composable
fun HorizontalBlogCard(
    postModel: PostModel,
    isImageInRight: Boolean = true,
    onPostModel: (postId: String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().onClick { onPostModel.invoke(postModel._id) }
            .margin(0.px).cursor(Cursor.Pointer).boxShadow(
                blurRadius = 10.px,
                color = MaterialTheme.colorScheme.unspecified.copyf(alpha = 0.5f)
            ).backgroundColor(MaterialTheme.colorScheme.container).borderRadius(
                Constants.borderRadiusLarge
            ), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (isImageInRight) {
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f).padding(20.px).margin(0.px),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {

                AuthorNameWithCategory(
                    author = Pair(postModel.author, postModel.authorId),
                    category = Pair(postModel.category, postModel.categoryId),
                )
                H5(
                    attrs = Modifier.cursor(Cursor.Pointer)
                        .color(MaterialTheme.colorScheme.onContainer).toAttrs()
                ) {
                    SpanText(text = postModel.title)
                }
                H4 {
                    SpanText(text = postModel.subtitle)
                }
            }
            Img(
                src = postModel.thumbnail,
                alt = ResourceConstants.FooterSocialIcons.RandomImg.contentDescription,
                attrs = Modifier.classNames(cssImgClassId).padding(0.px).borderRadius(
                    Constants.borderRadiusLarge
                ).float(CSSFloat.Right).height(auto).maxWidth(638.px).toAttrs()
            )
        } else {
            Img(
                src = postModel.thumbnail,
                alt = ResourceConstants.FooterSocialIcons.RandomImg.contentDescription,
                attrs = Modifier.classNames(cssImgClassId).padding(0.px).borderRadius(
                    Constants.borderRadiusLarge
                ).float(CSSFloat.Right).height(auto).maxWidth(638.px).toAttrs()
            )
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f).padding(20.px)
                    .margin(left = 10.px),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                AuthorNameWithCategory(
                    author = Pair(postModel.author, postModel.authorId),
                    category = Pair(postModel.category, postModel.categoryId),
                )
                H5(
                    attrs = Modifier.cursor(Cursor.Pointer)
                        .color(MaterialTheme.colorScheme.onContainer).toAttrs()
                ) {
                    SpanText(text = postModel.title)
                }
                H4 {
                    SpanText(text = postModel.subtitle)
                }
            }

        }
    }
}