package presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.horizontalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.Size

object CommonElements {
    @Composable
    fun NinjaButton(
        text: String,
        fillMaxWith: Boolean = false,
        gradient: Brush = horizontalGradient(
            colors = listOf(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                MaterialTheme.colorScheme.primary
            )
        ),

        onClick: () -> Unit
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                Color.Transparent
            ),
            shape = cornerShape,
            contentPadding = PaddingValues(),
            onClick = onClick
        )
        {
            Box(
                modifier = Modifier
                    .background(gradient)
                    .then(
                        if (fillMaxWith) {
                            Modifier
                                .fillMaxWidth(0.9f)
                                .height(height = Size.Button.height)
                        } else {
                            Modifier.size(width = Size.Button.width, height = Size.Button.height)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }


    val Modifier.shadowElevation: Modifier
        get() = this.shadow(elevation = 10.dp, ambientColor = ambientColor)
    val Modifier.globalClip: Modifier
        get() = this.clip(shape = cornerShape)

    val cornerShape = RoundedCornerShape(15.dp)

    val ambientColor = Color(0x125A6CEA)
}