package com.prashant.blog.utils.css

import com.prashant.blog.utils.constants.ResourceConstants
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssFocusAttribute
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssHoverAttribute
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssInputId
import com.prashant.blog.utils.localstorage.LocalStorageKeys.COLOR_MODE_KEY
import com.prashant.theme.MaterialTheme.colorPalette
import com.prashant.theme.colorscheme.hex
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.color
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.borderTop
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.graphics.ImageStyle
import com.varabyte.kobweb.silk.components.layout.DividerStyle
import com.varabyte.kobweb.silk.components.style.StyleModifiers
import com.varabyte.kobweb.silk.init.SilkStylesheet
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.theme.MutableSilkTheme
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.MutablePalette
import com.varabyte.kobweb.silk.theme.colors.palette.Palette
import com.varabyte.kobweb.silk.theme.colors.palette.SilkWidgetColorGroups
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.border
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.link
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobweb.silk.theme.replaceComponentStyleBase
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.clip
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

object CssStyleRegistration {


    //Member Variables
    private const val BRAND_KEY = "blog"
    private var MutablePalette.brand: Color
        get() = this.getValue(BRAND_KEY)
        set(value) = this.set(BRAND_KEY, value)

    private val colorMode =
        localStorage.getItem(COLOR_MODE_KEY)?.let { ColorMode.valueOf(it) } ?: ColorMode.DARK
    val Palette.brand get() = (this as MutablePalette).brand

    val colorScheme = colorMode.isLight.colorPalette

    private val TEXT_FONT =
        Modifier.fontFamily("Playfair Display").color(colorScheme.text)


    //Member Extension Functions
    private fun StyleModifiers.hover(createModifier: () -> Modifier) {
        this.cssRule(cssHoverAttribute) {
            createModifier.invoke()
        }
    }


    private val BLOCK_MARGIN = Modifier.margin(top = 1.cssRem)

    private val StyleModifiers.TextHover: Unit
        get() = hover {
            Modifier.styleModifier {
                color(
                    colorScheme.action.hex
                )
            }
        }


    fun SilkStylesheet.cssStyleRegistration() {
        registerStyleBase("body") { Modifier.classNames("surface") }
        registerStyleBase("canvas") { BLOCK_MARGIN }
        registerStyleBase("pre") { BLOCK_MARGIN }
        registerStyle(cssSelector = "h1",
            extraModifiers = TEXT_FONT.fontSize(48.px).fontWeight(600).margin(bottom = 10.px),
            init = { })

        registerStyle(cssSelector = "h2",
            extraModifiers = TEXT_FONT.fontSize(36.px).fontWeight(600).margin(bottom = 8.px),
            init = { })

        registerStyle(
            cssSelector = ".menuButton", extraModifiers = Modifier
        ) {
            base {
                Modifier
            }
            hover {
                Modifier.height(35.px).backgroundColor(colorScheme.action).borderRadius(10.px)
            }
        }

        registerStyle(
            cssSelector = "#clickableText", extraModifiers = Modifier
        ) {
            base {
                Modifier
                    .border(2.px, style = LineStyle.Solid, color = colorScheme.action)
                    .borderRadius(r = 30.px)
                    .padding(15.px)
                    .color(colorScheme.action)
            }
            hover {
                Modifier
                    .border(2.px, style = LineStyle.Solid, color = colorScheme.secondary)
                    .borderRadius(r = 30.px)
                    .padding(15.px)
                    .color(colorScheme.secondary)
            }
        }

        registerStyleBase(cssSelector = "h3",
            extraModifiers = Modifier,
            init = { TEXT_FONT.fontSize(28.px).fontWeight(FontWeight.Bold)
                .margin(bottom = 6.px) })

        registerStyle(cssInputId) {
            cssRule(suffix = cssFocusAttribute) {
                Modifier.outline(0.px).border {
                    color(colorScheme.unspecified)
                }
            }
        }

        registerStyleBase(
            cssSelector = "h4"
        ) {
            TEXT_FONT.fontSize(18.px).fontWeight(FontWeight.Bold)
                .margin(bottom = 2.px)
        }
        registerStyleBase(
            cssSelector =".${ResourceConstants.CSSIds.cssCardId}"
        ) {
            Modifier.color(colorScheme.text)
        }

        registerStyle(cssSelector = "h5",
            extraModifiers = TEXT_FONT.fontSize(16.px).fontWeight(FontWeight.Normal)
                .margin(bottom = 2.px),
            init = { })

        registerStyle(cssSelector = "h6",
            extraModifiers = TEXT_FONT.fontSize(14.px).fontWeight(FontWeight.Normal)
                .margin(bottom = 2.px),
            init = { })

        registerStyle(cssSelector = "span",
            extraModifiers = TEXT_FONT.fontSize(16.px).fontWeight(FontWeight.Medium)
                .margin(bottom = 2.px),
            init = { })

        registerStyleBase("blockquote") {
            TEXT_FONT.styleModifier {
                color("#9283E0")
            }.fontSize(24.px).fontWeight(FontWeight.Light).fontStyle(FontStyle.Italic)

        }

        registerStyleBase(
            cssSelector = "body",
        ) {
            TEXT_FONT.fontSize(16.px).fontWeight(FontWeight.Normal).margin(bottom = 20.px)
        }
        registerStyleBase(cssSelector = "p") {
            TEXT_FONT.fontSize(16.px).fontWeight(FontWeight.Normal)
        }

    }

    fun MutablePalette.palette() {
        color = colorScheme.text
        background = colorScheme.primary
        border = Colors.DarkSlateGray
        link.apply {
            default = colorScheme.secondary
            visited = colorScheme.action
        }
        brand = colorScheme.primary
    }

    fun MutablePalette.registerWidgets() {
        SilkWidgetColorGroups.MutableButton(this).set(
            default = colorScheme.secondary,
            hover = colorScheme.action,
            focus = colorScheme.secondary,
            pressed = colorScheme.secondary,
        )

    }

    fun MutableSilkTheme.replaceExistingComponentStyle() {
        replaceComponentStyleBase(ImageStyle) {
            Modifier.clip(Rect(8.px)).width(100.percent).styleModifier {
                property("object-fit", "scale-down")
            }
        }

        replaceComponentStyleBase(DividerStyle) {
            Modifier.margin(top = 1.5.cssRem, bottom = 0.5.cssRem)
                .borderTop(1.px, LineStyle.Solid, colorMode.toPalette().border)
                .fillMaxWidth(90.percent)
        }
    }
}