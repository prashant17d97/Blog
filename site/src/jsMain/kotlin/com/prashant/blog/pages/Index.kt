package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ApiResponse
import com.model.User
import com.prashant.blog.components.COLOR_MODE_KEY
import com.prashant.blog.components.ColorScheme
import com.prashant.blog.components.ElementBuilderImplementation
import com.prashant.blog.components.composetags.ButtonsWidgets.CapsuleButton
import com.prashant.blog.components.composetags.ButtonsWidgets.GeneralButton
import com.prashant.blog.components.composetags.ButtonsWidgets.OutlinedButton
import com.prashant.blog.components.composetags.LottieAnimationPlayer
import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.TagElement

@Page
@Composable
fun HomePage() {
    var colorMode by ColorMode.currentState

    var isLoading by remember {
        mutableStateOf(true)
    }

    var users by remember {
        mutableStateOf<List<User>?>(emptyList())
    }

    LaunchedEffect(Unit) {
        delay(500)
        val result = window.api.tryGet("getusers")?.decodeToString()
        val user = Json.decodeFromString<ApiResponse>(result.toString())
        isLoading = when (user) {
            is ApiResponse.Error -> {
                false
            }

            ApiResponse.Idle -> {
                true
            }

            is ApiResponse.Success -> {
                users = user.data
                false
            }
        }

    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            LottieAnimationPlayer(
                src = "https://lottie.host/ab2d0b41-6e0e-436e-9d1c-000ee26a2f87/qE0MnnMaj1.json",
            )
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                H1 {
                    SpanText(
                        text = users?.get(0)?.name ?: "Prashant Singh"
                    )
                }
                H2 {
                    SpanText(
                        text = users?.get(1)?.name ?: "Prashant Singh",
                    )
                }
                TagElement(
                    elementBuilder = ElementBuilderImplementation("blockquote"),
                    applyAttrs = null
                ) {
                    SpanText(
                        text = users?.get(1)?.name ?: "Prashant Singh",
                    )
                }
                OutlinedButton(
                    outlinedColor = ColorScheme.JasmineYellow.rgb,
                    selectedOutlineColor = ColorScheme.SelectedItem.rgb,
                    onClick = {
                        colorMode = colorMode.opposite
                        localStorage.setItem(COLOR_MODE_KEY, colorMode.name)
                    }
                ) {
                    when (colorMode) {
                        ColorMode.LIGHT -> FaMoon()
                        ColorMode.DARK -> FaSun()
                    }
                }

                CapsuleButton(
                    onClick = {
                        colorMode = colorMode.opposite
                        localStorage.setItem(COLOR_MODE_KEY, colorMode.name)
                        console.log(colorMode.toString())
                    }
                ) {
                    SpanText(text = " Hey change it into dark or Light!  ")
                    when (colorMode) {
                        ColorMode.LIGHT -> FaMoon()
                        ColorMode.DARK -> FaSun()
                    }
                }

                GeneralButton(onClick = {
                    colorMode = colorMode.opposite
                    localStorage.setItem(COLOR_MODE_KEY, colorMode.name)
                    console.log(colorMode.toString())
                }) {
                    SpanText(text = " Hey change it into dark or Light!  ")
                    when (colorMode) {
                        ColorMode.LIGHT -> FaMoon()
                        ColorMode.DARK -> FaSun()
                    }
                }


                Div(
                    attrs = Modifier
                        .justifyContent(JustifyContent.Center)
                        .alignItems(AlignItems.Center)
                        .display(DisplayStyle.Flex)
                        .toAttrs()
                ) {
                    SpanText(text = "Prashant")
                    SpanText(text = "Singh")
                    SpanText(text = "Developer")
                }

            }
        }
    }
}


