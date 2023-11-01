package com.prashant.blog.components.composetags

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.prashant.blog.components.ColorScheme
import com.prashant.blog.components.composetags.Widgets.SocialMediaIcons
import com.prashant.blog.components.constants.Constants
import com.prashant.blog.components.constants.ResourceConstants
import com.prashant.blog.components.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.components.constants.ResourceConstants.contentDescription
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.Img

/**
 * Composable function to display the footer based on the breakpoint.
 *
 * @param isBreakpointActive A boolean indicating whether the current screen is at a breakpoint.
 * @param pageContext The context of the page.
 */
@Composable
fun Footer(
    isBreakpointActive: Boolean,
    pageContext: PageContext
) {
    val colorMode by ColorMode.currentState

    if (isBreakpointActive) {
        SmallerScreenFooter(colorMode = colorMode)
    } else {
        LargerScreenFooter(colorMode = colorMode)
    }
}

/**
 * Composable function to display the footer for smaller screens.
 *
 * @param colorMode The color mode for the footer, indicating light or dark theme.
 */
@Composable
private fun SmallerScreenFooter(colorMode: ColorMode) {
    Column(modifier = Modifier.fillMaxWidth().padding(20.px)
        .backgroundColor(ColorScheme.White.rgb.takeIf { colorMode.isLight }
            ?: ColorScheme.Black.rgb),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.px),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ResourceConstants.FooterSocialIcons.socialMediaIcons.forEach {
                Img(
                    src = it,
                    alt = it.contentDescription,
                    attrs = Modifier.size(40.px)
                        .classNames(cssImgClassId)
                        .padding(leftRight = 5.px).toAttrs()
                )
            }
        }
        H6 {
            SpanText(
                text = "All Rights Reserved 2023 layers.", modifier = Modifier.textAlign(
                    TextAlign.End
                )
            )
        }

    }
}

/**
 * Composable function to display the footer for larger screens.
 *
 * @param colorMode The color mode for the footer, indicating light or dark theme.
 */
@Composable
private fun LargerScreenFooter(colorMode: ColorMode) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.px)
        .backgroundColor(ColorScheme.White.rgb.takeIf { colorMode.isLight }
            ?: ColorScheme.Black.rgb),
        contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.px)
                .maxWidth(Constants.MaxWidth)
                .height(100.px),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            A(href = "http://localhost:8080/"){
                Img(
                    src = ResourceConstants.FooterSocialIcons.SiteIcon,
                    alt = ResourceConstants.FooterSocialIcons.SiteIcon.contentDescription,
                    attrs = Modifier.classNames(cssImgClassId)
                        .size(80.px)
                        .borderRadius(50.percent)
                        .toAttrs()
                )
            }
            SocialMediaIcons {}
            H6 {
                SpanText(
                    text = "All Rights Reserved 2023 layers", modifier = Modifier
                        .textAlign(
                            TextAlign.End
                        )
                )
            }
        }
    }
}