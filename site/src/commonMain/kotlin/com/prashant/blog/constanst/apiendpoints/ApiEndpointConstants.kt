package com.prashant.blog.constanst.apiendpoints

import kotlinx.serialization.json.Json

object ApiEndpointConstants {
    val json = Json { ignoreUnknownKeys = true }
    const val Author = "author"
    val GetAuthorFromId = { authorId: String -> "$Author?$Id=$authorId" }
    val GetAuthorsPost =
        { authorId: String, date: String? -> "$Author?$Id=$authorId&$AuthorPost=true&$DateParam=$date" }
    const val GetUsers = "users"
    const val CreatePost = "createpost"
    const val POST = "post"
    const val PopularPOST = "popularpost"
    const val AddComment = "addcomment"
    const val GetComment = "comments"
    val GetCommentByPostId = { postId: String -> "$GetComment?$Id=$postId" }
    const val UpdateComment = "updatecomment"
    val GetPostFromId = { postId: String -> "$POST?$Id=$postId" }

    const val Category = "category"

    val GetCategoryFromId = { postId: String -> "$Category?$Id=$postId" }

    const val Login = "login"
    private const val ReadingList = "readinglist"

    const val HOME = "/"

    //Queries Param
    const val Id = "_id"
    const val DateParam = "date"
    const val AuthorPost = "posts"
    const val Type = "type"
    const val Popular = "Popular"

    val PostType = { postType: String -> "$POST?$Type=$postType" }
    val PopularPost = { postType: String -> "new?$Type=$postType" }

    val authorLink = { authorId: String -> "/$Author?$Id=$authorId" }
    val categoryLink = { categoryId: String -> "/$ReadingList?$Id=$categoryId" }
}