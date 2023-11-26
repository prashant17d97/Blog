package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.utils.constants.ResourceConstants.contentDescription
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.CSSFloat
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
    isImageInRight: Boolean = true, onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().onClick { onClick.invoke() }.margin(0.px).boxShadow(
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
                H5(attrs = Modifier.color(MaterialTheme.colorScheme.onContainer).toAttrs()) {
                    SpanText(text = "View all new")
                }
                H4 {
                    SpanText(text = "View all new")
                }
            }
            Img(
                src = ResourceConstants.FooterSocialIcons.RandomImg,
                alt = ResourceConstants.FooterSocialIcons.RandomImg.contentDescription,
                attrs = Modifier.classNames(cssImgClassId).padding(0.px).borderRadius(
                    Constants.borderRadiusLarge
                ).float(CSSFloat.Right).height(auto).maxWidth(638.px).toAttrs()
            )
        } else {
            Img(
                src = ResourceConstants.FooterSocialIcons.RandomImg,
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
                H5(attrs = Modifier.color(MaterialTheme.colorScheme.onContainer).toAttrs()) {
                    SpanText(text = "View all new")
                }
                H4 {
                    SpanText(text = "View all new")
                }
            }

        }
    }
}