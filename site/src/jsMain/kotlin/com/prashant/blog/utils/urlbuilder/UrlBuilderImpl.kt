package com.prashant.blog.utils.urlbuilder
class UrlBuilderImpl(private val baseUrl: String = "/") : UrlBuilder {
    private val queryParams = mutableMapOf<String, String>()
    private val pathSegments = mutableListOf<String>()

    override fun addQueryParam(key: String, value: String): UrlBuilder {
        queryParams[key] = value
        return this
    }

    override fun addPathSegment(path: String): UrlBuilder {
        pathSegments.add(path)
        return this
    }

    fun build(): String {
        val urlBuilder = StringBuilder(baseUrl)

        if (pathSegments.isNotEmpty()) {
            urlBuilder.append("/")
            urlBuilder.append(pathSegments.joinToString("/"))
        }

        if (queryParams.isNotEmpty()) {
            urlBuilder.append("?")
            urlBuilder.append(queryParams.entries.joinToString("&") { "${it.key}=${it.value}" })
        }

        return urlBuilder.toString()
    }
}
