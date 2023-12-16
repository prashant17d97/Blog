package com.prashant.blog.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeContent(
    val _id: String,
    val newPosts: List<PostModel> = listOf(PostModel(), PostModel(), PostModel()),
//    val categories: List<CategoryModel> = listOf(CategoryModel(), CategoryModel(), CategoryModel(), CategoryModel()),
    val popularPosts: List<PostModel> = listOf(PostModel(), PostModel(), PostModel(), PostModel(), PostModel(), PostModel(), PostModel()),
    val randoMPosts: PostModel = PostModel(),
    val essentialsPosts: List<PostModel> = listOf(PostModel(), PostModel()),
    val suggestPosts: List<PostModel> = listOf(PostModel(), PostModel())
)