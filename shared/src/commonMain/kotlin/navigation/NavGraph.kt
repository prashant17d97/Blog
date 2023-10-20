package navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import core.NavHostController
import presentation.CongratsScreen
import presentation.ForgetPassword
import presentation.Home
import presentation.LoginScreen
import presentation.OTPVerification
import presentation.Onboarding
import presentation.ResetPassword
import presentation.Splash
import presentation.Welcome
import presentation.signup.SignUpProcess

@Composable
fun NavGraph(navHostController: NavHostController) {

    val currentStack by navHostController.currentStack.collectAsState()
    val isPushingUp by navHostController.isPushingUp.collectAsState()
    AnimatedContent(
        targetState = currentStack,
        transitionSpec = {
            (fadeIn() + slideInHorizontally(animationSpec = tween(400),
                initialOffsetX = { fullHeight ->
                    fullHeight.takeIf { isPushingUp } ?: -fullHeight
                })).togetherWith(
                fadeOut(
                    animationSpec = tween(
                        300
                    )
                )
            )
        }
    ) { targetState ->
        when (targetState) {
            Screens.Splash -> Splash(navHostController)
            Screens.Welcome -> Welcome(navHostController)
            Screens.LoginScreen -> LoginScreen(navHostController)
            Screens.Onboarding -> Onboarding(navHostController)
            Screens.ForgetPassword -> ForgetPassword(navHostController)
            Screens.OTPVerification -> OTPVerification(navHostController)
            Screens.ResetPassword -> ResetPassword(navHostController)
            Screens.CongratsScreen -> CongratsScreen(navHostController)
            Screens.SignUpProcess -> SignUpProcess(navHostController)
            Screens.Home -> Home(navHostController)
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun composable(
    targetState: Screens,
    isPushingUp: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedContent(
        targetState = targetState,
        transitionSpec = {
            (fadeIn() + slideInHorizontally(animationSpec = tween(400),
                initialOffsetX = { fullHeight ->
                    fullHeight.takeIf { isPushingUp } ?: -fullHeight
                })).togetherWith(
                fadeOut(
                    animationSpec = tween(
                        200
                    )
                )
            )
        }
    ) {
        content()
    }
}