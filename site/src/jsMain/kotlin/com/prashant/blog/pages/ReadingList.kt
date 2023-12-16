package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Type
import com.prashant.blog.model.CategoryModel
import com.prashant.blog.navigation.NavigationRoute
import com.prashant.blog.network.rememberNetworkCall
import com.prashant.blog.utils.commonfunctions.CommonFunctions.handleResponse
import com.prashant.blog.utils.navigation.navigateTo
import com.prashant.blog.widgets.BlogLayout
import com.prashant.blog.widgets.ReadingListItem
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.window
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P

@Page
@Composable
fun ReadingList() {

    var categories: List<CategoryModel> by remember { mutableStateOf(emptyList()) }
    var isLoading: Boolean by remember { mutableStateOf(true) }
    val networkCall = rememberNetworkCall()

    LaunchedEffect(Unit) {
        isLoading = true
        networkCall.getCategory().handleResponse(onSuccess = {
            isLoading = false
            categories = it.data
        }, onFailure = {
            isLoading = false
        })
    }

    BlogLayout(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) { isBreakPoint, pageContext ->
        H2 {
            SpanText("All Reading lists")
        }
        P {
            SpanText("Get in-depth insights on web design, freelancing, content management, and more with these series of related reads.")
        }
        SimpleGrid(
            numColumns = numColumns(1.takeIf { isBreakPoint } ?: 2),
            modifier = Modifier.gap(20.px).fillMaxWidth().margin(top = 30.px, bottom = 10.px)
        ) {
            categories.forEach { category ->
                ReadingListItem(category) {
                    pageContext.navigateTo(NavigationRoute.New.buildUrl {
                        addQueryParam(
                            Type, "latest"
                        )
                    })
                    console.info("Item clicked index: ${window.history.state}")
                }
            }
        }


    }
}