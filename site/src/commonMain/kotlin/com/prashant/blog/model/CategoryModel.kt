package com.prashant.blog.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    @SerialName("_id")
    val _id: String = "",
    val thumbnail: String,
    val category: String,
    val description: String,
) {
    val validateMembers: Pair<Boolean, String>
        get() {
            val invalidFields = mutableListOf<String>()

            // Check for non-empty or non-null values for each field
            if (thumbnail.isBlank()) invalidFields.add("thumbnail")
            if (category.isBlank()) invalidFields.add("category")

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