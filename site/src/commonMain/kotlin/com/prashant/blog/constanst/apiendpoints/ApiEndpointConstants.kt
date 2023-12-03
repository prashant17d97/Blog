package com.prashant.blog.constanst.apiendpoints

import kotlinx.serialization.json.Json

object ApiEndpointConstants {
    val json = Json { ignoreUnknownKeys = true }
    const val GetAuthor = "author"
    val GetAuthorFromId = { authorId: String -> "$GetAuthor?_id=$authorId" }
    const val GetUsers = "users"
    const val CreatePost = "createpost"
    const val POST = "post"
    val GetPostFromId = { postId: String -> "$POST?_id=$postId" }
}