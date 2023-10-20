package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.NavHostController
import core.ResourcePath
import core.ResourcePath.Drawable.contentDescription
import core.Size
import navigation.Screens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CongratsScreen(navHostController: NavHostController) {

    GradiantWithImageColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        colors = listOf(
            Color.Transparent,
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp, alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(ResourcePath.Drawable.iconSuccess),
                contentDescription = ResourcePath.Drawable.iconBack.contentDescription,
                modifier = Modifier.size(Size.logoWidth)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = ResourcePath.String.congrats,
                style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.primary),
                textAlign = TextAlign.Center,
                lineHeight = 35.sp,
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = ResourcePath.String.resetSuccessful,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                lineHeight = 35.sp,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp, alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            CommonElements.NinjaButton(text = ResourcePath.String.next, onClick = {
                navHostController.navigate(
                    route = Screens.LoginScreen, popInclusive = true,
                )
            })
            Spacer(modifier = Modifier.height(15.dp))
        }


    }
}