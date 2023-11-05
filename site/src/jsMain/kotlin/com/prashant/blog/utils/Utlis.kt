package com.prashant.blog.utils

import com.prashant.blog.components.model.TopComment

object Utils {
    fun List<TopComment>.findLastId(): Int {
        return if (isEmpty()) {
            0
        } else {
            this[lastIndex].id
        }
    }
}