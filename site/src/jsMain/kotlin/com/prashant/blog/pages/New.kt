package com.prashant.blog.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Category
import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Type
import com.prashant.blog.model.CategoryModel
import com.prashant.blog.model.PostModel
import com.prashant.blog.navigation.NavigationRoute
import com.prashant.blog.network.rememberNetworkCall
import com.prashant.blog.utils.commonfunctions.CommonFunctions.capitalize
import com.prashant.blog.utils.commonfunctions.CommonFunctions.handleResponse
import com.prashant.blog.utils.navigation.navigateToWithParam
import com.prashant.blog.widgets.BlogLayout
import com.prashant.blog.widgets.VerticalBlogCard
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H5
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun New() {
    var count by remember { mutableStateOf(1) }
    var postType: String? by remember { mutableStateOf(null) }
    var categoryId: String? by remember { mutableStateOf(null) }
    var category: CategoryModel? by remember { mutableStateOf(null) }

    val networkCall = rememberNetworkCall()
    var postModels: List<PostModel> by remember { mutableStateOf(emptyList()) }
    LaunchedEffect(postType) {
        if (postType?.isEmpty() == true && categoryId?.isEmpty() == true) {
            networkCall.fetchAllPost().handleResponse(onSuccess = {
                postModels = it.data
            }) {

            }
        } else if (postType?.isNotEmpty() == true) {
            networkCall.fetchAllPost(postType).handleResponse(onSuccess = {
                postModels = it.data
            }) {

            }
        }

        categoryId?.let {
            networkCall.getCategoryById(it).handleResponse(
                onSuccess = { cat ->
                    category = cat.data
                },
                onFailure = { }
            )
        }
    }

    LaunchedEffect(Unit) {
        if (postType == null && categoryId == null) {
            networkCall.fetchAllPost().handleResponse(onSuccess = {
                postModels = it.data
            }) {

            }
        }
    }

    LaunchedEffect(category) {
        if (category != null) {
            networkCall.fetchAllPost(postType).handleResponse(onSuccess = {
                postModels = it.data
            }) {

            }
        }
    }
    BlogLayout(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) { isBreakPoint, pageContext ->

        val type = pageContext.route.params[Type]
        categoryId = pageContext.route.params[Category]
        postType = type?.capitalize()

        H2 {
            SpanText(postType ?: category?.category ?: "New")
        }
        P {
            SpanText(
                category?.description
                    ?: "Our latest web design tips, tricks, insights, and resources, hot off the presses."
            )

        }
        SimpleGrid(numColumns(base = 1.takeIf { isBreakPoint } ?: 2),
            modifier = Modifier.fillMaxWidth().padding(top = 30.px).gap(10.px)) {
            if (postModels.isNotEmpty()) {
                postModels.forEach { post ->
                    VerticalBlogCard(post) {
                        pageContext.navigateToWithParam(
                            NavigationRoute.Post,
                            mapOf(ApiEndpointConstants.Id to it)
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.id("loadMore").fillMaxWidth().margin(top = 60.px),
            horizontalArrangement = Arrangement.Center
        ) {
            H5(attrs = Modifier.id("clickableText").textAlign(TextAlign.Center).onClick {
                count += 1
            }.cursor(Cursor.Pointer).toAttrs()) {
                Text(value = "Load more...")
            }

        }
    }
}

