package com.prashant.blog.utils.localstorage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.localStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object LocalStorageKeys {
    const val COLOR_MODE_KEY = "blog:app:colorMode"
}

interface LocalStorage {

    val colorMode: StateFlow<String>
    fun saveValue(key: String, value: String)
    fun retrieveValue(key: String): String?
    fun defaultColorMode(): ColorMode
}

internal class LocalStorageImpl : LocalStorage {

    private val _coloMode = MutableStateFlow(ColorMode.DARK.name)
    override val colorMode: StateFlow<String> = _coloMode

    override fun saveValue(key: String, value: String) {
        localStorage.setItem(key, value)
        _coloMode.tryEmit(value)
    }

    override fun retrieveValue(key: String): String? {
        return localStorage.getItem(key)
    }

    override fun defaultColorMode(): ColorMode {
        return localStorage.getItem(LocalStorageKeys.COLOR_MODE_KEY)?.let {
            ColorMode.valueOf(it)
        } ?: ColorMode.DARK
    }
}

@Composable
fun rememberLocalStorage(): LocalStorage {
    return remember { LocalStorageImpl() }
}