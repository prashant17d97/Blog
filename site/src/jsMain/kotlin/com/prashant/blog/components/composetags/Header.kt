package com.prashant.blog.components.composetags

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.prashant.blog.components.ColorScheme
import com.prashant.blog.components.constants.Constants
import com.prashant.blog.components.constants.ResourceConstants.FooterSocialIcons.SiteIcon
import com.prashant.blog.components.constants.ResourceConstants.MenuItems.menuLists
import com.prashant.blog.components.constants.ResourceConstants.contentDescription
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.translateX
import com.varabyte.kobweb.compose.ui.modifiers.visibility
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaBars
import com.varabyte.kobweb.silk.components.icons.fa.FaSearchengin
import com.varabyte.kobweb.silk.components.icons.fa.FaXmark
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.layout.Divider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text


/**
 * Composable function for rendering the header of the user interface.
 *
 * This composable displays a header with two icons, using Jetpack Compose's `Row` and `Modifier` functions.
 *
 * @see androidx.compose.foundation.layout.Row
 * @param modifier Modifier for customizing the layout of the header.
 * @param verticalAlignment The vertical alignment of the content within the header.
 * @param horizontalArrangement The horizontal arrangement of content within the header.
 */
@Composable
fun Header(isBreakPoint: Boolean, pageContext: PageContext) {
    if (isBreakPoint) {
        SmallScreenHeader()
    } else {
        LargeScreenHeader()
    }

}

/**
 * Composable function to display the header for large screens.
 */
@Composable
private fun LargeScreenHeader() {
    Row(
        modifier = Modifier.fillMaxWidth().maxWidth(Constants.MaxWidth).padding(10.px)
            .margin(top = 20.px).height(80.px),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1.5f).fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Img(
                src = SiteIcon, alt = SiteIcon.contentDescription,
                attrs = Modifier.classNames("img-fluid")
                    .size(60.px)
                    .margin(right = 20.px)
                    .borderRadius(50.percent)
                    .toAttrs()
            )
            LargeScreenMenuItems {}
        }

        Row(
            modifier = Modifier.weight(0.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) { FaSearchengin(size = IconSize.XL) }
    }
}

/**
 * Composable function to display the header for small screens.
 */
@Composable
private fun SmallScreenHeader() {
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

    Collapsed(visibility = visibility, translateCollapsedX) {
        translateCollapsedX = (-100).percent
        coroutineScope.launch {
            delay(200)
            translateX = 0.percent
            visibility = !visibility
        }
    }
    ExpandedMenu(visibility = !visibility, translateX) {
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
    onClick: () -> Unit
) {

    var translateY by remember {
        mutableStateOf(-100)
    }

    var opacity by remember {
        mutableStateOf(0f)
    }
    Row(modifier = Modifier.fillMaxWidth().padding(20.px)
        .zIndex(1)
        .position(Position.Absolute)
        .translateX(translateX)
        .transition(CSSTransition(property = "translate", duration = 300.ms))
        .backgroundColor(ColorScheme.TransparentBlack.rgb)
        .visibility(Visibility.Visible.takeIf { visibility } ?: Visibility.Hidden),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {
        FaBars(size = IconSize.XL, modifier = Modifier.onClick {
            translateY = 0
            opacity = 1f
            onClick()
        }.cursor(Cursor.Pointer))
        Img(
            src = SiteIcon, alt = SiteIcon.contentDescription,
            attrs = Modifier.classNames("img-fluid")
                .size(40.px)
                .borderRadius(50.percent)
                .margin(leftRight = 20.px)
                .toAttrs()
        )

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
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier.width(60.percent).height(75.vh)
            .visibility(Visibility.Visible.takeIf { visibility } ?: Visibility.Hidden)
            .translateX(translateX)
            .transition(CSSTransition(property = "translate", duration = 300.ms))
            .backgroundColor(ColorScheme.TransparentBlack.rgb)
            .zIndex(1)
            .position(Position.Absolute)
            .transition(
                CSSTransition(
                    property = TransitionProperty.Inherit,
                    duration = 300.ms
                )
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
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
                src = SiteIcon, alt = SiteIcon.contentDescription,
                attrs = Modifier.classNames("img-fluid")
                    .size(40.px)
                    .borderRadius(50.percent)
                    .margin(leftRight = 20.px)
                    .toAttrs()
            )

        }

        Divider(modifier = Modifier.fillMaxWidth().height(0.5.px))
        SmallScreenMenuItems {}
    }
}

/**
 * Composable function to display menu items for small screens.
 *
 * @param onClick A callback function to handle click events.
 */
@Composable
private fun SmallScreenMenuItems(onClick: () -> Unit) {
    menuLists.forEach { menu ->
        P(
            attrs = Modifier.padding(leftRight = 20.px, topBottom = 5.px)
                .onClick { onClick.invoke() }
                .classNames("menuButton").toAttrs()
        ) {
            Text(value = menu)
        }

    }
}

/**
 * Composable function to display menu items for large screens.
 *
 * @param onClick A callback function to handle click events.
 */
@Composable
private fun LargeScreenMenuItems(onClick: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        menuLists.forEach { menu ->
            P(
                attrs = Modifier.padding(leftRight = 20.px, top = 5.px)
                    .margin(0.px)
                    .onClick { onClick.invoke() }
                    .classNames("menuButton").toAttrs()
            ) {
                Text(value = menu)
            }

        }
    }
}