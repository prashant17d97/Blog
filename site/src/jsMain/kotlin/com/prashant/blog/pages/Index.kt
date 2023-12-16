package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Id
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Type
import com.prashant.blog.model.PostModel
import com.prashant.blog.navigation.NavigationRoute
import com.prashant.blog.network.rememberNetworkCall
import com.prashant.blog.utils.commonfunctions.CommonFunctions.handleResponse
import com.prashant.blog.utils.constants.Constants.borderRadiusLarge
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.utils.constants.ResourceConstants.FooterSocialIcons.EssentialsCard
import com.prashant.blog.utils.constants.ResourceConstants.FooterSocialIcons.Pic
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.Essentials
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.New
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.Popular
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.Random
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.ReadingList
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems.Suggested
import com.prashant.blog.utils.constants.ResourceConstants.contentDescription
import com.prashant.blog.utils.navigation.navigateTo
import com.prashant.blog.utils.navigation.navigateToWithParam
import com.prashant.blog.widgets.AuthorNameWithCategory
import com.prashant.blog.widgets.BlogLayout
import com.prashant.blog.widgets.Card
import com.prashant.blog.widgets.CategoryViewItem
import com.prashant.blog.widgets.HeadingViewAll
import com.prashant.blog.widgets.HorizontalBlogCard
import com.prashant.blog.widgets.NewBlogItems
import com.prashant.blog.widgets.VerticalBlogCard
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.layout.HorizontalDivider
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.H6
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    val networkCall = rememberNetworkCall()
    var postModels: List<PostModel> by remember { mutableStateOf(emptyList()) }
    LaunchedEffect(Unit) {
        networkCall.fetchAllPost().handleResponse(onSuccess = {
            postModels = it.data
        }) {

        }
    }
    BlogLayout { isBreakPoint, pageContext ->
        ScreenContainer(
            postModel = postModels, isBreakPoint = isBreakPoint, pageContext = pageContext
        ) { postId ->
            pageContext.navigateTo(NavigationRoute.Post.buildUrl {
                addQueryParam(Id, postId)
            })
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(topBottom = 20.px, leftRight = 10.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                H2 {
                    Text(value = Suggested)
                }
                SimpleGrid(numColumns(base = 1.takeIf { isBreakPoint } ?: 2),
                    modifier = Modifier.gap(
                        15.px
                    )) {
                    if (postModels.isNotEmpty()) {
                        postModels.forEach { post ->
                            VerticalBlogCard(post) {
                                pageContext.navigateToWithParam(
                                    NavigationRoute.Post, mapOf(Id to it)
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ScreenContainer(
    isBreakPoint: Boolean,
    pageContext: PageContext,
    postModel: List<PostModel>,
    onPostClick: (postId: String) -> Unit
) {
    if (isBreakPoint) {
        SmallScreenHome(
            postModel = postModel, pageContext = pageContext, onPostClick = onPostClick
        )
    } else {
        LargeScreenHome(
            postModel = postModel, pageContext = pageContext, onPostClick = onPostClick
        )
    }
}

@Composable
private fun SmallScreenHome(
    postModel: List<PostModel>, pageContext: PageContext, onPostClick: (postId: String) -> Unit
) {
    val readingList = listOf("UI Design", "UX Design", "SEO", "Popular", "Essentials")

    Column(
        modifier = Modifier.fillMaxWidth().margin(top = 60.px),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier.classNames(cssImgClassId).width(100.percent).height(auto)
                .borderRadius(5.px).padding(leftRight = 5.px),
            contentAlignment = Alignment.TopCenter
        ) {
            if (postModel.isNotEmpty()) {
                Img(
                    src = postModel.first().thumbnail,
                    alt = Pic.contentDescription,
                    attrs = Modifier.classNames(cssImgClassId).borderRadius(10.px).styleModifier {
                        property("aspect-ratio", "16/9")
                    }.toAttrs()
                )

                Column(
                    modifier = Modifier.fillMaxSize().padding(10.px).gap(10.px).zIndex(2),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                    AuthorNameWithCategory(
                        author = Pair(
                            postModel.first().author, postModel.first().authorId
                        ), category = Pair(
                            postModel.first().category, postModel.first().categoryId
                        ), color = Color.rgb(0xFFFFFF)
                    )
                    H2(attrs = Modifier.onClick { onPostClick(postModel.first()._id) }
                        .cursor(Cursor.Pointer).color(
                            color = Color.rgb(0xFFFFFF)
                        ).toAttrs()) {
                        SpanText(text = postModel.first().title)
                    }
                    H6(
                        attrs = Modifier.color(
                            color = Color.rgb(0xFFFFFF)
                        ).toAttrs()
                    ) {
                        SpanText(text = postModel.first().subtitle)
                    }
                }
            }
        }
        Column(modifier = Modifier.fillMaxWidth().padding(10.px)) {
            HeadingViewAll(heading = New) {
                pageContext.navigateTo(NavigationRoute.New)
            }
            HorizontalDivider(
                modifier = Modifier.color(MaterialTheme.colorScheme.onContainer).fillMaxWidth()
                    .height(1.px)
            )
            if (postModel.isNotEmpty()) {
                postModel.forEach { NewBlogItems(it, onClick = { onPostClick(it._id) }) }
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
                    pageContext.navigateTo(NavigationRoute.ReadingList)
                }

                Row(
                    modifier = Modifier.fillMaxWidth().classNames("horizontal-scrollable")
                        .scrollBehavior(ScrollBehavior.Smooth).overflow {
                            x(Overflow.Auto)
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    readingList.forEach { readingItem ->
                        CategoryViewItem(text = readingItem) {
                            pageContext.navigateToWithParam(
                                NavigationRoute.New, mapOf(Type to readingItem)
                            )
                        }
                    }
                }
            }
        }


        //Popular list
        Column(
            modifier = Modifier.fillMaxWidth().margin(top = 60.px),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            HeadingViewAll(heading = Popular) {
                pageContext.navigateToWithParam(NavigationRoute.New, mapOf(Type to Popular))
            }
            if (postModel.isNotEmpty()) {
                Img(
                    src = postModel.first().thumbnail,
                    alt = EssentialsCard.contentDescription,
                    attrs = Modifier.weight(1f).maxHeight(345.px).borderRadius(
                        borderRadiusLarge
                    ).classNames(cssImgClassId).toAttrs()
                )
                SimpleGrid(numColumns(base = 1)) {
                    repeat(4) {
                        val post = postModel[it]
                        Card(
                            modifier = Modifier.margin(topBottom = 5.px).cursor(Cursor.Pointer)
                                .onClick {
                                    onPostClick.invoke(post._id)
                                }.padding(10.px)
                        ) {
                            H5(
                                attrs = Modifier.color(MaterialTheme.colorScheme.onContainer)
                                    .toAttrs()
                            ) {
                                SpanText(text = post.title)
                            }
                            H4(
                                attrs = Modifier.color(MaterialTheme.colorScheme.text).toAttrs()
                            ) {
                                SpanText(text = post.subtitle)
                            }
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
        if (postModel.isNotEmpty()) {
            VerticalBlogCard(postModel = postModel.first()) {
                onPostClick.invoke(postModel.first()._id)
            }
        }
        //Essentials

        Column(
            modifier = Modifier.fillMaxWidth().margin(top = 60.px),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            HeadingViewAll(heading = Essentials) {
                pageContext.navigateToWithParam(NavigationRoute.New, mapOf(Type to Essentials))
            }
            if (postModel.isNotEmpty()) {
                Img(
                    src = postModel.first().thumbnail,
                    alt = EssentialsCard.contentDescription,
                    attrs = Modifier.weight(1f).maxHeight(345.px).borderRadius(
                        borderRadiusLarge
                    ).classNames(cssImgClassId).toAttrs()
                )
                SimpleGrid(
                    numColumns(base = 1), modifier = Modifier.weight(1f)
                ) {
                    repeat(4) {
                        val post = postModel[it]
                        Card(
                            modifier = Modifier.margin(topBottom = 5.px).cursor(Cursor.Pointer)
                                .onClick {
                                    onPostClick.invoke(post._id)
                                }.padding(10.px)
                        ) {
                            AuthorNameWithCategory(
                                author = Pair(post.author, post.authorId),
                                category = Pair(post.category, post.categoryId),
                            )
                            H5(
                                attrs = Modifier.color(MaterialTheme.colorScheme.onContainer)
                                    .toAttrs()
                            ) {
                                SpanText(text = post.title)
                            }
                            H4(
                                attrs = Modifier.color(MaterialTheme.colorScheme.text).toAttrs()
                            ) {
                                SpanText(text = post.subtitle)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LargeScreenHome(
    postModel: List<PostModel>,
    onPostClick: (postId: String) -> Unit,
    pageContext: PageContext,
) {
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
                    .borderRadius(5.px).margin(leftRight = 15.px),
                contentAlignment = Alignment.TopStart
            ) {
                if (postModel.isNotEmpty()) {
                    Img(
                        src = postModel.first().thumbnail,
                        alt = Pic.contentDescription,
                        attrs = Modifier.classNames(cssImgClassId).borderRadius(10.px)
                            .styleModifier {
                                property("aspect-ratio", "16/9")
                            }.toAttrs()
                    )

                    Column(
                        modifier = Modifier.fillMaxSize().padding(20.px).gap(10.px),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.Start
                    ) {
                        AuthorNameWithCategory(
                            author = Pair(
                                postModel.first().author, postModel.first().authorId
                            ), category = Pair(
                                postModel.first().category, postModel.first().categoryId
                            ), color = Color.rgb(0xFFFFFF)
                        )
                        H2(attrs = Modifier.onClick { onPostClick(postModel.first()._id) }
                            .cursor(Cursor.Pointer).color(
                                color = Color.rgb(0xFFFFFF)
                            ).toAttrs()) {
                            SpanText(text = postModel.first().title)
                        }
                        H6(
                            attrs = Modifier.color(
                                color = Color.rgb(0xFFFFFF)
                            ).toAttrs()
                        ) {
                            SpanText(text = postModel.first().subtitle)
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.width(50.percent).padding(right = 10.px, left = 20.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                HeadingViewAll(heading = New) {
                    pageContext.navigateTo(NavigationRoute.New)
                }
                HorizontalDivider(
                    modifier = Modifier.color(MaterialTheme.colorScheme.onContainer).fillMaxWidth()
                        .height(1.px)
                )
                if (postModel.isNotEmpty()) {
                    postModel.forEach { NewBlogItems(it, onClick = { onPostClick(it._id) }) }
                }
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
                    pageContext.navigateTo(NavigationRoute.ReadingList)
                }

                Row(
                    modifier = Modifier.fillMaxWidth().classNames("horizontal-scrollable")
                        .scrollBehavior(ScrollBehavior.Smooth).overflow {
                            x(Overflow.Auto)
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    readingList.forEach { readingItem ->
                        CategoryViewItem(text = readingItem) {
                            pageContext.navigateToWithParam(
                                NavigationRoute.New, mapOf(Type to readingItem)
                            )
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
                    pageContext.navigateToWithParam(NavigationRoute.New, mapOf(Type to Popular))
                }
                if (postModel.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Img(
                            src = postModel.first().thumbnail,
                            alt = EssentialsCard.contentDescription,
                            attrs = Modifier.weight(1f).maxHeight(345.px).borderRadius(
                                borderRadiusLarge
                            ).classNames(cssImgClassId).toAttrs()
                        )

                        SimpleGrid(numColumns(base = 2)) {
                            repeat(4) {
                                val post = postModel[it]
                                Card(
                                    modifier = Modifier.margin(5.px).cursor(Cursor.Pointer)
                                        .onClick {
                                            onPostClick.invoke(post._id)
                                        }.padding(10.px)
                                ) {
                                    H5(
                                        attrs = Modifier.color(MaterialTheme.colorScheme.onContainer)
                                            .toAttrs()
                                    ) {
                                        SpanText(text = post.title)
                                    }
                                    H4(
                                        attrs = Modifier.color(MaterialTheme.colorScheme.text)
                                            .toAttrs()
                                    ) {
                                        SpanText(text = post.subtitle)
                                    }
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

            if (postModel.isNotEmpty()) {
                HorizontalBlogCard(postModel = postModel.first()) {
                    onPostClick.invoke(postModel.first()._id)
                }
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
                    pageContext.navigateToWithParam(NavigationRoute.New, mapOf(Type to Essentials))
                }

                if (postModel.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        SimpleGrid(
                            numColumns(base = 2), modifier = Modifier.weight(1f)
                        ) {
                            repeat(4) {
                                val post = postModel[it]
                                Card(
                                    modifier = Modifier.margin(5.px).cursor(Cursor.Pointer)
                                        .onClick {
                                            onPostClick.invoke(post._id)
                                        }.padding(10.px)
                                ) {
                                    AuthorNameWithCategory(
                                        author = Pair(post.author, post.authorId),
                                        category = Pair(post.category, post.categoryId),
                                    )
                                    H5(
                                        attrs = Modifier.color(MaterialTheme.colorScheme.onContainer)
                                            .toAttrs()
                                    ) {
                                        SpanText(text = post.title)
                                    }
                                    H4(
                                        attrs = Modifier.color(MaterialTheme.colorScheme.text)
                                            .toAttrs()
                                    ) {
                                        SpanText(text = post.subtitle)
                                    }
                                }
                            }

                        }
                        Img(
                            src = postModel.first().thumbnail,
                            alt = EssentialsCard.contentDescription,
                            attrs = Modifier.weight(1f).maxHeight(345.px).borderRadius(
                                borderRadiusLarge
                            ).classNames(cssImgClassId).toAttrs()
                        )
                    }

                }
            }
        }
    }
}

