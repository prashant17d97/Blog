package com.prashant.blog.navigation

import com.prashant.blog.utils.constants.ResourceConstants.MenuItems

sealed class NavigationRoute(val name: String, val route: Route) {
    data object Popular : NavigationRoute(
        name = MenuItems.Popular, route = Route(
            route = "/${MenuItems.Popular.lowercase()}",
            action = RouteAction.PromptAction
        )
    )

    data object New : NavigationRoute(
        name = MenuItems.New,
        route = Route(
            route = "/${MenuItems.New.lowercase()}",
            action = RouteAction.Navigate
        )
    )

    data object ReadingList : NavigationRoute(
        name = MenuItems.ReadingList, route = Route(
            route = "/${MenuItems.ReadingList.replace(" ", "").lowercase()}",
            action = RouteAction.Navigate
        )
    )

    data object Essentials : NavigationRoute(
        name = MenuItems.Essentials, route = Route(
            route = "/${MenuItems.Essentials.lowercase()}",
            action = RouteAction.PromptAction
        )
    )

    data object Suggested : NavigationRoute(
        name = MenuItems.Suggested, route = Route(
            route = "/${MenuItems.Suggested.lowercase()}",
            action = RouteAction.PromptAction
        )
    )

    data object Topics : NavigationRoute(
        name = MenuItems.Topics,
        route = Route(
            route = "/${MenuItems.Topics.lowercase()}",
            action = RouteAction.PromptAction
        )
    )

    data object Subscribe : NavigationRoute(
        name = MenuItems.Subscribe, route = Route(
            route = "/${MenuItems.Subscribe.lowercase()}",
            action = RouteAction.PromptAction
        )
    )

    data object Random : NavigationRoute(
        name = MenuItems.Random,
        route = Route(
            route = "/${MenuItems.Random.lowercase()}",
            action = RouteAction.Navigate
        )
    )

    data object DarkMode : NavigationRoute(
        name = MenuItems.DarkMode, route = Route(
            route = "/${MenuItems.DarkMode.lowercase()}",
            action = RouteAction.PromptAction
        )
    )
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

