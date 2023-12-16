package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import core.NavHostController
import core.ResourcePath
import core.ResourcePath.Drawable.contentDescription
import core.Size
import kotlinx.coroutines.delay
import navigation.Screens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.widgets.GradiantWithImageColumn

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Splash(navHostController: NavHostController) {

    LaunchedEffect(Unit) {
        delay(1000)
        navHostController.navigate(
            route = Screens.Welcome,
            popInclusive = true,
        )

    }

    GradiantWithImageColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(res = ResourcePath.Drawable.logo),
                    modifier = Modifier.size(
                        width = Size.logoWidth, height = Size.logoHeight
                    ),
                    contentDescription = ResourcePath.Drawable.logo.contentDescription
                )
                Text(
                    text = ResourcePath.String.appName,
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 40.sp,
                    )
                )
                Text(
                    text = ResourcePath.String.appSlogan,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 13.sp, fontWeight = FontWeight.SemiBold
                    )
                )
            }
        },
    )
}
