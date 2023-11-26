package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.web.events.SyntheticMouseEvent
import com.prashant.blog.utils.constants.Constants
import com.prashant.blog.utils.constants.Constants.ButtonHeight
import com.prashant.blog.utils.constants.Constants.ButtonHorizontalPadding
import com.prashant.blog.utils.css.CssStyleRegistration
import com.prashant.theme.MaterialTheme
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.onMouseDown
import com.varabyte.kobweb.compose.ui.modifiers.onMouseLeave
import com.varabyte.kobweb.compose.ui.modifiers.onMouseOver
import com.varabyte.kobweb.compose.ui.modifiers.outlineColor
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.forms.Button
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

object ButtonsWidgets {

    @Composable
    fun OutlinedButton(
        modifier: Modifier = Modifier,
        outlinedColor: Color.Rgb,
        selectedOutlineColor: Color.Rgb,
        height: CSSNumeric = 50.px,
        type: ButtonType = ButtonType.Button,
        enabled: Boolean = true,
        onClick: (event: SyntheticMouseEvent) -> Unit,
        content: @Composable() (RowScope.() -> Unit),
    ) {
        var isClicked by remember {
            mutableStateOf(false)
        }

        var backgroundColor by remember {
            mutableStateOf(outlinedColor)
        }

        Button(
            type = type,
            enabled = enabled,
            modifier = modifier
                .height(height)
                .padding(leftRight = ButtonHorizontalPadding)
                .backgroundColor(MaterialTheme.colorScheme.unspecified)
                .outlineColor(backgroundColor)
                .onMouseLeave {
                    backgroundColor = CssStyleRegistration.colorScheme.unspecified
                }
                .onMouseOver {
                    backgroundColor = CssStyleRegistration.colorScheme.action
                }
                .onMouseDown {
                    isClicked = !isClicked
                }
                .border {
                    color(
                        if (isClicked)
                            selectedOutlineColor
                        else
                            outlinedColor
                    )
                    style(LineStyle.Solid)
                    width(2.px)
                }
                .display(DisplayStyle.InlineBlock),
            focusBorderColor = MaterialTheme.colorScheme.action,
            onClick = onClick,
            content = content
        )
    }

    @Composable
    fun CapsuleButton(
        modifier: Modifier = Modifier,
        onClick: (evt: SyntheticMouseEvent) -> Unit,
        type: ButtonType = ButtonType.Button,
        enabled: Boolean = true,
        content: @Composable() (RowScope.() -> Unit)
    ) {
        Button(
            onClick = onClick,
            type = type,
            enabled = enabled,
            modifier = modifier
                .borderRadius(Constants.CapsuleButtonRadius)
                .height(ButtonHeight),
            content = content
        )
    }

    @Composable
    fun GeneralButton(
        modifier: Modifier = Modifier,
        onClick: (evt: SyntheticMouseEvent) -> Unit,
        type: ButtonType = ButtonType.Button,
        enabled: Boolean = true,
        content: @Composable() (RowScope.() -> Unit)
    ) {
        Button(
            onClick = onClick,
            type = type,
            enabled = enabled,
            modifier = modifier
                .height(ButtonHeight)
                .display(DisplayStyle.InlineBlock),
            content = content
        )
    }
}