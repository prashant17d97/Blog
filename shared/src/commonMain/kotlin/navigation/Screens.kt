package navigation


const val splash = "Splash"
const val welcome = "Welcome"
const val home = "Welcome"
const val onboarding = "Onboarding"
const val loginScreen = "LoginScreen"
const val forgetPassword = "ForgetPassword"
const val otpVerification = "OTPVerification"
const val resetPassword = "ResetPassword"
const val congratsScreen = "CongratsScreen"
const val signUpProcess = "SignUpProcess"

sealed class Screens(
    var title: String,
    var icon: Int = 0,
    var route: String = title,
    var isNavigatingBack: Boolean = false
) {
    data object Splash : Screens(title = splash)
    data object Welcome : Screens(title = welcome)
    data object Home : Screens(title = home)
    data object Onboarding : Screens(title = onboarding)
    data object LoginScreen : Screens(title = loginScreen)
    data object ForgetPassword : Screens(title = forgetPassword)
    data object OTPVerification : Screens(title = otpVerification)
    data object ResetPassword : Screens(title = resetPassword)
    data object CongratsScreen : Screens(title = congratsScreen)
    data object SignUpProcess : Screens(title = signUpProcess)
}
