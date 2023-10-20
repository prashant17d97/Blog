package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import core.NavHostController
import core.ResourcePath
import core.ResourcePath.Drawable.contentDescription
import core.Size
import kotlinx.coroutines.delay
import navigation.Screens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

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


@OptIn(ExperimentalResourceApi::class)
@Composable
fun GradiantWithImageColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    vertical: Dp = Size.Padding.parentVertical,
    horizontal: Dp = Size.Padding.parentHorizontal,
    image: String = ResourcePath.Drawable.splashPattern,
    colors: List<Color> = listOf(
        Color.Transparent,
        MaterialTheme.colorScheme.background,
        MaterialTheme.colorScheme.background
    ),
    brush: Brush = Brush.verticalGradient(
        colors = colors
    ),
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(image),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            contentDescription = ResourcePath.Drawable.splashPattern.contentDescription
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    brush = brush
                )
                .padding(horizontal, vertical),

            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = content
        )
    }
}