import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import core.BackHandler
import core.CloseApplication
import core.rememberNavHostController
import navigation.NavGraph
import navigation.Screens
import theme.AppTheme

@Composable
fun App() {
    val navHostController = rememberNavHostController(startDestination = Screens.Splash)
    val canExit by navHostController.canExit.collectAsState()
    BackHandler(enabled = true) {
        navHostController.popUp()
    }
    AppTheme(
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = false
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme
                .colorScheme
                .background,
        ) {
            NavGraph(navHostController)
        }
    }

    if (canExit) {
        CloseApplication()
    }
}