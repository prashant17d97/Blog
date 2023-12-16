package com.prashant.blog.utils.urlbuilder

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.HOME

class UrlBuilderImpl(private val baseUrl: String = HOME) : UrlBuilder {
    private val queryParams = mutableMapOf<String, String>()
    private val pathSegments = mutableListOf<String>()

    override fun addQueryParam(key: String, value: String): UrlBuilder {
        queryParams[key] = value
        return this
    }

    override fun addQueryParamMap(keyValueMap:Map<String,String>): UrlBuilder {
        keyValueMap.forEach {
            queryParams[it.key] = it.value
        }
        return this
    }

    override fun addPathSegment(path: String): UrlBuilder {
        pathSegments.add(path)
        return this
    }

    fun build(): String {
        val urlBuilder = StringBuilder(baseUrl)

        if (pathSegments.isNotEmpty()) {
            urlBuilder.append(HOME)
            urlBuilder.append(pathSegments.joinToString(HOME))
        }

        if (queryParams.isNotEmpty()) {
            urlBuilder.append("?")
            urlBuilder.append(queryParams.entries.joinToString("&") { "${it.key}=${it.value}" })
        }

        return urlBuilder.toString()
    }
}
