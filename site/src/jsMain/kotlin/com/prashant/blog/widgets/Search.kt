package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.prashant.blog.model.PostModel
import com.prashant.blog.network.rememberNetworkCall
import com.prashant.blog.utils.commonfunctions.CommonFunctions.handleResponse
import com.prashant.blog.utils.commonfunctions.CommonFunctions.timeOut
import com.prashant.blog.utils.constants.Constants
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.icons.fa.FaXmark
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.px

@Composable
fun Search(
    searchRequest: String,
    noDataFoundCallBack: () -> Unit,
    onPostClick: (postId: String) -> Unit,
) {

    var isLoading by remember { mutableStateOf(true) }
    var postModels: List<PostModel> by remember { mutableStateOf(emptyList()) }
    val networkCall = rememberNetworkCall()
    LaunchedEffect(searchRequest) {
        delay(1000)
        networkCall.getPostsByName(searchRequest).handleResponse(
            onSuccess = {
                isLoading = false
                postModels = it.data
            },
            onFailure = {
                isLoading = false
            }
        )

    }
    Column(
        modifier = Modifier.fillMaxWidth().maxWidth(Constants.MaxWidth).gap(20.px)
            .padding(topBottom = 40.px),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (!isLoading) {
            if (postModels.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    FaXmark(
                        modifier = Modifier.onClick { noDataFoundCallBack.invoke() },
                        size = IconSize.XXL
                    )
                }
                postModels.forEach { postModel ->
                    HorizontalBlogCard(
                        postModel = postModel, isImageInRight = false, onPostModel = onPostClick
                    )
                }
            } else {
                SpanText("No data found")
                timeOut(2000) {
                    noDataFoundCallBack.invoke()
                }
            }
        } else {
            SpanText("Loading.......")
        }
    }
}