package core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import navigation.Screens

internal class NavHostControllerImpl(startDestination: Screens) : NavHostController {

    private val _backStackEntry: MutableStateFlow<List<Screens>> =
        MutableStateFlow(listOf(startDestination))
    override val backStackEntry: StateFlow<List<Screens>> = _backStackEntry
    private val _isPushingUp: MutableStateFlow<Boolean> = MutableStateFlow(true)
    override val isPushingUp: StateFlow<Boolean>
        get() = _isPushingUp

    private val _canExit = MutableStateFlow(false)
    override val canExit: StateFlow<Boolean>
        get() = _canExit

    private val _currentStack: MutableStateFlow<Screens> = MutableStateFlow(startDestination)
    override val currentStack: StateFlow<Screens> = _currentStack

    private val _getArguments: MutableStateFlow<String> = MutableStateFlow("")
    override val getArguments: StateFlow<String> = _getArguments

    private var stackEntry: MutableList<Screens> = mutableListOf(startDestination)

    override fun navigate(
        route: Screens,
        popInclusive: Boolean,
        navigatingForward: Boolean,
        argumentString: String?
    ) {
        _isPushingUp.tryEmit(navigatingForward)
        if (popInclusive) {
            stackEntry.clear()
        }
        stackEntry.add(route)
        _currentStack.tryEmit(route)
        _backStackEntry.tryEmit(stackEntry.toList())

        argumentString?.let { _getArguments.tryEmit(it) }
    }

    override fun popUp() {
        _isPushingUp.tryEmit(false)
        if (stackEntry.size > 1) {
            stackEntry.removeAt(stackEntry.size - 1)
            _currentStack.tryEmit(stackEntry.last())
            _backStackEntry.tryEmit(stackEntry.toList())
        } else {
            _backStackEntry.tryEmit(emptyList())
            stackEntry.clear()
            _canExit.tryEmit(true)
        }
    }

    companion object {
        val Saver: Saver<NavHostControllerImpl, *> = listSaver(save = {
            listOf(
                it.currentStack,
                it.stackEntry,
            )
        }, restore = {
            NavHostControllerImpl(
                startDestination = it[0] as Screens
            )
        })
    }
}

@Composable
fun rememberNavHostController(
    startDestination: Screens
): NavHostController {
    return remember {
        NavHostControllerImpl(startDestination)
    }
}
