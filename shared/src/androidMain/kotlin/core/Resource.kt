package core

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

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


@Composable
actual fun font(name: String, res: String, weight: FontWeight, style: FontStyle): Font {
    val context = LocalContext.current
    val id = context.resources.getIdentifier(res, "resources/font", context.packageName)
    return Font(id, weight, style)
}
