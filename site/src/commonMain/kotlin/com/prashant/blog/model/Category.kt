package com.prashant.blog.model

import com.prashant.blog.enums.Theme
import kotlinx.serialization.Serializable


interface CategoryCommon {
    val color: String
}
@Serializable
enum class Category(override val color: String): CategoryCommon {
    Technology(color = Theme.Green.hex),
    Programming(color = Theme.Yellow.hex),
    Design(color = Theme.Purple.hex)
}