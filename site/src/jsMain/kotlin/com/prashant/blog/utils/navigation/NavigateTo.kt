package com.prashant.blog.utils.navigation

import com.prashant.blog.navigation.NavigationRoute
import com.varabyte.kobweb.core.PageContext

fun PageContext.navigateTo(route: NavigationRoute) {
    this.router.navigateTo(route.routeData.route)
}

fun PageContext.navigateTo(route: String) {
    this.router.navigateTo(route)
}

fun PageContext.navigateToWithParam(route: NavigationRoute, map: Map<String, String>) {
    this.router.navigateTo(route.buildUrl {
        addQueryParamMap(keyValueMap = map)
    })
}