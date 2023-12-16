package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Id
import com.prashant.blog.navigation.NavigationRoute
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.localstorage.LocalStorageKeys.COLOR_MODE_KEY
import com.prashant.blog.utils.localstorage.rememberLocalStorage
import com.prashant.blog.utils.navigation.navigateTo
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div

/**
 * Composable function for rendering a blog layout.
 *
 * @param columnModifier The modifier to apply to the column layout.
 * @param verticalArrangement The vertical arrangement of the content within the layout.
 * @param horizontalAlignment The horizontal alignment of the content within the layout.
 * @param content A lambda function to define the content of the blog layout, which takes
 *                `isBreakPoint` and `pageContext` as parameters.
 */
@Composable
fun BlogLayout(
    columnModifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.(isBreakPoint: Boolean, pageContext: PageContext) -> Unit
) {
    // Remember the breakpoint and page context.
    val localStorage = rememberLocalStorage()
    val isBreakPoint = rememberBreakpoint()
    val pageContext = rememberPageContext()
    var searchData by remember { mutableStateOf("") }
    var colorMode by ColorMode.currentState

    // Determine horizontal alignment based on the breakpoint.
    val parentHorizontalAlignment = if (isBreakPoint < Breakpoint.MD) {
        Alignment.Start
    } else {
        Alignment.CenterHorizontally
    }

    // Create the main column layout.
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = parentHorizontalAlignment
    ) {
        // Display the header based on the breakpoint.
        Header(
            isBreakPoint = isBreakPoint < Breakpoint.MD,
            onColorMode = {
                localStorage.saveValue(
                    COLOR_MODE_KEY,
                    value = colorMode.opposite.name/*when (currentColorMode) {
                        ColorMode.DARK.name -> ColorMode.LIGHT.name
                        ColorMode.LIGHT.name -> ColorMode.DARK.name
                        else -> ColorMode.DARK.name
                    }*/
                )
                window.location.reload()
            },
            pageContext = pageContext
        ) { searchData = it }

        if (searchData.isNotEmpty()) {
            Search(searchData, noDataFoundCallBack = { searchData = "" }, onPostClick = { postId ->
                pageContext.navigateTo(NavigationRoute.Post.buildUrl {
                    addQueryParam(
                        Id, postId
                    )
                })
            })
        }

        if (isBreakPoint < Breakpoint.MD) {
            Div(
                attrs = Modifier.height(30.px).color(MaterialTheme.colorScheme.unspecified)
                    .toAttrs()
            )
        }


        // Create a nested column for the content with provided parameters.
        Column(modifier = columnModifier.fillMaxSize().scrollBehavior(ScrollBehavior.Smooth)
            .overflow { y(Overflow.Scroll) }.maxWidth(Constants.MaxWidth)
            .margin(topBottom = 75.px)
            .padding(leftRight = 15.px),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = { content(isBreakPoint < Breakpoint.MD, pageContext) })

        // Display the footer based on the breakpoint.
        Footer(isBreakpointActive = isBreakPoint < Breakpoint.MD)
    }
}
