package com.prashant.blog.components.composetags

import androidx.compose.runtime.Composable
import com.prashant.blog.components.constants.Constants
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.px

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
    val isBreakPoint = rememberBreakpoint()
    val pageContext = rememberPageContext()

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
        Header(isBreakPoint = isBreakPoint < Breakpoint.MD, pageContext = pageContext)

        // Create a nested column for the content with provided parameters.
        Column(
            modifier = columnModifier.fillMaxSize()
                .scrollBehavior(ScrollBehavior.Smooth)
                .overflow { y(Overflow.Scroll) }.maxWidth(Constants.MaxWidth)
                .margin(topBottom = 60.px),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = { content(isBreakPoint < Breakpoint.MD, pageContext) }
        )

        // Display the footer based on the breakpoint.
        Footer(isBreakpointActive = isBreakPoint < Breakpoint.MD, pageContext = pageContext)
    }
}
