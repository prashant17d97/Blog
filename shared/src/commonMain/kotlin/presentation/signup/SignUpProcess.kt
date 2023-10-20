package presentation.signup

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
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
import presentation.CommonElements.cornerShape
import presentation.CommonElements.globalClip
import presentation.CommonElements.shadowElevation
import presentation.GradiantWithImageColumn
import presentation.signup.CurrentProcessesPosition.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignUpProcess(navHostController: NavHostController) {

    var currentProcess by remember {
        mutableStateOf(FillBio)
    }
    var isPushingUp by remember {
        mutableStateOf(true)
    }
    GradiantWithImageColumn(
        image = ResourcePath.Drawable.iconBgSecond,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        brush = Brush.linearGradient(
            colors = listOf(
                Color.Transparent,
                MaterialTheme.colorScheme.background,
                MaterialTheme.colorScheme.background,
                MaterialTheme.colorScheme.background,
            )
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
                        isPushingUp = false
                        when (currentProcess) {
                            FillBio -> navHostController.popUp()
                            PaymentMethod -> currentProcess = FillBio
                            UploadPhoto -> currentProcess = PaymentMethod
                            PhotoPreview -> currentProcess = UploadPhoto
                            Location -> currentProcess = PhotoPreview
                        }
                    })
            }
            AnimatedContent(
                targetState = currentProcess,
                transitionSpec = {
                    (fadeIn() + slideInHorizontally(
                        animationSpec = tween(400),
                        initialOffsetX = { fullHeight ->
                            fullHeight.takeIf { isPushingUp } ?: -fullHeight
                        })).togetherWith(
                        fadeOut(
                            animationSpec = tween(
                                300
                            )
                        )
                    )
                }) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 10.dp, alignment = Alignment.Top
                    ),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = currentProcess.heading,
                        style = MaterialTheme.typography.headlineLarge,
                        lineHeight = 35.sp,
                    )
                    Text(
                        text = currentProcess.subTitle,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraLight),
                        lineHeight = 20.sp,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    when (currentProcess) {
                        FillBio -> Bio()
                        PaymentMethod -> PaymentMethod()
                        UploadPhoto -> UploadPhotograph {}
                        PhotoPreview -> PreviewUpload {
                            isPushingUp = false
                            currentProcess = UploadPhoto
                        }

                        Location -> Location {}
                    }
                }
            }

        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp, alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            NinjaButton(text = ResourcePath.String.next, onClick = {
                isPushingUp = true
                when (currentProcess) {
                    FillBio -> currentProcess = PaymentMethod
                    PaymentMethod -> currentProcess = UploadPhoto
                    UploadPhoto -> currentProcess = PhotoPreview
                    PhotoPreview -> currentProcess = Location
                    Location -> navHostController.navigate(route = Screens.CongratsScreen,)
                }
            })
            Spacer(modifier = Modifier.height(15.dp))
        }


    }
}

@Composable
private fun Bio() {
    val focusManager = LocalFocusManager.current

    var name by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var number by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp, alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(placeholder = {
            Text(
                text = ResourcePath.String.firstName
            )
        },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
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
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth().shadowElevation,
            shape = cornerShape,
            value = name,
            onValueChange = {
                name = it
            })

        TextField(placeholder = {
            Text(
                text = ResourcePath.String.lastname
            )
        },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
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
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth().shadowElevation,
            shape = cornerShape,
            value = lastName,
            onValueChange = {
                lastName = it
            })



        TextField(placeholder = {
            Text(
                text = ResourcePath.String.number
            )
        },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
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
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth().shadowElevation,
            shape = cornerShape,
            value = number,
            onValueChange = {
                if (number.length < 10) {
                    number = it
                } else {
                    focusManager.clearFocus()
                }
            })
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun PaymentMethod() {

    val paymentList = listOf(
        ResourcePath.Drawable.iconPaypal,
        ResourcePath.Drawable.iconPayoneer,
        ResourcePath.Drawable.iconVisa
    )
    Column(
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp, alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(paymentList.size) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(70.dp).shadowElevation.globalClip.background(color = MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(paymentList[it]),
                    contentDescription = paymentList[it].contentDescription,
                    colorFilter = ColorFilter.tint(color = Color.White.takeIf { isSystemInDarkTheme() }
                        ?: Color.Black))
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun UploadPhotograph(onClick: (UploadPhotoOption) -> Unit) {
    val photoUploadOptions = listOf(
        UploadPhotoOption.Gallery,
        UploadPhotoOption.Camera
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp, alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(photoUploadOptions.size) {
            val photoOption = photoUploadOptions[it]
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .shadowElevation
                    .globalClip
                    .background(color = MaterialTheme.colorScheme.surface)
                    .clickable {
                        onClick.invoke(photoOption)
                    },
                verticalArrangement = Arrangement.spacedBy(
                    space = 10.dp,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(photoOption.icon),
                    contentDescription = photoOption.label
                )

                Text(
                    text = photoOption.label,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun PreviewUpload(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .shadowElevation
                .globalClip
                .background(color = MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                modifier = Modifier
                    .size(250.dp)
                    .globalClip,
                painter = painterResource(ResourcePath.Drawable.iconFacebook),
                contentDescription = ""
            )

            Image(
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp)
                    .clickable {
                        onClick()
                    },
                painter = painterResource(ResourcePath.Drawable.iconClose),
                contentDescription = ""
            )
        }

    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Location(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .shadowElevation
            .globalClip
            .background(color = MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.background(
                    color = Color.Transparent
                ),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 10.dp,
                    alignment = Alignment.Start
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(ResourcePath.Drawable.iconPin),
                    contentDescription = ResourcePath.Drawable.iconPin.contentDescription
                )

                Text(
                    text = ResourcePath.String.yourLocation,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick()
                    }
                    .globalClip
                    .height(50.dp)
                    .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = ResourcePath.String.setLocation,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

