package com.prashant.blog.utils.css.enums

import com.prashant.blog.utils.constants.ResourceConstants

enum class EditorControl(
    val icon: String,
) {
    Bold(icon = ResourceConstants.Icons.bold),
    Italic(icon = ResourceConstants.Icons.italic),
    Link(icon = ResourceConstants.Icons.link),
    Title(icon = ResourceConstants.Icons.title),
    Subtitle(icon = ResourceConstants.Icons.subtitle),
    Quote(icon = ResourceConstants.Icons.quote),
    Code(icon = ResourceConstants.Icons.code),
    Image(icon = ResourceConstants.Icons.image)
}