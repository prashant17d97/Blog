package com.prashant.blog.repo

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.prashant.blog.constanst.db.DatabaseConstants
import com.prashant.blog.constanst.db.DatabaseConstants.DATABASE
import com.prashant.blog.model.AuthorModel
import com.prashant.blog.model.CategoryModel
import com.prashant.blog.model.PostCommentRequest
import com.prashant.blog.model.PostModel
import com.prashant.blog.sealeds.MongoResponse
import com.prashant.blog.utils.ApiUtils.mongoTryCatch
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList

/**
 * Initializes MongoDB configuration and adds the MongoDB instance to the [InitApiContext] data.
 *
 * @param context The [InitApiContext] for initializing MongoDB.
 */
@InitApi
fun initMongoDB(context: InitApiContext) {
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    context.data.add(MongoDB(context))
}

/**
 * MongoDB repository class responsible for interacting with the MongoDB database.
 *
 * @param context The [InitApiContext] used for logging and other contextual operations.
 */
class MongoDB(private val context: InitApiContext) : MongoRepository {
    private val client = MongoClient.create()
    private val database = client.getDatabase(DATABASE)
    private val authorCollection =
        database.getCollection<AuthorModel>(DatabaseConstants.AuthorCollection)
    private val postCollection = database.getCollection<PostModel>(DatabaseConstants.PostCollection)
    private val categoryCollection =
        database.getCollection<CategoryModel>(DatabaseConstants.CategoryCollection)
    private val postComments =
        database.getCollection<PostCommentRequest>(DatabaseConstants.PostComments)

    /**
     * Retrieves home content from the MongoDB database.
     *
     * @return A [MongoResponse] containing either successful retrieval or an error.
     */
    override suspend fun getHomeContent(): MongoResponse<String> {
        return mongoTryCatch {
            MongoResponse.Success(
                null
            )
        }
    }

    /**
     * Retrieves author content based on the provided [AuthorModel].
     *
     * @param author The [AuthorModel] used to query the database.
     * @return A [MongoResponse] containing either successful retrieval or an error.
     */
    override suspend fun addNewAuthor(author: AuthorModel): MongoResponse<Boolean> {
        return mongoTryCatch {
            MongoResponse.Success(
                authorCollection.insertOne(author).wasAcknowledged()
            )
        }
    }

    /**
     * Retrieves author content based on the provided author ID.
     *
     * @param authorId The ID of the author to retrieve.
     * @return A [MongoResponse] containing either successful retrieval or an error.
     */
    override suspend fun getAuthorContentById(authorId: String): MongoResponse<AuthorModel> {
        return mongoTryCatch {
            MongoResponse.Success(
                authorCollection.find(Filters.eq(AuthorModel::_id.name, authorId)).first()
            )
        }
    }

    /**
     * Adds a new post to the MongoDB database.
     *
     * @param post The [PostModel] representing the post to be added.
     * @return A [MongoResponse] containing either successful addition or an error.
     */
    override suspend fun addPost(post: PostModel): MongoResponse<Boolean> {
        context.logger.info("Add post mongo response: $post")
        return mongoTryCatch {
            MongoResponse.Success(postCollection.insertOne(post).wasAcknowledged())
        }
    }

    /**
     * Retrieves a post based on the provided post ID.
     *
     * @param postId The ID of the post to retrieve.
     * @return A [MongoResponse] containing either successful retrieval or an error.
     */
    override suspend fun retrievePost(postId: String): MongoResponse<PostModel> {
        context.logger.info("retrievePost: $postId")
        return mongoTryCatch("Post not found!") {
            MongoResponse.Success(
                postCollection.find(Filters.eq(AuthorModel::_id.name, postId)).first()
            )
        }
    }

    override suspend fun createCategory(category: CategoryModel): MongoResponse<Boolean> {
        return mongoTryCatch {
            MongoResponse.Success(categoryCollection.insertOne(category).wasAcknowledged())
        }
    }

    override suspend fun retrieveCategoryById(categoryId: String): MongoResponse<CategoryModel> {
        return mongoTryCatch("Category not found") {
            MongoResponse.Success(
                categoryCollection.find(
                    Filters.eq(
                        CategoryModel::_id.name, categoryId
                    )
                ).first()
            )
        }
    }

    override suspend fun retrieveCategories(): MongoResponse<List<CategoryModel>> {
        return mongoTryCatch("Categories not found") {
            MongoResponse.Success(categoryCollection.find().toList())
        }
    }

    override suspend fun findAuthorsPosts(authorId: String): MongoResponse<List<PostModel>> {
        return mongoTryCatch("Error in fetching author's posts!") {
            MongoResponse.Success(
                postCollection.find(Filters.eq(PostModel::authorId.name, authorId)).toList()
            )
        }
    }

    override suspend fun fetchAllPost(): MongoResponse<List<PostModel>> {
        return mongoTryCatch {
            MongoResponse.Success(
                postCollection.find().sort(Sorts.descending("createdAt")).toList()
            )
        }
    }

    override suspend fun addComment(postCommentRequest: PostCommentRequest): MongoResponse<Boolean> {
        return mongoTryCatch {
            MongoResponse.Success(postComments.insertOne(postCommentRequest).wasAcknowledged())
        }
    }

    override suspend fun getComment(postId: String): MongoResponse<List<PostCommentRequest>> {
        return mongoTryCatch {
            MongoResponse.Success(
                postComments.find(
                    Filters.eq(
                        PostCommentRequest::postId.name,
                        postId
                    )
                ).toList()
            )
        }
    }

    override suspend fun updateChildComment(postCommentRequest: PostCommentRequest): MongoResponse<Boolean> {
        return mongoTryCatch {
            MongoResponse.Success(
                postComments.updateOne(
                    Filters.eq(PostCommentRequest::_id.name, postCommentRequest._id), mutableListOf(
                        Updates.set(
                            PostCommentRequest::userName.name,
                            postCommentRequest.userName
                        ),
                        Updates.set(
                            PostCommentRequest::userEmail.name,
                            postCommentRequest.userEmail
                        ),
                        Updates.set(
                            PostCommentRequest::commentDate.name,
                            postCommentRequest.commentDate
                        ),
                        Updates.set(
                            PostCommentRequest::comment.name,
                            postCommentRequest.comment
                        ),
                        Updates.set(
                            PostCommentRequest::postId.name,
                            postCommentRequest.postId
                        ),
                        Updates.set(
                            PostCommentRequest::childComments.name,
                            postCommentRequest.childComments
                        ),
                    )
                ).wasAcknowledged()
            )
        }
    }
}
