package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.HOME
import com.prashant.blog.navigation.NavigationRoute
import com.prashant.blog.navigation.RouteAction.Navigate
import com.prashant.blog.navigation.RouteAction.PromptAction
import com.prashant.blog.navigation.menuLists
import com.prashant.blog.utils.commonfunctions.CommonFunctions.gainFocus
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssImgClassId
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssInputId
import com.prashant.blog.utils.constants.ResourceConstants.FooterSocialIcons.SiteIcon
import com.prashant.blog.utils.constants.ResourceConstants.contentDescription
import com.prashant.blog.utils.navigation.navigateTo
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.css.transitionTimingFunction
import com.varabyte.kobweb.compose.dom.clearFocus
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.onKeyDown
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.translateX
import com.varabyte.kobweb.compose.ui.modifiers.visibility
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaBars
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import com.varabyte.kobweb.silk.components.icons.fa.FaXmark
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.icons.fa.IconStyle
import com.varabyte.kobweb.silk.components.layout.HorizontalDivider
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text


@Composable
fun Header(
    isBreakPoint: Boolean,
    pageContext: PageContext,
    onColorMode: () -> Unit,
    onSearch: (searchString: String) -> Unit,
) {

    HeaderContainer(isBreakPoint = isBreakPoint, onSearch = onSearch, onLogoClick = {
        pageContext.router.navigateTo(
            HOME
        )
    }) { navigationRoute ->
        val route = navigationRoute.routeData
        if (navigationRoute == NavigationRoute.DarkMode) {
            onColorMode()
        } else {
            when (route.action) {
                Navigate -> pageContext.navigateTo(route.route)
                PromptAction -> {

                }
            }
        }
    }
}

@Composable
private fun HeaderContainer(
    isBreakPoint: Boolean,
    onSearch: (searchString: String) -> Unit,
    onLogoClick: () -> Unit,
    onMenuItemClick: (menu: NavigationRoute) -> Unit,
) {
    if (isBreakPoint) {
        SmallScreenHeader(onMenuItemClick = onMenuItemClick, onLogoClick = onLogoClick)
    } else {
        LargeScreenHeader(
            onMenuItemClick = onMenuItemClick,
            onSearch = onSearch
        )
    }
}

/**
 * Composable function to display the header for large screens.
 */
@Composable
private fun LargeScreenHeader(
    onMenuItemClick: (menu: NavigationRoute) -> Unit,
    onSearch: (searchString: String) -> Unit
) {
    var isSearching by remember {
        mutableStateOf(false)
    }

    var searching by remember {
        mutableStateOf("")
    }

    var animatedValue by remember {
        mutableStateOf(3)
    }

    var darkMode by ColorMode.currentState
    val transParent = MaterialTheme.colorScheme.unspecified
    Row(
        modifier = Modifier.fillMaxWidth().maxWidth(Constants.MaxWidth).margin(top = 20.px)
            .padding(leftRight = 10.px),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Link(path = HOME) {
                Img(
                    src = SiteIcon,
                    alt = SiteIcon.contentDescription,
                    attrs = Modifier.classNames(cssImgClassId).size(60.px).borderRadius(50.percent)
                        .toAttrs()
                )
            }

            LargeScreenMenuItems(onMenuClick = onMenuItemClick)
        }

        Row(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Box(
                modifier = Modifier.margin(left = 10.px, right = 25.px).padding(15.px)
                    .height(80.percent)
                    .width(animatedValue.em).borderRadius(40.px).styleModifier {
                        transitionTimingFunction(AnimationTimingFunction.Linear)
                    }.transition(CSSTransition(property = "width", duration = 300.ms))
                    .backgroundColor(MaterialTheme.colorScheme.container.copyf(alpha = 0.8f))
                    .boxShadow(
                        offsetX = 0.px,
                        offsetY = 0.px,
                        blurRadius = 10.px,
                        spreadRadius = 4.px,
                        color = MaterialTheme.colorScheme.action.copyf(alpha = 0.25f),
                        inset = false
                    ), contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ) {
                    if (!isSearching) {
                        FaCustomIcon(
                            modifier = Modifier.visibility(!isSearching).onClick {
                                isSearching = true
                                animatedValue = 25
                                document.getElementById(cssInputId)?.gainFocus()
                            }, iconName = "magnifying-glass", size = "lg"
                        )
                    } else {
                        Input(InputType.Search,
                            attrs = Modifier.fillMaxWidth()
                                .backgroundColor(MaterialTheme.colorScheme.unspecified)
                                .color(MaterialTheme.colorScheme.text).margin(right = 10.px)
                                .padding(0.px).id(cssInputId).onKeyDown {
                                    if (it.key == "Enter") {
                                        onSearch(searching)
                                        searching = ""
                                        document.getElementById(cssInputId)?.clearFocus()
                                    }
                                }.border {
                                    color(transParent)
                                }.weight(1f).toAttrs {
                                    value(searching)
                                    onInput {
                                        searching = it.value
                                    }
                                    attr("placeholder", "Search")
                                })

                        FaCustomIcon(modifier = Modifier.visibility(Visibility.Visible.takeIf { isSearching }
                            ?: Visibility.Hidden).onClick {
                            isSearching = false
                            animatedValue = 3
                        }, iconName = "arrow-right")
                    }
                }
            }
            if (darkMode.isLight) {
                FaMoon(
                    size = IconSize.XXL,
                    style = IconStyle.FILLED,
                    modifier = Modifier.boxShadow(
                        offsetX = 0.px,
                        offsetY = 0.px,
                        blurRadius = 10.px,
                        spreadRadius = 4.px,
                        color = MaterialTheme.colorScheme.action.copyf(alpha = 0.25f),
                        inset = false
                    ).onClick {
                        onMenuItemClick.invoke(NavigationRoute.DarkMode)
                    })
            } else {
                FaSun(
                    size = IconSize.XXL,
                    style = IconStyle.FILLED,
                    modifier = Modifier.boxShadow(
                        offsetX = 0.px,
                        offsetY = 0.px,
                        blurRadius = 10.px,
                        spreadRadius = 4.px,
                        color = MaterialTheme.colorScheme.action.copyf(alpha = 0.25f),
                        inset = false
                    ).onClick {
                        onMenuItemClick.invoke(NavigationRoute.DarkMode)

                    })
            }
        }
    }
}

/**
 * Composable function to display the header for small screens.
 */
@Composable
private fun SmallScreenHeader(
    onMenuItemClick: (menu: NavigationRoute) -> Unit,
    onLogoClick: () -> Unit,
) {
    var visibility by remember {
        mutableStateOf(true)
    }

    var translateX by remember {
        mutableStateOf((-100).percent)
    }

    var translateCollapsedX by remember {
        mutableStateOf(0.percent)
    }

    val coroutineScope = rememberCoroutineScope()

    Collapsed(visibility = visibility, translateCollapsedX, onLogoClick = onLogoClick) {
        translateCollapsedX = (-100).percent
        coroutineScope.launch {
            delay(200)
            translateX = 0.percent
            visibility = !visibility
        }
    }
    ExpandedMenu(
        visibility = !visibility,
        translateX,
        onMenuItemClick = onMenuItemClick,
        onLogoClick = onLogoClick
    ) {
        translateX = (-100).percent
        coroutineScope.launch {
            delay(200)
            visibility = !visibility
            translateCollapsedX = 0.percent
        }

    }
}

/**
 * Composable function to display the collapsed menu.
 *
 * @param visibility A boolean indicating the visibility of the collapsed menu.
 * @param translateX The translation on the X-axis for the menu.
 * @param onClick A callback function to handle click events.
 */
@Composable
private fun Collapsed(
    visibility: Boolean,
    translateX: CSSSizeValue<CSSUnit.percent>,
    onLogoClick: () -> Unit,
    onClick: () -> Unit,
) {

    var translateY by remember {
        mutableStateOf(-100)
    }

    var opacity by remember {
        mutableStateOf(0f)
    }
    Row(modifier = Modifier.fillMaxWidth().padding(20.px).zIndex(1).position(Position.Absolute)
        .translateX(translateX).transition(CSSTransition(property = "translate", duration = 300.ms))
        .backgroundColor(MaterialTheme.colorScheme.onContainer.copyf(alpha = 0.8f))
        .visibility(Visibility.Visible.takeIf { visibility } ?: Visibility.Hidden),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {
        FaBars(size = IconSize.XL, modifier = Modifier.onClick {
            translateY = 0
            opacity = 1f
            onClick()
        }.cursor(Cursor.Pointer))
        Img(
            src = SiteIcon,
            alt = SiteIcon.contentDescription,
            attrs = Modifier.classNames(cssImgClassId).size(40.px).borderRadius(50.percent)
                .onClick { onLogoClick() }.margin(leftRight = 10.px).toAttrs()
        )
        H1(
            attrs = Modifier.onClick { onLogoClick() }.fontWeight(FontWeight.Bold).toAttrs()
        ) { SpanText("Debug Desk") }

    }
}

/**
 * Composable function to display the expanded menu.
 *
 * @param visibility A boolean indicating the visibility of the expanded menu.
 * @param translateX The translation on the X-axis for the menu.
 * @param onClick A callback function to handle click events.
 */
@Composable
fun ExpandedMenu(
    visibility: Boolean,
    translateX: CSSSizeValue<CSSUnit.percent>,
    onMenuItemClick: (menu: NavigationRoute) -> Unit,
    onLogoClick: () -> Unit,
    onClick: () -> Unit,
) {

    Column(modifier = Modifier.width(60.percent).height(100.vh)
        .visibility(Visibility.Visible.takeIf { visibility } ?: Visibility.Hidden)
        .translateX(translateX).transition(CSSTransition(property = "translate", duration = 300.ms))
        .backgroundColor(MaterialTheme.colorScheme.onContainer).zIndex(5).position(Position.Absolute)
        .transition(
            CSSTransition(
                property = TransitionProperty.Inherit, duration = 300.ms
            )
        ), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.px, leftRight = 20.px),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FaXmark(
                size = IconSize.XL, modifier = Modifier.onClick {
                    onClick()
                }.cursor(Cursor.Pointer)
            )
            Img(
                src = SiteIcon,
                alt = SiteIcon.contentDescription,
                attrs = Modifier.classNames(cssImgClassId).size(40.px).borderRadius(50.percent)
                    .onClick { onLogoClick() }.toAttrs()
            )

        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth().height(1.px))
        SmallScreenMenuItems(onClick = onMenuItemClick)
    }
}

/**
 * Composable function to display menu items for small screens.
 *
 * @param onClick A callback function to handle click events.
 */
@Composable
private fun SmallScreenMenuItems(onClick: (menu: NavigationRoute) -> Unit) {
    menuLists.forEach { menu ->
        P(
            attrs = Modifier.padding(leftRight = 20.px, topBottom = 5.px)
                .onClick { onClick.invoke(menu) }.classNames("menuButton").toAttrs()
        ) {
            Text(value = menu.name)
        }

    }
}

/**
 * Composable function to display menu items for large screens.
 *
 * @param onMenuClick A callback function to handle click events.
 */
@Composable
private fun LargeScreenMenuItems(onMenuClick: (menu: NavigationRoute) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (menu in menuLists) {
            if (menu != NavigationRoute.DarkMode) {
                P(
                    attrs = Modifier.padding(leftRight = 20.px, top = 5.px).margin(leftRight = 5.px)
                        .onClick { onMenuClick.invoke(menu) }.classNames("menuButton")
                        .cursor(Cursor.Pointer).toAttrs()
                ) {
                    Text(value = menu.name)
                }
            }

        }
    }
}