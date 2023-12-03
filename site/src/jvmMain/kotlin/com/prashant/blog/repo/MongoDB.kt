package com.prashant.blog.repo

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.and
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.prashant.blog.constanst.db.DatabaseConstants
import com.prashant.blog.constanst.db.DatabaseConstants.DATABASE
import com.prashant.blog.model.AuthorModel
import com.prashant.blog.model.HomeContent
import com.prashant.blog.model.PostModel
import com.prashant.blog.sealeds.MongoResponse
import com.prashant.blog.utils.ApiUtils.mongoTryCatch
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

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
    private val postCollection =
        database.getCollection<PostModel>(DatabaseConstants.PostCollection)

    /**
     * Retrieves home content from the MongoDB database.
     *
     * @return A [MongoResponse] containing either successful retrieval or an error.
     */
    override suspend fun getHomeContent(): MongoResponse<HomeContent> {
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
    override suspend fun getAuthorContent(author: AuthorModel): MongoResponse<AuthorModel> {
        return mongoTryCatch {
            MongoResponse.Success(
                authorCollection.find(
                    and(
                        Filters.eq(AuthorModel::_id.name, author._id),
                        Filters.eq(AuthorModel::name.name, author.name)
                    )
                ).firstOrNull()
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
        return mongoTryCatch {
            MongoResponse.Success(
                postCollection.find(Filters.eq(AuthorModel::_id.name, postId)).first()
            )
        }
    }
}
