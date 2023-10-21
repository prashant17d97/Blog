package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ApiResponse
import com.model.User
import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.compose.css.FontSize
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H4

@Page
@Composable
fun HomePage() {
    var isLoading by remember {
        mutableStateOf(true)
    }

    var users by remember {
        mutableStateOf<List<User>?>(emptyList())
    }

    LaunchedEffect(Unit) {
        delay(5000)
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
            H4 {
                SpanText(
                    text = "Loading! Please Wait....",
                    modifier = Modifier
                        .fontStyle(value = FontStyle.Inherit)
                        .fontWeight(FontWeight.ExtraBold)
                        .fontSize(FontSize.Larger)
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                H1 {
                    SpanText(
                        text = users?.get(0)?.name ?: "Prashant Singh", modifier = Modifier
                            .fontStyle(value = FontStyle.Inherit)
                            .fontWeight(FontWeight.ExtraBold)
                            .fontSize(FontSize.Larger)
                    )
                }
                H2 {
                    SpanText(
                        text = users?.get(1)?.name ?: "Prashant Singh", modifier = Modifier
                            .fontStyle(value = FontStyle.Inherit)
                            .fontWeight(FontWeight.ExtraBold)
                            .fontSize(FontSize.Larger)
                    )
                }
            }
        }
    }
}


