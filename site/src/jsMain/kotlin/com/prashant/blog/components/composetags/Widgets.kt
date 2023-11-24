package com.prashant.blog.components.composetags

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.components.composetags.ButtonsWidgets.CapsuleButton
import com.prashant.blog.components.composetags.ButtonsWidgets.OutlinedButton
import com.prashant.blog.repo.GlobalRepository
import com.prashant.blog.utils.CssAttributesUtils.anchorTextColor
import com.prashant.blog.utils.CssAttributesUtils.hoverFilter
import com.prashant.blog.utils.CssAttributesUtils.onHover
import com.prashant.blog.utils.CssAttributesUtils.textColor
import com.prashant.blog.utils.CssAttributesUtils.textDecor
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.constants.Constants.borderRadiusMedium
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssCardId
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssIconId
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.utils.constants.ResourceConstants.contentDescription
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.CSSFloat
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.float
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaEye
import com.varabyte.kobweb.silk.components.icons.fa.FaHeart
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.icons.fa.IconStyle
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.TextArea

object Widgets {

    //Card
    @Composable
    fun Card(
        modifier: Modifier = Modifier,
        verticalArrangement: Arrangement.Vertical = Arrangement.SpaceEvenly,
        horizontalAlignment: Alignment.Horizontal = Alignment.Start,
        content: @Composable (ColumnScope.() -> Unit)
    ) {
        Column(
            modifier = modifier.classNames(cssCardId).flexGrow(1f)
                .backgroundColor(MaterialTheme.colorScheme.container).boxShadow(
                    blurRadius = 10.px, color = MaterialTheme.colorScheme.primary
                ).borderRadius(Constants.borderRadiusLarge)
                .minWidth(276.px).minHeight(169.px),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = content
        )
    }

    @Composable
    fun VerticalBlogCard(src: String, onClick: () -> Unit) {
        Column(
            modifier = Modifier.fillMaxWidth().onClick { onClick.invoke() },
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Img(
                src = src,
                alt = src.contentDescription,
                attrs = Modifier.fillMaxWidth().margin(topBottom = 10.px).classNames(cssImgClassId)
                    /*.maxWidth(775.px)
                    .maxHeight(550.px)*/
                    .toAttrs()
            )
            H5(
                attrs = Modifier.color(
                    MaterialTheme.colorScheme.onContainer
                ).toAttrs()
            ) {
                AuthorNameWithCategory(
                    author = "TOMAS LAURINAVICIUS",
                    authorLink = "/author",
                    category = "RESOURCE",
                    categoryLink = ""
                )
            }
            H3(attrs = Modifier.cursor(Cursor.Pointer).toAttrs()) {
                SpanText(text = "Website Downtime: Applicable Tips on How to Prevent It")
            }

            P {
                SpanText("User research is the reality check every project needs. Here’s our guide to why you should be doing it — and how to get started.")
            }

        }
    }

    @Composable
    fun NewBlogItems(onClick: () -> Unit) {
        Column(modifier = Modifier.fillMaxWidth().onClick { onClick.invoke() }) {
            P(attrs = Modifier.color(MaterialTheme.colorScheme.onContainer).toAttrs()) {
                SpanText(text = "SEP  04  2018")
            }

            SpanText(text = "Our 15 favorite websites from August 2018")
        }
    }

    @Composable
    fun HeadingViewAll(
        modifier: Modifier = Modifier,
        heading: String,
        headingSecond: String = "View all new",
        onClick: () -> Unit,
    ) {
        var onMouseHover by remember { mutableStateOf(false) }
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            H4 {
                Text(value = heading)
            }
            H5(attrs = Modifier
                .onClick { onClick.invoke() }
                .textColor(onMouseHover)
                .onHover(onHover = { onMouseHover = it })
                .textDecor(onMouseHover)
                .cursor(Cursor.Pointer).toAttrs()
            ) {
                SpanText(text = headingSecond)
            }
        }
    }


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


    @Composable
    fun CategoryViewItem(text: String, onClick: () -> Unit) {
        Column(
            modifier = Modifier.width(Constants.HomeReadingListItemWidth)
                .onClick { onClick.invoke() },
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Img(
                src = ResourceConstants.FooterSocialIcons.ReadingCard,
                attrs = Modifier.classNames(cssImgClassId)
                    .minWidth(Constants.HomeReadingListImgWidth)
                    .minHeight(Constants.HomeReadingListImgHeight)
                    .maxWidth(Constants.HomeReadingListImgWidth)
                    .maxHeight(Constants.HomeReadingListImgHeight)
                    .borderRadius(Constants.borderRadiusLarge).margin(bottom = 10.px).toAttrs()
            )
            H3 {
                SpanText(text = text)
            }
        }
    }

    @Composable
    fun AuthorNameWithCategory(
        modifier: Modifier = Modifier,
        author: String,
        authorLink: String = "/author",
        category: String,
        categoryLink: String,
    ) {
        var onAnchorMouseHover by remember { mutableStateOf(false) }
        var onAnchorCategoryMouseHover by remember { mutableStateOf(false) }

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            H6(
                attrs = Modifier.color(MaterialTheme.colorScheme.text).toAttrs()
            ) {
                SpanText(text = "By ")
            }


            A(
                href = authorLink,
                attrs = Modifier.anchorTextColor(onAnchorMouseHover)
                    .onHover(onHover = { onAnchorMouseHover = it })
                    .textDecor(onAnchorMouseHover).toAttrs()
            ) {
                H6 {
                    SpanText(text = author)
                }
            }
            H6(
                attrs = Modifier.color(MaterialTheme.colorScheme.text).hoverFilter().toAttrs()
            ) {
                SpanText(text = " In ")
            }
            A(
                href = categoryLink,
                attrs = Modifier.anchorTextColor(onAnchorCategoryMouseHover)
                    .onHover(onHover = { onAnchorCategoryMouseHover = it })
                    .textDecor(onAnchorCategoryMouseHover).toAttrs()
            ) {
                H6 {
                    SpanText(text = category)
                }
            }
        }
    }

    @Composable
    fun SocialMediaIcons(modifier: Modifier = Modifier, onClick: () -> Unit) {
        var visited by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = modifier.flexGrow(0.7f).gap(10.px),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ResourceConstants.FooterSocialIcons.socialMediaIcons.forEach { icon ->
                A(href = icon) {
                    Img(src = icon,
                        alt = icon.contentDescription,
                        attrs = Modifier.size(40.px).onClick { onClick.invoke() }
                            .cursor(Cursor.Pointer).classNames(cssIconId).toAttrs())
                }
            }
        }
    }

    @Composable
    fun VerticalLikeView(
        modifier: Modifier = Modifier,
        onLikeClick: () -> Unit,
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FaHeart(
                style = IconStyle.FILLED,
                size = IconSize.LG,
                modifier = Modifier.color(MaterialTheme.colorScheme.onContainer)
                    .classNames(cssIconId)
                    .onClick { onLikeClick.invoke() }.margin(bottom = 10.px)
            )
            P { SpanText("100 K") }
            FaEye(
                style = IconStyle.FILLED,
                size = IconSize.LG,
                modifier = Modifier.classNames(cssIconId).margin(topBottom = 10.px)
            )
            P { SpanText("100 K") }
        }
    }

    @Composable
    fun HorizontalLikeView(
        modifier: Modifier = Modifier,
        onLikeClick: () -> Unit,
    ) {
        Row(
            modifier = modifier.alignItems(AlignItems.Center).gap(10.px),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            FaHeart(style = IconStyle.FILLED,
                size = IconSize.LG,
                modifier = Modifier.color(MaterialTheme.colorScheme.onContainer)
                    .onClick { onLikeClick.invoke() })
            P(
                attrs = Modifier.margin(0.px).textAlign(TextAlign.Center).toAttrs()
            ) {
                SpanText(
                    "100 K"
                )
            }
            FaEye(
                style = IconStyle.FILLED, size = IconSize.LG, modifier = Modifier.margin(0.px)
            )
            P(
                attrs = Modifier.margin(0.px).textAlign(TextAlign.Center).toAttrs()
            ) {
                SpanText(
                    "100 K",
                )
            }
        }
    }

    @Composable
    fun PostAuthorView(
        modifier: Modifier = Modifier,
        author: String,
        noActionPerformed: Boolean = true,
        authorImage: String,
        authorLink: String="/author",
    ) {
        Column(
            modifier = modifier.fillMaxWidth().padding(10.px),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Img(
                src = authorImage,
                attrs = Modifier.margin(20.px).borderRadius(50.percent).size(60.px)
                    .classNames(cssImgClassId).toAttrs()
            )

            if (noActionPerformed) {
                Link(path = authorLink) {
                    H3(attrs = Modifier.color(MaterialTheme.colorScheme.action).toAttrs()) {
                        Text(author)
                    }
                }
            } else {
                H3(attrs = Modifier.color(MaterialTheme.colorScheme.secondary).toAttrs()) {
                    Text(author)
                }
            }
            P {
                SpanText(text = "Follow me on my social handles below")
            }
            SocialMediaIcons(modifier = Modifier.margin(10.px)) {}
        }
    }

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

}