package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.prashant.blog.components.composetags.BlogLayout
import com.prashant.blog.components.composetags.Widgets.Card
import com.prashant.blog.components.composetags.Widgets.CategoryViewItem
import com.prashant.blog.components.composetags.Widgets.HeadingViewAll
import com.prashant.blog.components.composetags.Widgets.HorizontalBlogCard
import com.prashant.blog.components.composetags.Widgets.NewBlogItems
import com.prashant.blog.components.composetags.Widgets.VerticalBlogCard
import com.prashant.blog.utils.constants.Constants.borderRadiusLarge
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.utils.constants.ResourceConstants.FooterSocialIcons.EssentialsCard
import com.prashant.blog.utils.constants.ResourceConstants.FooterSocialIcons.Pic
import com.prashant.blog.utils.constants.ResourceConstants.FooterSocialIcons.SuggestionOne
import com.prashant.blog.utils.constants.ResourceConstants.FooterSocialIcons.SuggestionTwo
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.Essentials
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.New
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.Popular
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.Random
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.ReadingList
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.Suggested
import com.prashant.blog.utils.constants.ResourceConstants.contentDescription
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
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
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.grid
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.layout.Divider
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    val colorMode: ColorMode by ColorMode.currentState
    BlogLayout { isBreakPoint, pageContext ->
        if (isBreakPoint) {
            SmallScreenHome(colorMode = colorMode) { }
        } else {
            LargeScreenHome(colorMode = colorMode, pageContext = pageContext) { }
        }
    }
}


@Composable
private fun SmallScreenHome(colorMode: ColorMode, onClick: () -> Unit) {
    val readingList = listOf("UI Design", "UX Design", "SEO", "Popular", "Essentials")

    Column(
        modifier = Modifier.fillMaxWidth().margin(top = 60.px),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier.classNames(cssImgClassId).width(100.percent).height(auto)
                .borderRadius(5.px), contentAlignment = Alignment.TopStart
        ) {
            Img(
                src = Pic,
                alt = Pic.contentDescription,
                attrs = Modifier.classNames(cssImgClassId)
                    .toAttrs()
            )
        }
        Column(modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px)) {
            HeadingViewAll(heading = New) {

            }
            Divider(
                modifier = Modifier.color(MaterialTheme.colorScheme.onContainer).fillMaxWidth()
                    .height(1.px)
            )

            NewBlogItems {}
        }

        //Reading list
        Row(
            modifier = Modifier.fillMaxWidth().margin(top = 60.px),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                HeadingViewAll(heading = ReadingList) {

                }

                Row(
                    modifier = Modifier.fillMaxWidth().classNames("horizontal-scrollable")
                        .scrollBehavior(ScrollBehavior.Smooth)
                        .overflow {
                            x(Overflow.Auto)
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    readingList.forEach { readingItem ->
                        CategoryViewItem(text = readingItem) {

                        }
                    }
                }
            }
        }


        //Popular list
        Column(
            modifier = Modifier.fillMaxWidth()
                .margin(top = 60.px)
                .padding(leftRight = 10.px),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            HeadingViewAll(heading = Popular) {

            }

            Img(
                src = Pic,
                alt = Pic.contentDescription,
                attrs = Modifier
                    .fillMaxWidth()
                    .maxHeight(358.px).classNames(cssImgClassId)
                    .toAttrs()
            )

            SimpleGrid(
                numColumns(base = 2),
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(4) {
                    Card(modifier = Modifier.margin(5.px).padding(10.px)) {
                        H5(
                            attrs = Modifier.color(MaterialTheme.colorScheme.onContainer)
                                .toAttrs()
                        ) {
                            SpanText(text = "View all new")
                        }
                        H4(
                            attrs = Modifier.color(MaterialTheme.colorScheme.text)
                                .toAttrs()
                        ) {
                            SpanText(text = "View all new")
                        }
                    }
                }

            }
        }

        //Random

        H2(
            attrs = Modifier.margin(top = 60.px, bottom = 20.px).padding(leftRight = 10.px)
                .toAttrs()
        ) {
            Text(value = Random)
        }
        Div(
            attrs = Modifier.margin(10.px)
                .grid { rows { size(1.fr); size(1.fr) } }
                .backgroundColor(MaterialTheme.colorScheme.container)
                .boxShadow(
                    blurRadius = 10.px,
                    color = MaterialTheme.colorScheme.unspecified.copyf(alpha = 0.5f)
                )
                .borderRadius(borderRadiusLarge)
                .display(DisplayStyle.InlineBlock)
                .toAttrs()
        ) {
            Img(
                src = ResourceConstants.FooterSocialIcons.Popular,
                alt = Popular.contentDescription,
                attrs = Modifier
                    .flexGrow(1)
                    .borderRadius(borderRadiusLarge)
                    .classNames(cssImgClassId)
                    .toAttrs()
            )
            Div(
                attrs = Modifier.flexGrow(1).padding(20.px).toAttrs(),
            ) {
                H2 {
                    Text(value = Popular)
                }
                H5(attrs = Modifier.color(MaterialTheme.colorScheme.onContainer).toAttrs()) {
                    SpanText(text = "View all new")
                }

            }

        }


        //Essentials

        Column(
            modifier = Modifier.fillMaxWidth()
                .margin(top = 60.px)
                .padding(leftRight = 10.px),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            HeadingViewAll(heading = Essentials) {

            }

            Img(
                src = EssentialsCard,
                alt = EssentialsCard.contentDescription,
                attrs = Modifier
                    .fillMaxWidth()
                    .maxHeight(358.px).classNames(cssImgClassId)
                    .toAttrs()
            )

            SimpleGrid(
                numColumns(base = 2),
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(4) {
                    Card(modifier = Modifier.margin(5.px).padding(10.px)) {
                        H5(
                            attrs = Modifier.color(MaterialTheme.colorScheme.onContainer)
                                .toAttrs()
                        ) {
                            SpanText(text = "View all new")
                        }
                        H4(
                            attrs = Modifier.color(MaterialTheme.colorScheme.text)
                                .toAttrs()
                        ) {
                            SpanText(text = "View all new")
                        }
                    }
                }

            }
        }


        //Suggested
        Row(
            modifier = Modifier.fillMaxWidth().margin(top = 60.px),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                H2 {
                    Text(value = Suggested)
                }
                SimpleGrid(numColumns(base = 1)) {
                    repeat(1) {
                        VerticalBlogCard(SuggestionOne) {}
                        VerticalBlogCard(SuggestionTwo) {}
                    }

                }
            }
        }

    }
}

@Composable
fun LargeScreenHome(colorMode: ColorMode, pageContext: PageContext, onClick: () -> Unit) {
    val readingList = listOf("UI Design", "UX Design", "SEO", "Popular", "Essentials")
    Column(modifier = Modifier.fillMaxWidth()) {

        //New
        Row(
            modifier = Modifier.fillMaxWidth().margin(top = 60.px),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier.classNames(cssImgClassId).width(100.percent).height(auto)
                    .borderRadius(5.px).margin(right = 45.px), contentAlignment = Alignment.TopStart
            ) {
                Img(
                    src = Pic, alt = Pic.contentDescription,
                    attrs = Modifier.classNames(cssImgClassId)
                        .toAttrs()
                )
            }
            Column(
                modifier = Modifier.width(50.percent).padding(right = 10.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                HeadingViewAll(heading = New) {

                }
                Divider(
                    modifier = Modifier.color(MaterialTheme.colorScheme.onContainer)
                        .fillMaxWidth()
                        .height(1.px)
                )

                NewBlogItems {}
            }
        }

        //Reading list
        Row(
            modifier = Modifier.fillMaxWidth().margin(top = 60.px),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                HeadingViewAll(heading = ReadingList) {

                }

                Row(
                    modifier = Modifier.fillMaxWidth().classNames("horizontal-scrollable")
                        .scrollBehavior(ScrollBehavior.Smooth)
                        .overflow {
                            x(Overflow.Auto)
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    readingList.forEach { readingItem ->
                        CategoryViewItem(text = readingItem) {

                        }
                    }
                }
            }
        }


        //Popular list
        Row(
            modifier = Modifier.fillMaxWidth().margin(top = 60.px),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                HeadingViewAll(heading = Popular) {

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Img(
                        src = ResourceConstants.FooterSocialIcons.Popular,
                        alt = ResourceConstants.FooterSocialIcons.Popular.contentDescription,
                        attrs = Modifier
                            .weight(1f)
                            .maxHeight(358.px).classNames(cssImgClassId)
                            .toAttrs()
                    )

                    SimpleGrid(numColumns(base = 2)) {
                        repeat(4) {
                            Card(modifier = Modifier.margin(5.px).padding(10.px)) {
                                H5(
                                    attrs = Modifier.color(MaterialTheme.colorScheme.onContainer)
                                        .toAttrs()
                                ) {
                                    SpanText(text = "View all new")
                                }
                                H4(
                                    attrs = Modifier.color(MaterialTheme.colorScheme.text)
                                        .toAttrs()
                                ) {
                                    SpanText(text = "View all new")
                                }
                            }
                        }

                    }
                }
            }
        }

        //Random
        Column(modifier = Modifier.fillMaxWidth().margin(top = 60.px).padding(10.px)) {
            H2 {
                Text(value = Random)
            }

            HorizontalBlogCard {

            }
        }

        //Essentials
        Row(
            modifier = Modifier.fillMaxWidth().margin(top = 60.px),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                HeadingViewAll(heading = Essentials) {

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    SimpleGrid(
                        numColumns(base = 2), modifier = Modifier
                            .weight(1f)
                    ) {
                        repeat(4) {
                            Card(modifier = Modifier.margin(5.px).padding(10.px)) {
                                H5(
                                    attrs = Modifier.color(MaterialTheme.colorScheme.onContainer)
                                        .toAttrs()
                                ) {
                                    SpanText(text = "View all new")
                                }
                                H4(
                                    attrs = Modifier.color(MaterialTheme.colorScheme.text)
                                        .toAttrs()
                                ) {
                                    SpanText(text = "View all new")
                                }
                            }
                        }

                    }
                    Img(
                        src = EssentialsCard,
                        alt = EssentialsCard.contentDescription,
                        attrs = Modifier
                            .weight(1f)
                            .maxHeight(358.px).classNames(cssImgClassId)
                            .toAttrs()
                    )

                }
            }
        }


        //Suggested
        Row(
            modifier = Modifier.fillMaxWidth().margin(top = 60.px),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(leftRight = 10.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                H2 {
                    Text(value = Suggested)
                }
                SimpleGrid(numColumns(base = 2), modifier = Modifier.fillMaxWidth().gap(10.px)) {
                    repeat(1) {
                        VerticalBlogCard(src = SuggestionOne) {
                            pageContext.router.navigateTo("/post")
                        }
                        VerticalBlogCard(src = SuggestionTwo) { pageContext.router.navigateTo("/post") }
                    }

                }
            }
        }

    }
}

