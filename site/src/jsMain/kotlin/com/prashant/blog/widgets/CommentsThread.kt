package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.prashant.blog.model.ChildComment
import com.prashant.blog.repo.CommentProcessor
import com.prashant.blog.utils.commonfunctions.DateTimeUtil
import com.prashant.blog.utils.commonfunctions.DateTimeUtil.parseDateString
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.widgets.ButtonsWidgets.OutlinedButton
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P

@Composable
fun CommentsThread(
    repository: CommentProcessor,
    padding: Int = 0,
    isBreakPoint: Boolean,
    isReplying: Boolean,
    onClick: (isReplying: Boolean) -> Unit,
    onReplyValueChange: (isReplying: Boolean) -> Unit,
) {

    val scope = rememberCoroutineScope()
    val postComments by repository.comments.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth().padding(padding.px)
            .margin(top = 60.px),
    ) {
        postComments.forEachIndexed { index, comment ->
            Row(
                modifier = Modifier.fillMaxWidth().gap(10.px),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Img(
                        src = comment.userImage,
                        attrs = Modifier.size(40.px).borderRadius(50.percent).margin(right = 10.px)
                            .classNames(cssImgClassId).toAttrs()
                    )

                    if (isBreakPoint) {
                        SpanText(
                            comment.userName,
                            modifier = Modifier.fontWeight(FontWeight.Bold).margin(right = 10.px)
                        )
                        SpanText(
                            comment.commentDate.parseDateString(),
                            modifier = Modifier.fontWeight(FontWeight.Thin).fontSize(10.px)
                        )
                    } else {
                        H4(
                            attrs = Modifier.margin(0.px).textAlign(TextAlign.Start).toAttrs()
                        ) {
                            SpanText(comment.userName)
                        }
                        P(
                            attrs = Modifier.weight(1f).margin(0.px).textAlign(TextAlign.Start)
                                .toAttrs()
                        ) {
                            SpanText(comment.commentDate.parseDateString())
                        }
                    }
                }
                OutlinedButton(outlinedColor = MaterialTheme.colorScheme.onContainer,
                    height = 35.px,
                    selectedOutlineColor = MaterialTheme.colorScheme.action,
                    onClick = {
                        repository.updateReplyChatWindow(index)
                        onClick.invoke(comment.isReplyingForThisThread)
                    }) {
                    SpanText("Reply")
                }
            }
            Column(
                modifier = Modifier.padding(left = 50.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                P {
                    SpanText(comment.comment)
                }
                comment.childComments.forEach { childComment ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(left = 50.px.takeIf { !isBreakPoint } ?: 0.px)
                                .margin(top = 20.px).gap(10.px),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Img(
                                src = childComment.userImage,
                                attrs = Modifier.size(40.px).borderRadius(50.percent)
                                    .classNames(cssImgClassId).toAttrs()
                            )
                            if (isBreakPoint) {
                                SpanText(
                                    comment.userName,
                                    modifier = Modifier.fontWeight(FontWeight.Bold)
                                )
                                SpanText(
                                    comment.commentDate.parseDateString(),
                                    modifier = Modifier.fontWeight(FontWeight.Thin).fontSize(10.px)
                                )
                            } else {
                                P(
                                    attrs = Modifier.margin(0.px).textAlign(TextAlign.Start).toAttrs()
                                ) {
                                    SpanText(childComment.userName)
                                }
                                P(
                                    attrs = Modifier.weight(1f).margin(0.px).textAlign(TextAlign.Start)
                                        .toAttrs()
                                ) { SpanText(childComment.commentDate.parseDateString()) }
                            }

                        }
                        P(
                            attrs = Modifier.fillMaxWidth()
                                .padding(left = 100.px.takeIf { !isBreakPoint } ?: 50.px).toAttrs()
                        ) {
                            SpanText(childComment.comment)
                        }
                    }
                }
            }
            if (isReplying && comment.isReplyingForThisThread) {
                Column(
                    modifier = Modifier.fillMaxWidth().margin(bottom = 20.px),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PostComment(
                        heading = "Leave a reply",
                        isReply = true,
                        isBreakpoint = isBreakPoint,
                        onIsReplyingChange = onReplyValueChange
                    ) { comments, name, email ->
                        if (comments.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty()) {
                            scope.launch {
                                repository.addChildComment(
                                    index, ChildComment(
                                        userName = name,
                                        userImage = ResourceConstants.FooterSocialIcons.RandomImg,
                                        userEmail = email,
                                        commentDate = DateTimeUtil.getCurrentTimestamp(),
                                        comment = comments
                                    ), id = comment._id
                                )
                            }
                            onReplyValueChange(false)
                        }
                    }
                }
            }
            if (index != postComments.lastIndex)
                Div(
                    attrs = Modifier.fillMaxWidth().margin(top = 10.px, bottom = 20.px)
                        .backgroundColor(MaterialTheme.colorScheme.onContainer).height(1.px)
                        .toAttrs()
                )
        }
        Div(
            attrs = Modifier.fillMaxWidth().margin(top = 20.px)
                .backgroundColor(MaterialTheme.colorScheme.onContainer).height(1.px).toAttrs()
        )
    }
}