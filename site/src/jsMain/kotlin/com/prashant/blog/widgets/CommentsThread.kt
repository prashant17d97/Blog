package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.prashant.blog.widgets.ButtonsWidgets.OutlinedButton
import com.prashant.blog.repo.GlobalRepository
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P

@Composable
fun CommentsThread(
    repository: GlobalRepository,
    widthPercent: Int = 60,
    padding: Int = 0,
    onClick: (isReplying: Boolean) -> Unit,
    replyThread: @Composable ColumnScope.(parentCommentId: Int) -> Unit,
) {
    val comments by repository.comments.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth().padding(padding.px).width(widthPercent.percent)
            .margin(top = 60.px)
    ) {
        comments.forEachIndexed { index, comment ->
            Row(
                modifier = Modifier.fillMaxWidth().gap(10.px),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Img(
                    src = comment.userImage,
                    attrs = Modifier.size(40.px).borderRadius(50.percent)
                        .classNames(cssImgClassId).toAttrs()
                )

                P(
                    attrs = Modifier.margin(0.px).textAlign(TextAlign.Start).toAttrs()
                ) {
                    SpanText(comment.userName)
                }
                P(
                    attrs = Modifier.weight(1f).margin(0.px).textAlign(TextAlign.Start)
                        .toAttrs()
                ) {
                    SpanText(comment.commentDate)
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
                modifier = Modifier.width(100.percent).padding(left = 50.px)
            ) {
                P {
                    SpanText(comment.comment)
                }
                comment.childComments.forEach { childComment ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(left = 50.px)
                            .margin(top = 20.px).gap(10.px),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Img(
                            src = childComment.userImage,
                            attrs = Modifier.size(40.px).borderRadius(50.percent)
                                .classNames(cssImgClassId).toAttrs()
                        )
                        P(
                            attrs = Modifier.margin(0.px).textAlign(TextAlign.Start).toAttrs()
                        ) {
                            SpanText(childComment.userName)
                        }
                        P(
                            attrs = Modifier.weight(1f).margin(0.px).textAlign(TextAlign.Start)
                                .toAttrs()
                        ) { SpanText(childComment.commentDate) }
                    }
                    P(
                        attrs = Modifier.fillMaxWidth().padding(left = 100.px).toAttrs()
                    ) {
                        SpanText(childComment.comment)
                    }
                }
                replyThread.invoke(this, comment.id)

            }
        }
        Div(
            attrs = Modifier.fillMaxWidth().margin(top = 20.px)
                .backgroundColor(MaterialTheme.colorScheme.onContainer).height(1.px).toAttrs()
        )
    }
}