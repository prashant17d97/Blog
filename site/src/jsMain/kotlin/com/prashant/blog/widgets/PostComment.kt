package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.utils.commonfunctions.CommonFunctions.isEmailValid
import com.prashant.blog.utils.commonfunctions.CommonFunctions.timeOut
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.constants.Constants.borderRadiusMedium
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssCardId
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
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
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaXmark
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
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
    onIsReplyingChange: (Boolean) -> Unit,
    onClick: (comment: String, name: String, email: String) -> Unit
) {
    var comments by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val modifier = if (isBreakpoint) {
        Modifier.fillMaxWidth()
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
            .padding(topBottom = 40.px, leftRight = 60.px.takeIf { !isBreakpoint } ?: 20.px)
            .margin(topBottom = 20.px), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.gap(10.px).fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height((50.takeIf { isReply } ?: 30).px),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                H4(
                    attrs = Modifier.color(MaterialTheme.colorScheme.onContainer).toAttrs()
                ) { SpanText(heading) }
                if (isReply) {
                    FaXmark(
                        modifier = Modifier.onClick { onIsReplyingChange(false) },
                        size = IconSize.XL
                    )
                }
            }

            TextArea(value = comments,
                attrs = Modifier.fillMaxWidth().borderRadius(borderRadiusMedium).padding(20.px)
                    .classNames("comment").toAttrs {
                        value(comments.trim())
                        onInput {
                            comments = it.value
                        }
                        attr("placeholder", "Comments")
                    })

            if (!isUserLoggedIn) {
                Input(InputType.Text,
                    attrs = Modifier.fillMaxWidth().borderRadius(borderRadiusMedium)
                        .padding(20.px).classNames("name").toAttrs {
                            value(name)
                            onInput {
                                name = it.value
                            }
                            attr("placeholder", "Name")
                        })
                Input(InputType.Email,
                    attrs = Modifier.fillMaxWidth().borderRadius(borderRadiusMedium)
                        .padding(20.px).classNames("email").toAttrs {
                            value(email)
                            onInput {
                                email = it.value
                            }
                            attr("placeholder", "Email")
                        })
            }
            Column(modifier = Modifier.gap(20.px), horizontalAlignment = Alignment.Start) {
                if (!isReply) {
                    ButtonsWidgets.CapsuleButton(modifier = Modifier.backgroundColor(MaterialTheme.colorScheme.action),
                        onClick = {
                            validateCommentData(
                                comments.trim(),
                                name.trim(),
                                email.trim()
                            ) { message, isValid ->
                                errorMessage = if (isValid) {
                                    onClick(comments.trim(), name.trim(), email.trim())
                                    comments = ""
                                    name = ""
                                    email = ""
                                    ""
                                } else {
                                    message
                                }
                            }
                        }) {
                        SpanText("Post comment")
                    }
                } else {
                    ButtonsWidgets.OutlinedButton(outlinedColor = MaterialTheme.colorScheme.onContainer,
                        selectedOutlineColor = MaterialTheme.colorScheme.action,
                        height = 35.px,
                        onClick = {
                            validateCommentData(
                                comments.trim(),
                                name.trim(),
                                email.trim()
                            ) { message, isValid ->
                                errorMessage = if (isValid) {
                                    onClick(comments.trim(), name.trim(), email.trim())
                                    comments = ""
                                    name = ""
                                    email = ""
                                    ""
                                } else {
                                    message
                                }
                            }
                        }) {
                        SpanText("Reply")
                    }
                }

                isTyping(
                    comment = comments,
                    name = name,
                    email = email,
                    onClear = {
                        comments = ""
                        name = ""
                        email = ""
                    })
                if (errorMessage.isNotEmpty()) {
                    timeOut(1000) { errorMessage = "" }
                    SpanText(
                        errorMessage,
                        modifier = Modifier.color(MaterialTheme.colorScheme.secondary)
                    )
                }

            }
        }
    }
}

@Composable
private fun isTyping(name: String, email: String, comment: String, onClear: () -> Unit) {
    if (name.isNotEmpty() || email.isNotEmpty() || comment.isNotEmpty()) {
        H4(
            attrs = Modifier.onClick { onClear() }.cursor(Cursor.Pointer)
                .color(MaterialTheme.colorScheme.action).toAttrs()
        ) {
            SpanText("Clear")
        }
    }
}

private fun validateCommentData(
    comment: String,
    name: String,
    email: String,
    isValid: (message: String, isValid: Boolean) -> Unit
) {
    when {
        comment.isEmpty() -> isValid("Empty comment field is not allowed!", false)
        name.isEmpty() -> isValid("Empty name field is not allowed!", false)
        email.isEmpty() -> isValid("Empty email field is not allowed!", false)
        !email.isEmailValid && email.isNotEmpty() -> isValid("Invalid email!", false)
        else -> isValid("", true)
    }
}
