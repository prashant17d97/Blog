package com.prashant.blog.utils.commonfunctions

object CommonFunctions {

    fun <E> Set<E>.findKey(key: String): E? {
        return this.find {
            it == key
        }
    }

       fun String.capitalize() =
        this.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }


}