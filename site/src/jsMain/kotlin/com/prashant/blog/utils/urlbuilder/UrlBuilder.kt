package com.prashant.blog.utils.urlbuilder

interface UrlBuilder {
    fun addQueryParam(key: String, value: String): UrlBuilder
    fun addPathSegment(path: String): UrlBuilder
}