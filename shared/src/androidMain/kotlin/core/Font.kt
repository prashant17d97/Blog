package core

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import java.io.IOException


/*
@Composable
actual fun GetFont(
    id: FontResource,
    fontWeight: FontWeight,
    fontStyle: FontStyle
): Font? {
    Font
    return Font(resId = id.fontResourceId, weight = fontWeight, style = fontStyle)
}


actual class String(private val context: Context) {
    actual fun get(id: StringResource, args: List<Any>): kotlin.String {
        return if (args.isEmpty()) {
            StringDesc.Resource(id).toString(context = context)
        } else {
            id.format(*args.toTypedArray()).toString(context = context)
        }
    }
}*/

/*
actual class Drawable(private val context: Context) {
    actual fun getDrawable(id: ImageResource): Drawable? {
        return id.getDrawable(context = context)
    }
}*/


@SuppressLint("DiscouragedApi")
@Composable
actual fun font(res: String, fontWeight: FontWeight, fontStyle: FontStyle): Font {
    val context = LocalContext.current
    val fontResourceId: Int =
        context.resources.getIdentifier(fontNameFromPath(res), "font", context.packageName)

    return Font(resId = fontResourceId, weight = fontWeight, style = fontStyle)
}

private fun fontNameFromPath(path: String): String {
    val parts = path.split("/")
    val fileName = parts.last()
    return fileName.substringBeforeLast(".")
}

interface Resource {
    suspend fun readBytes(): ByteArray
}

/**
 * Get a resource from <sourceSet>/resources (for example, from commonMain/resources).
 */
internal abstract class AbstractResourceImpl(val path: String) : Resource {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return if (other is AbstractResourceImpl) {
            path == other.path
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return path.hashCode()
    }
}

fun resource(path: String): Resource = AndroidResourceImpl(path)


private class AndroidResourceImpl(path: String) : AbstractResourceImpl(path) {
    override suspend fun readBytes(): ByteArray {
        val classLoader = Thread.currentThread().contextClassLoader
            ?: (::AndroidResourceImpl.javaClass.classLoader)
        val resource = classLoader.getResourceAsStream(path)
        if (resource != null) {
            return resource.readBytes()
        } else {
            throw MissingResourceException(path)
        }
    }
}

internal class MissingResourceException constructor(path: String) :
    IOException("Missing resource with path: $path")

