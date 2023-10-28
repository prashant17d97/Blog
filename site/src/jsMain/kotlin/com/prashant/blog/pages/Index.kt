package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import com.prashant.blog.components.ColorScheme
import com.prashant.blog.components.composetags.BlogLayout
import com.prashant.blog.components.constants.ResourceConstants.FooterSocialIcons.Pic
import com.prashant.blog.components.constants.ResourceConstants.MenuItems.New
import com.prashant.blog.components.constants.ResourceConstants.contentDescription
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.Divider
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    BlogLayout { isBreakPoint, pageContext ->
        if (isBreakPoint) {
            SmallScreenHome { }
        } else {
            LargeScreenHome { }
        }
    }
}


@Composable
private fun SmallScreenHome(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().margin(top = 60.px),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier
                .classNames("img-fluid")
                .width(100.percent)
                .height(auto)
                .borderRadius(5.px),
            contentAlignment = Alignment.TopStart
        ) {
            Img(
                src = Pic,
                alt = Pic.contentDescription,
                attrs = Modifier.classNames("img-fluid")
                    .toAttrs()
            )
        }
        Column(modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                H3 {
                    Text(value = New)
                }
                H5(attrs = Modifier.color(ColorScheme.PassiveText.rgb).toAttrs()) {
                    SpanText(text = "View all new")
                }
            }
            Divider(
                modifier = Modifier.color(ColorScheme.PassiveText.rgb).fillMaxWidth().height(1.px)
            )

            P(attrs = Modifier.color(ColorScheme.PassiveText.rgb).toAttrs()) {
                SpanText(text = "SEP  04  2018")
            }

            SpanText(text = "Our 15 favorite websites from August 2018")
        }
    }
}

@Composable
fun LargeScreenHome(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().margin(top = 60.px),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .classNames("img-fluid")
                .width(100.percent)
                .height(auto)
                .borderRadius(5.px)
                .margin(right = 45.px),
            contentAlignment = Alignment.TopStart
        ) {
            Img(
                src = Pic, alt = Pic.contentDescription,
                attrs = Modifier.classNames("img-fluid")
                    .toAttrs()
            )
        }
        Column(
            modifier = Modifier
                .width(50.percent).padding(right = 10.px),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                H3 {
                    Text(value = New)
                }
                H5(attrs = Modifier.color(ColorScheme.PassiveText.rgb).toAttrs()) {
                    SpanText(text = "View all new")
                }
            }
            Divider(
                modifier = Modifier.color(ColorScheme.PassiveText.rgb).fillMaxWidth().height(1.px)
            )

            P(attrs = Modifier.color(ColorScheme.PassiveText.rgb).toAttrs()) {
                SpanText(text = "SEP  04  2018")
            }

            SpanText(text = "Our 15 favorite websites from August 2018")
        }
    }
}

