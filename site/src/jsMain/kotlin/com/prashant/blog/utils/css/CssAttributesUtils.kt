package com.prashant.blog.utils.css

import androidx.compose.runtime.Composable
import com.prashant.blog.model.TopComment
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.functions.brightness
import com.varabyte.kobweb.compose.css.functions.contrast
import com.varabyte.kobweb.compose.css.functions.hueRotate
import com.varabyte.kobweb.compose.css.functions.invert
import com.varabyte.kobweb.compose.css.functions.saturate
import com.varabyte.kobweb.compose.css.functions.sepia
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.filter
import com.varabyte.kobweb.compose.ui.modifiers.onMouseLeave
import com.varabyte.kobweb.compose.ui.modifiers.onMouseOver
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.styleModifier
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.percent

object CssAttributesUtils {
    fun List<TopComment>.findLastId(): Int {
        return if (isEmpty()) {
            0
        } else {
            this[lastIndex].id
        }
    }

    fun Modifier.hoverFilter(): Modifier = this.styleModifier {
        filter(invert(44.percent))
        filter(sepia(55.percent))
        filter(saturate(1571.percent))
        filter(hueRotate(314.deg))
        filter(brightness(103.percent))
        filter(contrast(102.percent))
    }

    fun Modifier.onHover(onHover: (over: Boolean) -> Unit) =
        this.onMouseLeave { onHover.invoke(false) }.onMouseOver { onHover.invoke(true) }


    @Composable
    fun Modifier.textColor(boolean: Boolean): Modifier {
        return this.color(MaterialTheme.colorScheme.action.takeIf { boolean }
            ?: MaterialTheme.colorScheme.text)
    }

    @Composable
    fun Modifier.anchorTextColor(boolean: Boolean) =
        this.color(MaterialTheme.colorScheme.secondary.takeIf { boolean }
            ?: MaterialTheme.colorScheme.action)


    fun Modifier.textDecor(boolean: Boolean) =
        this.textDecorationLine(TextDecorationLine.Underline.takeIf { boolean }
            ?: TextDecorationLine.None)
}