package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import core.NavHostController
import core.ResourcePath
import core.ResourcePath.Drawable.contentDescription
import core.ResourcePath.Drawable.iconFacebook
import core.ResourcePath.Drawable.iconGoogle
import core.ResourcePath.Drawable.iconLock
import core.ResourcePath.Drawable.iconMail
import core.ResourcePath.Drawable.iconProfile
import core.ResourcePath.Drawable.iconVisibility
import core.ResourcePath.Drawable.iconVisibilityOff
import core.ResourcePath.Drawable.loginLogo
import navigation.Screens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.CommonElements.NinjaButton
import presentation.CommonElements.shadowElevation

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(navHostController: NavHostController) {

    val focusManager = LocalFocusManager.current
    var isLogin by remember { mutableStateOf(true) }
    var isVisible by remember { mutableStateOf(false) }
    var keepMeSignIn by remember { mutableStateOf(false) }
    var emailMe by remember { mutableStateOf(false) }

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    GradiantWithImageColumn(
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp, alignment = Alignment.CenterVertically
        ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(loginLogo),
            contentDescription = loginLogo.contentDescription,
            modifier = Modifier.size(180.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = ResourcePath.String.loginToAccount.takeIf { isLogin }
            ?: ResourcePath.String.signUpFree,
            style = MaterialTheme.typography.headlineSmall)

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp, alignment = Alignment.Top
            )
        ) {
            AnimatedVisibility(!isLogin) {
                TextField(leadingIcon = {
                    Image(
                        painter = painterResource(iconProfile),
                        contentDescription = iconProfile.contentDescription
                    )
                },
                    placeholder = { Text(text = ResourcePath.String.name) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth().shadowElevation,
                    shape = CommonElements.cornerShape,
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
                    value = name,
                    onValueChange = {
                        name = it
                    })
            }

            TextField(leadingIcon = {
                Image(
                    painter = painterResource(iconMail),
                    contentDescription = iconMail.contentDescription
                )
            },
                placeholder = { Text(text = ResourcePath.String.email) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth().shadowElevation,
                shape = CommonElements.cornerShape,
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
                value = email,
                onValueChange = {
                    email = it
                })

            TextField(leadingIcon = {
                Image(
                    painter = painterResource(iconLock),
                    contentDescription = iconLock.contentDescription
                )
            },
                trailingIcon = {
                    Image(painter = painterResource(iconVisibilityOff.takeIf { isVisible }
                        ?: iconVisibility), contentDescription = iconLock.contentDescription,
                        modifier = Modifier.clickable {
                            isVisible = !isVisible
                        })
                },
                placeholder = { Text(text = ResourcePath.String.password) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
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
        }


        AnimatedVisibility(isLogin) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 10.dp, alignment = Alignment.CenterVertically
                ), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = ResourcePath.String.continueWith,
                    style = MaterialTheme.typography.bodyLarge
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.shadowElevation.background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = CommonElements.cornerShape
                        ).weight(1f).height(55.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                space = 10.dp, alignment = Alignment.CenterHorizontally
                            )
                        ) {
                            Image(
                                painter = painterResource(iconFacebook),
                                contentDescription = iconFacebook.contentDescription
                            )
                            Text(
                                text = ResourcePath.String.facebook,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier.shadowElevation.background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = CommonElements.cornerShape
                        ).weight(1f).height(55.dp), contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                space = 10.dp, alignment = Alignment.CenterHorizontally
                            )
                        ) {
                            Image(
                                painter = painterResource(iconGoogle),
                                contentDescription = iconGoogle.contentDescription
                            )
                            Text(
                                text = ResourcePath.String.google,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                    }
                }

                Text(textDecoration = TextDecoration.Underline,
                    text = ResourcePath.String.forgetYourPassword,
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.clickable {
                        navHostController.navigate(Screens.ForgetPassword,)
                    })
            }
        }
        AnimatedVisibility(!isLogin) {
            Column(
                modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(
                    space = 5.dp, alignment = Alignment.CenterVertically
                ), horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.clickable {
                        keepMeSignIn = !keepMeSignIn
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Checkbox(checked = keepMeSignIn, onCheckedChange = { keepMeSignIn = it })
                    Text(
                        text = ResourcePath.String.keepMeSignIn,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Row(
                    modifier = Modifier.clickable {
                        emailMe = !emailMe
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start

                ) {
                    Checkbox(checked = emailMe, onCheckedChange = { emailMe = it })
                    Text(
                        text = ResourcePath.String.emailSpecialPricing,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        NinjaButton(text = ResourcePath.String.login.takeIf { isLogin }
            ?: ResourcePath.String.createAccount, onClick = {

            val (route, popInclusive) = if (isLogin) {
                Screens.Home to true
            } else {
                Screens.SignUpProcess to false
            }

            navHostController.navigate(
                route = route, popInclusive = popInclusive,
            )
        })

        if (isLogin) {
            Spacer(modifier = Modifier.height(10.dp))
        }
        Text(textDecoration = TextDecoration.Underline,
            text = ResourcePath.String.createAccountButton.takeIf { isLogin }
                ?: ResourcePath.String.alreadyHaveAnAccount,
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
            modifier = Modifier.clickable {
                isLogin = !isLogin
            })
    }
}