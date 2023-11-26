package com.prashant.theme.colorscheme

import com.varabyte.kobweb.compose.ui.graphics.Color

data class ColorScheme(
    val primary: Color.Rgb = Color.rgb(0xFFFFFF),
    val secondary: Color.Rgb = Color.rgb(0xFF5480),
    val container: Color.Rgb = Color.rgb(0xF9F9F9),
    val onContainer: Color.Rgb = Color.rgb(0x9B9B9B),
    val action: Color.Rgb = Color.rgb(0x9283E0),
    val text: Color.Rgb = Color.rgb(0xFFFFFF),
    val unspecified: Color.Rgb = Color.rgba(0x00000000),
)

@OptIn(ExperimentalStdlibApi::class)
val Color.Rgb.hex: String
    get() {
        return this.value.toHexString(HexFormat.UpperCase)
    }

fun lightColoScheme(
    primary: Color.Rgb,
    secondary: Color.Rgb,
    container: Color.Rgb,
    onContainer: Color.Rgb,
    action: Color.Rgb,
    text: Color.Rgb,
    unspecified: Color.Rgb
): ColorScheme = ColorScheme(
    primary = primary,
    secondary = secondary,
    container = container,
    onContainer = onContainer,
    action = action,
    text = text,
    unspecified = unspecified
)

fun darkColoScheme(
    primary: Color.Rgb,
    secondary: Color.Rgb,
    container: Color.Rgb,
    onContainer: Color.Rgb,
    action: Color.Rgb,
    text: Color.Rgb,
    unspecified: Color.Rgb
): ColorScheme = ColorScheme(
    primary = primary,
    secondary = secondary,
    container = container,
    onContainer = onContainer,
    action = action,
    text = text,
    unspecified = unspecified
)
