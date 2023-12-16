package presentation.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

/**
 * Composable function that renders a theme switch button with animation, allowing toggling between dark and light themes.
 *
 * @param width The width of the theme switch button.
 * @param isDarkTheme The current theme mode (dark or light).
 * @param darkBackground The background color in dark mode.
 * @param lightBackground The background color in light mode.
 * @param lightIconColor The icon color in light mode.
 * @param darkIconColor The icon color in dark mode.
 * @param animationDuration The duration of the switch animation in milliseconds.
 * @param onThemeChange A callback function to handle theme toggle events.
 *
 * @author Prashant singh
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ThemeSwitch(
    width: Int = 70,
    isDarkTheme: Boolean,
    darkBackground: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    lightBackground: Color = darkBackground,
    lightIconColor: Color = MaterialTheme.colorScheme.primaryContainer,
    darkIconColor: Color = lightIconColor,
    animationDuration: Int = 500,
    onThemeChange: (Boolean) -> Unit
) {
    val animationSpec: AnimationSpec<Float> = tween(animationDuration)
    val screenWidth = LocalWindowInfo.current.containerSize.width
    val switchWidth = width.takeIf { width < screenWidth } ?: screenWidth
    val height = (switchWidth / 2).toFloat()

    val canvasOffset by animateFloatAsState(
        if (isDarkTheme) height else 0f,
        label = "CanvasOffset",
        animationSpec = animationSpec
    )

    val backgroundColor by animateColorAsState(
        targetValue = darkBackground.takeIf { isDarkTheme }
            ?: lightBackground,
        animationSpec = tween(animationDuration),
        label = "backgroundColor")

    val iconColor by animateColorAsState(
        targetValue = darkIconColor.takeIf { isDarkTheme }
            ?: lightIconColor,
        animationSpec = tween(animationDuration),
        label = "backgroundColor")


    Row(modifier = Modifier
        .size(width = width.dp, height = height.dp)
        .clip(CircleShape)
        .clickable { onThemeChange(!isDarkTheme) }
        .background(color = backgroundColor)) {
        CurvedMoon(
            modifier = Modifier.offset(x = canvasOffset.dp),
            moonRadius = canvasOffset,
            height = height,
            backgroundColor = backgroundColor,
            iconColor = iconColor
        )
    }
}


/**
 *
 *Composable function that renders a curved moon icon with specified parameters.
 *@param modifier The modifier to be applied to the composable.
 *@param moonRadius The radius of the curved moon.
 *@param height The height of the curved moon.
 *@param backgroundColor The background color of the composable.
 *@param iconColor The color of the curved moon icon.
 *
 * @author Prashant singh
 */
@Composable
private fun CurvedMoon(
    modifier: Modifier = Modifier,
    moonRadius: Float,
    height: Float,
    backgroundColor: Color,
    iconColor: Color
) {
    Canvas(
        modifier = modifier
            .fillMaxHeight()
            .background(backgroundColor)
            .size(height.dp)
    ) {
        drawCircle(
            color = iconColor,
            radius = (height * 1.30f),
        )
        drawCircle(
            color = backgroundColor,
            alpha = 1f,
            style = Fill,
            center = Offset(x = (height * 2.6f), y = height),
            radius = moonRadius,
        )
    }
}
