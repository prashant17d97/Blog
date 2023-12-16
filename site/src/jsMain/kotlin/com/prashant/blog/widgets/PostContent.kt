package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.model.PostModel
import com.prashant.blog.utils.commonfunctions.CommonFunctions.timeOut
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Code
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Pre
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

@Composable
fun PostContent(
    modifier: Modifier = Modifier, postContent: PostModel
) {
    LaunchedEffect(postContent) {
        (document.getElementById(ResourceConstants.CSSIds.postContent) as HTMLDivElement).innerHTML =
            postContent.content.replace("<pre>","<pre style=\"background-color: #010101; border: 1px solid #ddd; border-radius: 4px; overflow-x: auto; padding: 20px; margin: 0px;\">")
    }
    Column(
        modifier = Modifier.padding(bottom = 50.px, leftRight = 5.percent).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier.margin(bottom = 40.px).fillMaxWidth()
                .classNames(ResourceConstants.CSSIds.cssImgClassId).objectFit(ObjectFit.Cover)
                .styleModifier {
                    property("aspect-ratio", "16/9")
                },
            src = postContent.thumbnail,
        )
        Div(
            attrs = modifier.id(ResourceConstants.CSSIds.postContent).fillMaxWidth().toAttrs()
        )

        var icon by remember { mutableStateOf("copy") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .classNames("code-block")
                .borderRadius(10.px)
                .backgroundColor(Color.rgb(0x0000000))
                .color(MaterialTheme.colorScheme.text),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Pre(
                attrs = Modifier
                    .margin(10.px)
                    .toAttrs()
            ) {
                Code {
                    Text(
                        """// MainActivity.kt
@Composable

fun LocationPermissionDialog(onDismiss: () -> Unit) {
// ... (code snippets above)
}"""
                    )
                }
            }

            Div(
                attrs = Modifier
                    .classNames("copy-button")
                    .size(45.px)
                    .display(DisplayStyle.Flex)
                    .justifyContent(JustifyContent.Center)
                    .alignItems(AlignItems.Center)
                    .backgroundColor(MaterialTheme.colorScheme.container)
                    .margin(10.px)
                    .padding(10.px)
                    .borderRadius(10.px)
                    .onClick {
                        if (icon=="copy"){
                            val copyButton = document.querySelector(".copy-button")
                            val codeBlock = document.querySelector(".code-block pre")

                            copyButton?.addEventListener("click", {
                                codeBlock?.textContent?.let { it1 ->
                                    window.navigator.clipboard.writeText(
                                        it1
                                    )
                                }
                                icon = "clipboard-check"

                                timeOut(1000){
                                    icon = "copy"
                                }
                            })
                        }
                    }.padding(10.px).cursor(Cursor.Pointer).toAttrs(),
            ) {
                FaCustomIcon(
                    iconName = icon
                )
            }
        }

    }
}