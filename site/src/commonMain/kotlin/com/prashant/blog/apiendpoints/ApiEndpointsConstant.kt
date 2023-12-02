package com.prashant.blog.apiendpoints

import kotlinx.serialization.json.Json

object ApiEndpointsConstant {

    val json = Json { ignoreUnknownKeys = true }
    const val GetAuthor = "getauthor"
    const val GetAuthorById = "getauthorbyid"
    val GetAuthorFromId = { authorId: String -> "$GetAuthorById?_id=$authorId" }
    const val GetUsers = "getusers"
}