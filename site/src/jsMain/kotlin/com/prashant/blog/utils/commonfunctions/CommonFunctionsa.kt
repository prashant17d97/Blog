package com.prashant.blog.utils.commonfunctions

import kotlinx.serialization.json.Json.Default.decodeFromString

object CommonFunctions {

    fun <E> Set<E>.findKey(key: String): E? {
        return this.find {
            it == key
        }
    }

    fun String.capitalize() =
        this.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

    inline fun <reified T> String?.parseData(): T? {
        return this?.let { decodeFromString(it) }
    }

}