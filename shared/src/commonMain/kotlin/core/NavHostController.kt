package core

import kotlinx.coroutines.flow.StateFlow
import navigation.Screens

interface NavHostController {
    val currentStack: StateFlow<Screens>
    val backStackEntry: StateFlow<List<Screens>>
    val isPushingUp: StateFlow<Boolean>
    val canExit: StateFlow<Boolean>
    val getArguments: StateFlow<String>

    fun navigate(
        route: Screens,
        popInclusive: Boolean = false,
        navigatingForward: Boolean = true,
        argumentString: String? = null
    )

    fun popUp()
}