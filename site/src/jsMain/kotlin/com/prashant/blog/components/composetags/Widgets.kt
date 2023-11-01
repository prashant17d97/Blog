package com.prashant.blog.components.composetags

import androidx.compose.runtime.Composable
import com.prashant.blog.components.ColorScheme
import com.prashant.blog.components.ColorScheme.Black.cardColor
import com.prashant.blog.components.constants.Constants
import com.prashant.blog.components.constants.ResourceConstants
import com.prashant.blog.components.constants.ResourceConstants.CSSIds.cssCardIt
import com.prashant.blog.components.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.components.constants.ResourceConstants.contentDescription
import com.varabyte.kobweb.compose.css.CSSFloat
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
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
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.float
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
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

object Widgets {

    //Card
    @Composable
    fun Card(colorMode: ColorMode, content: @Composable ColumnScope.() -> Unit) {
        Column(
            modifier = Modifier.classNames(cssCardIt).flexGrow(1f)
                .backgroundColor(colorMode.cardColor()).boxShadow(
                    blurRadius = 10.px, color = ColorScheme.TransparentBlack.rgb
                ).borderRadius(Constants.borderRadiusLarge).margin(5.px).padding(10.px)
                .minWidth(276.px).minHeight(169.px),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
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
                attrs = Modifier.fillMaxWidth().margin(topBottom = 10.px)
                    .classNames(cssImgClassId).toAttrs()
            )
            H5(
                attrs = Modifier.color(
                    ColorScheme.PassiveText.rgb
                ).toAttrs()
            ) {
                SpanText(text = "BY   TOMAS LAURINAVICIUS   IN   RESOURCE")
            }
            H3 {
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
            P(attrs = Modifier.color(ColorScheme.PassiveText.rgb).toAttrs()) {
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
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            H3 {
                Text(value = heading)
            }
            H5(attrs = Modifier.color(ColorScheme.PassiveText.rgb).onClick { onClick.invoke() }
                .toAttrs()) {
                SpanText(text = headingSecond)
            }
        }
    }


    @Composable
    fun HorizontalBlogCard(
        colorMode: ColorMode, isImageInRight: Boolean = true, onClick: () -> Unit
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().onClick { onClick.invoke() }.margin(0.px).boxShadow(
                blurRadius = 10.px, color = ColorScheme.TransparentBlack.rgb
            ).backgroundColor(colorMode.cardColor()).borderRadius(
                Constants.borderRadiusLarge
            ),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isImageInRight) {
                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(20.px).margin(0.px),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    H5(attrs = Modifier.color(ColorScheme.PassiveText.rgb).toAttrs()) {
                        SpanText(text = "View all new")
                    }
                    H4 {
                        SpanText(text = "View all new")
                    }
                }
                Img(
                    src = ResourceConstants.FooterSocialIcons.RandomImg,
                    alt = ResourceConstants.FooterSocialIcons.RandomImg.contentDescription,
                    attrs = Modifier.classNames(cssImgClassId)
                        .padding(0.px).borderRadius(
                            Constants.borderRadiusLarge
                        ).float(CSSFloat.Right).height(auto).maxWidth(638.px).toAttrs()
                )
            } else {
                Img(
                    src = ResourceConstants.FooterSocialIcons.RandomImg,
                    alt = ResourceConstants.FooterSocialIcons.RandomImg.contentDescription,
                    attrs = Modifier.classNames(cssImgClassId)
                        .padding(0.px).borderRadius(
                            Constants.borderRadiusLarge
                        ).float(CSSFloat.Right).height(auto).maxWidth(638.px).toAttrs()
                )
                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(20.px)
                        .margin(left = 10.px),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    H5(attrs = Modifier.color(ColorScheme.PassiveText.rgb).toAttrs()) {
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
        modifier: Modifier,
        author: String,
        authorLink: String,
        category: String,
        categoryLink: String,
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            H4(
                attrs = Modifier.color(ColorScheme.PassiveText.rgb).toAttrs()
            ) {
                SpanText(text = "BY ")
            }


            A(
                href = authorLink,
            ) {
                H4 {
                    SpanText(text = author)
                }
            }
            H4(
                attrs = Modifier.color(ColorScheme.PassiveText.rgb).toAttrs()
            ) {
                SpanText(text = " IN ")
            }
            A(
                href = categoryLink,
            ) {
                H4 {
                    SpanText(text = category)
                }
            }
        }
    }

    @Composable
    fun SocialMediaIcons(modifier: Modifier = Modifier, onClick: () -> Unit) {
        Row(
            modifier = modifier.flexGrow(0.7f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ResourceConstants.FooterSocialIcons.socialMediaIcons.forEach {
                A(href = it) {
                    Img(
                        src = it,
                        alt = it.contentDescription,
                        attrs = Modifier.size(40.px)
                            .onClick { onClick.invoke() }
                            .cursor(Cursor.Pointer)
                            .classNames("Icon").padding(leftRight = 5.px).toAttrs()
                    )
                }
            }
        }
    }

    @Composable
    fun VerticalLikeView(
        modifier: Modifier = Modifier,
        onLikeClick: () -> Unit,
    ) {
        Column(modifier = modifier) {
            FaHeart(
                style = IconStyle.FILLED,
                size = IconSize.LG,
                modifier = Modifier.color(ColorScheme.Green.rgb).onClick { onLikeClick.invoke() }
                    .margin(bottom = 10.px)
            )
            P { SpanText("100 K") }
            FaEye(
                style = IconStyle.FILLED,
                size = IconSize.LG,
                modifier = Modifier
                    .margin(topBottom = 10.px)
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
            modifier = modifier,
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            FaHeart(
                style = IconStyle.FILLED,
                size = IconSize.LG,
                modifier = Modifier.color(ColorScheme.Green.rgb).onClick { onLikeClick.invoke() }
            )
            P {
                SpanText(
                    "100 K",
                    modifier = Modifier.margin(leftRight = 10.px).textAlign(TextAlign.Start)
                )
            }
            FaEye(
                style = IconStyle.FILLED,
                size = IconSize.LG,
                modifier = Modifier
            )
            P {
                SpanText(
                    "100 K",
                    modifier = Modifier.margin(leftRight = 10.px)
                )
            }
        }
    }

    @Composable
    fun PostAuthorView(
        modifier: Modifier = Modifier,
        author: String,
        authorImage: String,
        authorLink: String,
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Img(
                src = authorImage, attrs = Modifier
                    .margin(20.px)
                    .borderRadius(50.percent)
                    .size(60.px)
                    .classNames(cssImgClassId).toAttrs()
            )

            H4 {
                SpanText(author, modifier = Modifier.onClick {
                    authorLink
                }.cursor(Cursor.Pointer))
            }
            SpanText(text = "Follow me on my social handles below")
            SocialMediaIcons(modifier = Modifier.margin(10.px)) {}
        }
    }
}