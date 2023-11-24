package presentation

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.NavHostController

@Composable
fun Home(navHostController: NavHostController) {
    var isSearching by remember {
        mutableStateOf(false)
    }

    val targetValue by animateIntAsState(
        targetValue = if (isSearching) 20 else 3,
        animationSpec = tween(durationMillis = 1000)
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(5) {
            Text(
                text = "Prashant Singh at Home", style = MaterialTheme.typography.headlineLarge
            )
        }
    }
    Row { }
}