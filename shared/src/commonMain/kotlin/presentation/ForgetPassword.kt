package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
import presentation.CommonElements.NinjaButton

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ForgetPassword(navHostController: NavHostController) {
    GradiantWithImageColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        colors = listOf(
            Color.Transparent,
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp, alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(painter = painterResource(ResourcePath.Drawable.iconBack),
                    contentDescription = ResourcePath.Drawable.iconBack.contentDescription,
                    modifier = Modifier.size(Size.Button.backButton).clickable {
                        navHostController.popUp()
                    })
            }

            Text(
                text = ResourcePath.String.forgotPassword,
                style = MaterialTheme.typography.headlineLarge,
                lineHeight = 35.sp,
            )
            Text(
                text = ResourcePath.String.passwordResetOption,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraLight),
                lineHeight = 20.sp,
            )

            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(
                    space = 10.dp, alignment = Alignment.Top
                ), horizontalAlignment = Alignment.Start
            ) {
                PasswordResetOption(viaSource = RestViaOption.SMS, onClick = {})
                PasswordResetOption(viaSource = RestViaOption.Email, onClick = {})
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp, alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            NinjaButton(text = ResourcePath.String.next, onClick = {
                navHostController.navigate(route = Screens.OTPVerification,)
            })
            Spacer(modifier = Modifier.height(15.dp))
        }


    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
private fun PasswordResetOption(viaSource: RestViaOption, onClick: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth().background(
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
            shape = CommonElements.cornerShape
        ).padding(15.dp).clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 10.dp, alignment = Alignment.Start
        )
    ) {
        Image(
            painter = painterResource(viaSource.icon),
            contentDescription = viaSource.icon.contentDescription,
            modifier = Modifier.size(Size.Button.backButton)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(
                5.dp, alignment = Alignment.CenterVertically
            ), horizontalAlignment = Alignment.Start
        ) {
            val (viaOption, sourceEndpoint) = when (viaSource) {
                RestViaOption.SMS -> viaSource.viaOption to viaSource.sourceEndpoint
                RestViaOption.Email -> viaSource.viaOption to viaSource.sourceEndpoint
            }

            Text(
                text = viaOption,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraLight),
                lineHeight = 20.sp,
            )
            Text(
                text = "**** **** $sourceEndpoint",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                lineHeight = 25.sp,
            )
        }
    }
}

internal enum class RestViaOption(
    val viaOption: String, val sourceEndpoint: String, val icon: String
) {
    SMS("Via sms:", "4321", ResourcePath.Drawable.iconMessage), Email(
        "Via email:",
        "@gmail.com",
        ResourcePath.Drawable.iconResetOptionMail
    )
}