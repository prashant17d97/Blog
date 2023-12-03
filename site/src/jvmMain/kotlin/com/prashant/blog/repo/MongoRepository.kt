package com.prashant.blog.repo

import com.prashant.blog.model.AuthorModel
import com.prashant.blog.model.HomeContent
import com.prashant.blog.model.PostModel
import com.prashant.blog.sealeds.MongoResponse

/**
 * Interface defining MongoDB repository operations for interacting with the database.
 * Implementations of this interface should provide methods for retrieving home content,
 * author information, adding posts, and retrieving posts from the MongoDB database.
 */
interface MongoRepository {

    /**
     * Retrieves home content from the MongoDB database.
     *
     * @return A [MongoResponse] containing either successful retrieval or an error.
     */
    suspend fun getHomeContent(): MongoResponse<HomeContent>

    /**
     * Retrieves author content based on the provided [AuthorModel].
     *
     * @param author The [AuthorModel] used to query the database.
     * @return A [MongoResponse] containing either successful retrieval or an error.
     */
    suspend fun getAuthorContent(author: AuthorModel): MongoResponse<AuthorModel>

    /**
     * Retrieves author content based on the provided author ID.
     *
     * @param authorId The ID of the author to retrieve.
     * @return A [MongoResponse] containing either successful retrieval or an error.
     */
    suspend fun getAuthorContentById(authorId: String): MongoResponse<AuthorModel>

    /**
     * Adds a new post to the MongoDB database.
     *
     * @param post The [PostModel] representing the post to be added.
     * @return A [MongoResponse] containing either successful addition or an error.
     */
    suspend fun addPost(post: PostModel): MongoResponse<Boolean>

    /**
     * Retrieves a post based on the provided post ID.
     *
     * @param postId The ID of the post to retrieve.
     * @return A [MongoResponse] containing either successful retrieval or an error.
     */
    suspend fun retrievePost(postId: String): MongoResponse<PostModel>
}
