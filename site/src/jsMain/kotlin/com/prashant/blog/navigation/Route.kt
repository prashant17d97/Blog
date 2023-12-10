package com.prashant.blog.navigation

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.PopularPost
import com.prashant.blog.utils.constants.ResourceConstants.MenuItems
import com.prashant.blog.utils.urlbuilder.UrlBuilder
import com.prashant.blog.utils.urlbuilder.UrlBuilderImpl

sealed class NavigationRoute(val name: String, val routeData: Route) {
    data object Popular : NavigationRoute(
        name = MenuItems.Popular, routeData = Route(
            route = PopularPost(MenuItems.Popular),
            action = RouteAction.Navigate
        )
    )

    data object New : NavigationRoute(
        name = MenuItems.New,
        routeData = Route(
            route = "/${MenuItems.New.lowercase()}",
            action = RouteAction.Navigate
        )
    )

    data object Post : NavigationRoute(
        name = MenuItems.Post,
        routeData = Route(
            route = "/${MenuItems.Post.lowercase()}",
            action = RouteAction.Navigate
        )
    )

    data object Author : NavigationRoute(
        name = MenuItems.Author,
        routeData = Route(
            route = "/${MenuItems.Author.lowercase()}",
            action = RouteAction.Navigate
        )
    )

    data object ReadingList : NavigationRoute(
        name = MenuItems.ReadingList, routeData = Route(
            route = "/${MenuItems.ReadingList.replace(" ", "").lowercase()}",
            action = RouteAction.Navigate
        )
    )

    data object Essentials : NavigationRoute(
        name = MenuItems.Essentials, routeData = Route(
            route = "/${MenuItems.Essentials.lowercase()}",
            action = RouteAction.PromptAction
        )
    )

    data object Suggested : NavigationRoute(
        name = MenuItems.Suggested, routeData = Route(
            route = "/${MenuItems.Suggested.lowercase()}",
            action = RouteAction.PromptAction
        )
    )

    data object Topics : NavigationRoute(
        name = MenuItems.Topics,
        routeData = Route(
            route = "/${MenuItems.Topics.lowercase()}",
            action = RouteAction.PromptAction
        )
    )

    data object Subscribe : NavigationRoute(
        name = MenuItems.Subscribe, routeData = Route(
            route = "/${MenuItems.Subscribe.lowercase()}",
            action = RouteAction.PromptAction
        )
    )

    data object Random : NavigationRoute(
        name = MenuItems.Random,
        routeData = Route(
            route = "/${MenuItems.Random.lowercase()}",
            action = RouteAction.Navigate
        )
    )

    data object DarkMode : NavigationRoute(
        name = MenuItems.DarkMode, routeData = Route(
            route = "/${MenuItems.DarkMode.lowercase()}",
            action = RouteAction.PromptAction
        )
    )

    fun buildUrl(urlBuilder: UrlBuilder.() -> UrlBuilder): String {
        val builder = UrlBuilderImpl(this.routeData.route)
        urlBuilder(builder)
        return builder.build()
    }
}

val menuLists = listOf(
    NavigationRoute.Popular,
    NavigationRoute.New,
    NavigationRoute.ReadingList,
    NavigationRoute.Topics,
    NavigationRoute.Subscribe,
    NavigationRoute.DarkMode
)

data class Route(
    val route: String, val action: RouteAction
)

enum class RouteAction {
    Navigate, PromptAction
}

