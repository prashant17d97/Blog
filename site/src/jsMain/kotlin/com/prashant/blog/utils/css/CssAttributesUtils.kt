package com.prashant.blog.utils.css

import androidx.compose.runtime.Composable
import com.prashant.blog.model.TopComment
import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.css.enums.ControlStyle
import com.prashant.blog.utils.css.enums.EditorControl
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.functions.brightness
import com.varabyte.kobweb.compose.css.functions.contrast
import com.varabyte.kobweb.compose.css.functions.hueRotate
import com.varabyte.kobweb.compose.css.functions.invert
import com.varabyte.kobweb.compose.css.functions.saturate
import com.varabyte.kobweb.compose.css.functions.sepia
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.filter
import com.varabyte.kobweb.compose.ui.modifiers.onMouseLeave
import com.varabyte.kobweb.compose.ui.modifiers.onMouseOver
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.styleModifier
import kotlinx.browser.document
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.w3c.dom.HTMLTextAreaElement
import kotlin.js.Date

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

    fun Modifier.noBorder(): Modifier {
        return this.border(
            width = 0.px,
            style = LineStyle.None,
            color = Colors.Transparent
        ).outline(
            width = 0.px,
            style = LineStyle.None,
            color = Colors.Transparent
        )
    }

    fun getEditor() =
        document.getElementById(ResourceConstants.CSSIds.editor) as HTMLTextAreaElement

    fun getSelectedIntRange(): IntRange? {
        val editor = getEditor()
        val start = editor.selectionStart
        val end = editor.selectionEnd
        return if (start != null && end != null) {
            IntRange(start, (end - 1))
        } else null
    }

    fun getSelectedText(): String? {
        val range = getSelectedIntRange()
        return if (range != null) {
            getEditor().value.substring(range)
        } else null
    }

    fun applyStyle(controlStyle: ControlStyle) {
        val selectedText = getSelectedText()
        val selectedIntRange = getSelectedIntRange()
        if (selectedIntRange != null && selectedText != null) {
            getEditor().value = getEditor().value.replaceRange(
                range = selectedIntRange,
                replacement = controlStyle.style
            )
            document.getElementById(ResourceConstants.CSSIds.editorPreview)?.innerHTML =
                getEditor().value
        }
    }

    fun applyControlStyle(
        editorControl: EditorControl,
        onLinkClick: () -> Unit,
        onImageClick: () -> Unit
    ) {
        when (editorControl) {
            EditorControl.Bold -> {
                applyStyle(
                    ControlStyle.Bold(
                        selectedText = getSelectedText()
                    )
                )
            }

            EditorControl.Italic -> {
                applyStyle(
                    ControlStyle.Italic(
                        selectedText = getSelectedText()
                    )
                )
            }

            EditorControl.Link -> {
                onLinkClick()
            }

            EditorControl.Title -> {
                applyStyle(
                    ControlStyle.Title(
                        selectedText = getSelectedText()
                    )
                )
            }

            EditorControl.Subtitle -> {
                applyStyle(
                    ControlStyle.Subtitle(
                        selectedText = getSelectedText()
                    )
                )
            }

            EditorControl.Quote -> {
                applyStyle(
                    ControlStyle.Quote(
                        selectedText = getSelectedText()
                    )
                )
            }

            EditorControl.Code -> {
                applyStyle(
                    ControlStyle.Code(
                        selectedText = getSelectedText()
                    )
                )
            }

            EditorControl.Image -> {
                onImageClick()
            }
        }
    }

    fun Long.parseDateString() = Date(this).toLocaleDateString()

    fun parseSwitchText(posts: List<String>): String {
        return if (posts.size == 1) "1 Post Selected" else "${posts.size} Posts Selected"
    }

    fun validateEmail(email: String): Boolean {
        val regex = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)"
        return regex.toRegex().matches(email)
    }
}