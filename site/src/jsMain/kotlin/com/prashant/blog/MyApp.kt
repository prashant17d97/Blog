package com.prashant.blog

import androidx.compose.runtime.Composable
import com.prashant.blog.components.COLOR_MODE_KEY
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.color
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.graphics.lightened
import com.varabyte.kobweb.compose.ui.modifiers.borderTop
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.graphics.ImageStyle
import com.varabyte.kobweb.silk.components.layout.DividerStyle
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerStyleBase
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.MutablePalette
import com.varabyte.kobweb.silk.theme.colors.palette.Palette
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
import org.jetbrains.compose.web.css.vh


val BLOCK_MARGIN = Modifier.margin(top = 1.cssRem)

private val TEXT_FONT =
    Modifier.fontFamily("Playfair Display").fontSize(18.px)
private val CODE_FONT =
    Modifier.fontFamily("Ubuntu Mono", "Roboto Mono", "Lucida Console", "Courier New", "monospace")


@InitSilk
fun initSilk(ctx: InitSilkContext) {
    ctx.apply {
        config.apply {
            initialColorMode = localStorage.getItem(COLOR_MODE_KEY)?.let { ColorMode.valueOf(it) }
                ?: ColorMode.DARK
        }

        stylesheet.apply {
            registerStyleBase("html") {
                // Always show a vertical scroller, or else our page content shifts when switching from one page that
                // can scroll to one that can't
                Modifier
                    .scrollBehavior(ScrollBehavior.Smooth)
                    .overflow { y(Overflow.Scroll) }
            }
            registerStyleBase("code") { CODE_FONT }
            registerStyleBase("canvas") { BLOCK_MARGIN }

            registerStyleBase("p") { BLOCK_MARGIN }
            registerStyleBase("pre") { BLOCK_MARGIN }
            registerStyleBase("h1") {
                TEXT_FONT
                    .fontSize(48.px)
                    .fontWeight(600)
                    .margin(bottom = 10.px)

            }

            registerStyleBase("h2") {
                TEXT_FONT
                    .fontSize(36.px)
                    .fontWeight(600)
                    .margin(bottom = 8.px)

            }

            registerStyleBase("h3") {
                TEXT_FONT
                    .fontSize(28.px)
                    .fontWeight(600)
                    .margin(bottom = 6.px)

            }

            registerStyleBase("h4") {
                TEXT_FONT
                    .fontSize(18.px)
                    .fontWeight(FontWeight.Bold)
                    .margin(bottom = 4.px)

            }

            registerStyleBase("h5") {
                TEXT_FONT
                    .fontSize(16.px)
                    .fontWeight(FontWeight.Normal)
                    .margin(bottom = 2.px)

            }

            registerStyleBase("h6") {
                TEXT_FONT
                    .fontSize(14.px)
                    .fontWeight(FontWeight.Normal)
                    .margin(bottom = 2.px)

            }

            registerStyleBase("blockquote") {
                TEXT_FONT
                    .styleModifier {
                        color("#9283E0")
                    }
                    .fontSize(24.px)
                    .fontWeight(FontWeight.Light)
                    .fontStyle(FontStyle.Italic)

            }

            registerStyleBase("body") {
                TEXT_FONT
                    .fontSize(16.px)
                    .fontWeight(FontWeight.Normal)
                    .margin(bottom = 20.px)
            }


        }

        // The "link visited" color looks a little garish with this site's theme. Disable "visited" colors for now by
        // just setting them to the same value as the default color. We might revisit this later.
        theme.palettes.apply {
            light.apply {
                color = Colors.Black.lightened(0.2f)
                background = Colors.WhiteSmoke
                border = Colors.DarkSlateGray
                link.visited = ctx.theme.palettes.light.link.default
                brand = Color.rgb(0x009900)
            }

            dark.apply {
                color = Colors.White.darkened(0.1f)
                background = Color.rgb(15, 15, 25)
                border = Colors.LightSlateGray
                link.apply {
                    val linkDark = Color.rgb(0x1a85ff)
                    default = linkDark
                    visited = linkDark
                }
                brand = Color.rgb(0x04f904)
            }
        }

        theme.replaceComponentStyleBase(ImageStyle) {
            Modifier
                .clip(Rect(8.px))
                .width(100.percent)
                .styleModifier {
                    property("object-fit", "scale-down")
                }
        }

        theme.replaceComponentStyleBase(DividerStyle) {
            Modifier
                .margin(top = 1.5.cssRem, bottom = 0.5.cssRem)
                .borderTop(1.px, LineStyle.Solid, colorMode.toPalette().border)
                .fillMaxWidth(90.percent)
        }
    }
}

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    SilkApp {
        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            content()
        }
    }
}


private const val BRAND_KEY = "brand"
val Palette.brand get() = (this as MutablePalette).brand
var MutablePalette.brand: Color
    get() = this.getValue(BRAND_KEY)
    set(value) = this.set(BRAND_KEY, value)