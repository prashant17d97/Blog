package com.prashant.theme


import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import com.prashant.theme.Colors.unspecified
import com.prashant.theme.colorscheme.ColorScheme
import com.prashant.theme.colorscheme.darkColoScheme
import com.prashant.theme.colorscheme.lightColoScheme
import com.varabyte.kobweb.silk.theme.colors.ColorMode


private val lightColors = lightColoScheme(
    primary = Colors.primaryLight,
    secondary = Colors.secondaryLight,
    container = Colors.containerLight,
    onContainer = Colors.onContainerLight,
    action = Colors.actionLight,
    text = Colors.textLight,
    unspecified = unspecified
)
private val darkColors = darkColoScheme(
    primary = Colors.primaryDark,
    secondary = Colors.secondaryDark,
    container = Colors.containerDark,
    onContainer = Colors.onContainerDark,
    action = Colors.actionDark,
    text = Colors.textDark,
    unspecified = unspecified
)


object MaterialTheme {
    val colorScheme: ColorScheme
        @Composable
        get() {
            val colorMode by ColorMode.currentState
            return if (colorMode.isLight) {
                lightColors
            } else {
                darkColors
            }
        }


    val Boolean.colorPalette: ColorScheme
        @ReadOnlyComposable
        get() {
            return if (this) {
                lightColors
            } else {
                darkColors
            }
        }
}