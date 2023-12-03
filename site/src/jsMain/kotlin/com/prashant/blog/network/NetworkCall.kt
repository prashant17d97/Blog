package com.prashant.blog.network

import com.prashant.blog.model.AuthorModel
import com.prashant.blog.model.JSApiResponse
import com.prashant.blog.model.PostModel


interface NetworkCall {
    suspend fun getAuthor(author: AuthorModel): JSApiResponse<AuthorModel>
    suspend fun getAuthorById(authorId: String): JSApiResponse<AuthorModel>
    suspend fun createPost(postModel: PostModel): JSApiResponse<String>
    suspend fun retrievePost(postId: String): JSApiResponse<PostModel>
    suspend fun getUser(): String?
}