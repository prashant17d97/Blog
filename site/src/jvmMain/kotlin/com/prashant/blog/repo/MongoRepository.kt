package com.prashant.blog.repo

import com.prashant.blog.model.Author

interface MongoRepository {

    fun getHomeContent()
    suspend fun getAuthorContent(author: Author): MongoResponse<Author>
    suspend fun getAuthorContentById(authorId: String): MongoResponse<Author>
}