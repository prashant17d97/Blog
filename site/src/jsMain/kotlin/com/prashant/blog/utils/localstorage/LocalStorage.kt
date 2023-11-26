package com.prashant.blog.utils.localstorage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.localStorage

object LocalStorageKeys {
    const val COLOR_MODE_KEY = "blog:app:colorMode"
}

interface LocalStorage {
    fun saveValue(key: String, value: String)
    fun retrieveValue(key: String): String?
    fun defaultColorMode(): ColorMode
}

internal class LocalStorageImpl : LocalStorage {
    override fun saveValue(key: String, value: String) {
        localStorage.setItem(key, value)
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