package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.NavHostController
import core.ResourcePath
import core.ResourcePath.Drawable.contentDescription
import core.Size
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Onboarding(navHostController: NavHostController) {

    GradiantWithImageColumn(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(ResourcePath.Drawable.iconBack),
                contentDescription = ResourcePath.Drawable.iconBack.contentDescription,
                modifier = Modifier.size(Size.Button.backButton).clickable {
                    navHostController.popUp()
                }
            )
        }
    }
}