package core

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource


/*@Composable
actual fun GetFont(
    id: String,
    fontWeight: FontWeight,
    fontStyle: FontStyle
): Font? = id.asFont(weight = fontWeight, style = fontStyle)

actual class String {
    actual fun get(id: StringResource, args: List<Any>): kotlin.String {
        return if (args.isEmpty()) {
            StringDesc.Resource(id).localized()
        } else {
            id.format(*args.toTypedArray()).localized()
        }
    }
}*/

/*
actual class Drawable{
    actual fun getDrawable(id: ImageResource): Drawable? {
        return ResourcesKt.getImageByFileName(name: "image_name").toUIImage()!
    }
}*/


private val cache: MutableMap<String, Font> = mutableMapOf()

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun font(res: String, fontWeight: FontWeight, fontStyle: FontStyle): Font {
    return cache.getOrPut(res) {
        val byteArray = runBlocking {
            resource(res).readBytes()
        }
        androidx.compose.ui.text.platform.Font(res, byteArray, fontWeight, fontStyle)
    }
}