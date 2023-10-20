package core

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

/*
expect class String {
    fun get(id: StringResource, args: List<Any>): kotlin.String
}

*/
/*
expect class Drawable{
    fun getDrawable(id:ImageResource):Drawable?
}
*//*


@Composable
expect fun GetFont(
    id: FontResource,
    fontWeight: FontWeight,
    fontStyle: FontStyle
): Font?*/


@Composable
expect fun font(res: String, fontWeight: FontWeight, fontStyle: FontStyle): Font