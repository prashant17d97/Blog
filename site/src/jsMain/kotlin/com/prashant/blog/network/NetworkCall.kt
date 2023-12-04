package com.prashant.blog.network

import com.prashant.blog.model.AuthorModel
import com.prashant.blog.model.CategoryModel
import com.prashant.blog.model.JSApiResponse
import com.prashant.blog.model.PostModel


interface NetworkCall {
    suspend fun createNewAuthor(author: AuthorModel): JSApiResponse<String>
    suspend fun getAuthorById(authorId: String): JSApiResponse<AuthorModel>
    suspend fun createPost(postModel: PostModel): JSApiResponse<String>
    suspend fun retrievePost(postId: String): JSApiResponse<PostModel>

    suspend fun createCategory(category: CategoryModel): JSApiResponse<Boolean>
    suspend fun retrieveCategoryById(categoryId: String): JSApiResponse<CategoryModel>
    suspend fun retrieveCategories(): JSApiResponse<List<CategoryModel>>
}
