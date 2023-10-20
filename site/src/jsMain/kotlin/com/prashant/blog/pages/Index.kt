package com.prashant.blog.pages

import androidx.compose.runtime.Composable
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
import org.jetbrains.compose.web.dom.H1

@Page
@Composable
fun HomePage() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            H1 {
                SpanText(
                    "Prashant Singh", modifier = Modifier
                        .fontStyle(value = FontStyle.Inherit)
                        .fontWeight(FontWeight.ExtraBold)
                        .fontSize(FontSize.Larger)
                )
            }
            H2 {
                SpanText(
                    "Prashant Singh", modifier = Modifier
                        .fontStyle(value = FontStyle.Inherit)
                        .fontWeight(FontWeight.ExtraBold)
                        .fontSize(FontSize.Larger)
                )
            }
        }
    }
}
