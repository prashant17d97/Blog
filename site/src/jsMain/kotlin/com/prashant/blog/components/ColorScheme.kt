package com.prashant.blog.components

import com.varabyte.kobweb.compose.ui.graphics.Color


sealed class ColorScheme(val hex: String, val rgb: Color.Rgb) {

    data object PrimaryOrHover : ColorScheme(hex = "#FF5480", rgb = Color.rgb(0xFF5480))
    data object Secondary : ColorScheme(hex = "#979797", rgb = Color.rgb(0x979797))
    data object SelectedItem : ColorScheme(hex = "#9283E0", rgb = Color.rgb(0x9283E0))
    data object LightSalmonPink : ColorScheme(hex = "#FFA599", rgb = Color.rgb(0xFFA599))

    val BrinkPink = PrimaryOrHover
    data object JasmineYellow : ColorScheme(hex = "#FFD581", rgb = Color.rgb(0xFFD581))
    data object Blue : ColorScheme(hex = "#27AEFF", rgb = Color.rgb(0x27AEFF))
    data object Green : ColorScheme(hex = "#00DC90", rgb = Color.rgb(0x00DC90))
    data object LightBG : ColorScheme(hex = "#F9F9Fb", rgb = Color.rgb(0xF9F9Fb))
    data object NightBG : ColorScheme(hex = "#001f2a", rgb = Color.rgb(0x001f2a))

    data object LightText : ColorScheme(hex = "#000000", rgb = Color.rgb(0x000000))
    data object NightText : ColorScheme(hex = "#FFFFFF", rgb = Color.rgb(0xFFFFFF))
    data object Transparent : ColorScheme(hex = "#00000000", rgb = Color.rgba(a = 0, r = 0, g = 0, b = 0))

    data object PassiveText : ColorScheme(hex = "#9B9B9B", rgb = Color.rgb(0x9B9B9B))

    val quoteText: ColorScheme = SelectedItem
}

