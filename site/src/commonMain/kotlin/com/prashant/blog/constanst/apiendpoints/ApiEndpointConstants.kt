package com.prashant.blog.constanst.apiendpoints

import kotlinx.serialization.json.Json

object ApiEndpointConstants {
    val json = Json { ignoreUnknownKeys = true }
    const val Author = "author"
    val GetAuthorFromId = { authorId: String -> "$Author?_id=$authorId" }
    const val GetUsers = "users"
    const val CreatePost = "createpost"
    const val POST = "post"
    val GetPostFromId = { postId: String -> "$POST?_id=$postId" }

    const val Category = "category"

    val GetCategoryFromId = { postId: String -> "$Category?_id=$postId" }

    const val Login = "login"
}