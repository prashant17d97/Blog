package com.prashant.blog

import androidx.compose.runtime.Composable
import com.prashant.blog.utils.css.CssStyleRegistration.cssStyleRegistration
import com.prashant.blog.utils.css.CssStyleRegistration.palette
import com.prashant.blog.utils.css.CssStyleRegistration.registerWidgets
import com.prashant.blog.utils.css.CssStyleRegistration.replaceExistingComponentStyle
import com.prashant.blog.utils.constants.ResourceConstants.CSSIds.cssSurfaceId
import com.prashant.blog.utils.localstorage.LocalStorageKeys.COLOR_MODE_KEY
import com.prashant.blog.utils.localstorage.rememberLocalStorage
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.css.vh


@InitSilk
fun initSilk(ctx: InitSilkContext) {
    ctx.apply {
        config.apply {
            initialColorMode =
                localStorage.getItem(COLOR_MODE_KEY)?.let { ColorMode.valueOf(it) }
                    ?: ColorMode.DARK
        }
        stylesheet.cssStyleRegistration()

        theme.palettes.apply {
            light.palette()
            dark.palette()

            light.registerWidgets()
            dark.registerWidgets()

        }

        theme.replaceExistingComponentStyle()
    }
}

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    val localStorage = rememberLocalStorage()

    localStorage.saveValue(COLOR_MODE_KEY, localStorage.defaultColorMode().name)

    SilkApp {
        Surface(
            SmoothColorStyle.toModifier().classNames(cssSurfaceId).minHeight(100.vh)
        ) {
            content()
        }
    }
}
