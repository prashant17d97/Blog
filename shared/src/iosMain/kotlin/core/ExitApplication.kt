package core

import androidx.compose.runtime.Composable
import platform.posix.exit

@Composable
actual fun CloseApplication(){
    exit(0)
}