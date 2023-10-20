package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import presentation.CommonElements.shadowElevation

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ResetPassword(navHostController: NavHostController) {
    val focusManager = LocalFocusManager.current

    var password by remember {
        mutableStateOf("")
    }

    var isVisible by remember {
        mutableStateOf(false)
    }

    var isVisibleConfirmPassword by remember {
        mutableStateOf(false)
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

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
                text = ResourcePath.String.resetPassword,
                style = MaterialTheme.typography.headlineLarge,
                lineHeight = 35.sp,
            )
            Text(
                text = ResourcePath.String.resetPasswordInstruction,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraLight),
                lineHeight = 20.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                trailingIcon = {
                    Image(painter = painterResource(ResourcePath.Drawable.iconVisibilityOff.takeIf { isVisible }
                        ?: ResourcePath.Drawable.iconVisibility),
                        contentDescription = ResourcePath.Drawable.iconLock.contentDescription,
                        modifier = Modifier.clickable {
                            isVisible = !isVisible
                        })
                },
                placeholder = { Text(text = ResourcePath.String.password) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                ),

                visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    errorContainerColor = MaterialTheme.colorScheme.surface,
                ),
                modifier = Modifier.fillMaxWidth().shadowElevation,
                shape = CommonElements.cornerShape,
                value = password,
                onValueChange = {
                    password = it
                })


            TextField(
                trailingIcon = {
                    Image(painter = painterResource(ResourcePath.Drawable.iconVisibilityOff.takeIf { isVisibleConfirmPassword }
                        ?: ResourcePath.Drawable.iconVisibility),
                        contentDescription = ResourcePath.Drawable.iconLock.contentDescription,
                        modifier = Modifier.clickable {
                            isVisibleConfirmPassword = !isVisibleConfirmPassword
                        })
                },
                placeholder = { Text(text = ResourcePath.String.confirmPassword) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                visualTransformation = if (isVisibleConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    errorContainerColor = MaterialTheme.colorScheme.surface,
                ),
                modifier = Modifier.fillMaxWidth().shadowElevation,
                shape = CommonElements.cornerShape,
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                })

        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp, alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            NinjaButton(text = ResourcePath.String.next, onClick = {
                navHostController.navigate(route = Screens.CongratsScreen,)
            })
            Spacer(modifier = Modifier.height(15.dp))
        }


    }
}