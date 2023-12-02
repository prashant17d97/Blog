package com.prashant.blog.network

import com.prashant.blog.model.ApiResponseValue
import com.prashant.blog.model.Author


interface NetworkCall {
    suspend fun getAuthor(author: Author): String
    suspend fun getAuthorById(authorId: String): ApiResponseValue<Author>?
    suspend fun getUser(): String?
}