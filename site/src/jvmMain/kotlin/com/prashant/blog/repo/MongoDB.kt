package com.prashant.blog.repo

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.and
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.prashant.blog.model.Author
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

@InitApi
fun initMongoDB(context: InitApiContext) {
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    context.data.add(MongoDB(context))
}

class MongoDB(private val context: InitApiContext) : MongoRepository {
    private val client = MongoClient.create()
    private val database = client.getDatabase(DATABASE)
    private val authorCollection = database.getCollection<Author>("author")
    override fun getHomeContent() {

    }

    override suspend fun getAuthorContent(author: Author): MongoResponse<Author> {
        return try {
            context.logger.info("Database: ${database.name}\n${database.readConcern}")
            MongoResponse.Success(
                authorCollection.find(
                    and(
                        Filters.eq(Author::_id.name , author._id,),
                        Filters.eq(Author::name.name , author.name,)
                    )
                ).firstOrNull()
            )
        } catch (exception: Exception) {
            MongoResponse.Error(exception.localizedMessage)
        }
    }

    override suspend fun getAuthorContentById(authorId: String): MongoResponse<Author> {
        return try {
            MongoResponse.Success(
                authorCollection.find(Filters.eq(Author::_id.name, authorId)).first()
            )
        } catch (exception: Exception) {
            MongoResponse.Error(exception.localizedMessage)
        }
    }

    companion object {
        private const val DATABASE = "blog_database"
    }
}