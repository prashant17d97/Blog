package com.prashant.blog.model

import kotlinx.serialization.Serializable

@Serializable
data class PostModel(
    val _id: String = "",
    val author: String = "",
    val authorId: String = "",
    val createdAt: String = "",
    val title: String? = null,
    val subtitle: String? = null,
    val thumbnail: String? = null,
    val content: String? = null,
    val category: String? = null,
    val popular: Boolean = false,
    val main: Boolean = false,
    val sponsored: Boolean = false,
    val likes: Int = 0,
    val views: Int = 0,
) {
    val validateMembers: Pair<Boolean, String>
        get() {
            val invalidFields = mutableListOf<String>()

            // Check for non-empty or non-null values for each field
            if (author.isEmpty()) invalidFields.add("author")
            if (title.isNullOrBlank()) invalidFields.add("title")
            if (subtitle.isNullOrBlank()) invalidFields.add("subtitle")
            if (thumbnail.isNullOrBlank()) invalidFields.add("thumbnail")
            if (content.isNullOrBlank()) invalidFields.add("content")
            if (category.isNullOrBlank()) invalidFields.add("category")

            // Add additional checks if needed for other fields

            return if (invalidFields.isEmpty()) {
                Pair(true, "")
            } else {
                Pair(
                    false,
                    "${invalidFields.joinToString(", ")} are not allowed to be empty or null"
                )
            }
        }
}