package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.widgets.ButtonsWidgets.CapsuleButton
import com.prashant.blog.widgets.ButtonsWidgets.OutlinedButton
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.constants.Constants.borderRadiusMedium
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssCardId
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.TextArea

@Composable
fun PostComment(
    heading: String = "Leave a comment",
    isReply: Boolean = false,
    isUserLoggedIn: Boolean = false,
    padding: Int = 0,
    isBreakpoint: Boolean = false,
    onClick: (comment: String, name: String, email: String) -> Unit
) {
    var comments by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val modifier = if (isBreakpoint) {
        Modifier.width(97.percent).margin(
            top = 60.px, leftRight = 10.px, bottom = 20.px
        )
    } else {
        Modifier.width(60.percent).margin(
            top = 60.px
        )
    }

    Box(
        modifier = modifier
            .backgroundColor(MaterialTheme.colorScheme.container)
            .classNames(cssCardId)
            .boxShadow(
                blurRadius = 10.px,
                color = MaterialTheme.colorScheme.unspecified.copyf(alpha = 0.5f)
            ).borderRadius(Constants.borderRadiusLarge).padding(padding.px)
            .padding(topBottom = 40.px, leftRight = 60.px), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.gap(10.px).fillMaxWidth()
        ) {
            H4(
                attrs = Modifier.color(MaterialTheme.colorScheme.onContainer).toAttrs()
            ) { SpanText(heading) }

            TextArea(value = comments,
                attrs = Modifier.fillMaxWidth().borderRadius(borderRadiusMedium).padding(20.px)
                    .classNames("comment").toAttrs {
                        value(comments.trim())
                        onInput {
                            comments = it.value.trim()
                        }
                        attr("placeholder", "Comments")
                    })

            if (!isUserLoggedIn) {
                Input(InputType.Text,
                    attrs = Modifier.fillMaxWidth().borderRadius(borderRadiusMedium)
                        .padding(20.px).classNames("name").toAttrs {
                            value(name)
                            onInput {
                                name = it.value.trim()
                            }
                            attr("placeholder", "Name")
                        })
                Input(InputType.Email,
                    attrs = Modifier.fillMaxWidth().borderRadius(borderRadiusMedium)
                        .padding(20.px).classNames("email").toAttrs {
                            value(email)
                            onInput {
                                email = it.value.trim()
                            }
                            attr("placeholder", "Email")
                        })
            }
            if (!isReply) {
                CapsuleButton(modifier = Modifier.backgroundColor(MaterialTheme.colorScheme.action),
                    onClick = {
                        onClick(comments.trim(), name.trim(), email.trim())
                        comments = ""
                        name = ""
                        email = ""

                    }) {
                    SpanText("Post comment")
                }
            } else {
                OutlinedButton(outlinedColor = MaterialTheme.colorScheme.onContainer,
                    selectedOutlineColor = MaterialTheme.colorScheme.action,
                    height = 35.px,
                    onClick = {
                        onClick(comments.trim(), name.trim(), email.trim())
                        comments = ""
                        name = ""
                        email = ""
                    }) {
                    SpanText("Reply")
                }
            }
        }
    }

}