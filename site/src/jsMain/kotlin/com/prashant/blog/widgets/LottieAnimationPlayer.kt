package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.ElementBuilder
import org.jetbrains.compose.web.dom.TagElement


/**
 * A Composable function for rendering Lottie animations using the LottieFiles Player.
 *
 * @param modifier The modifier to apply to the Lottie animation.
 * @param src The URL of the Lottie animation JSON file.
 * @param autoPlay Set to true to automatically play the animation when it's loaded.
 * @param loop Set to true to loop the animation indefinitely.
 * @param controls Set to false to hide playback controls.
 * @param direction The playback direction (Forward or Reverse).
 * @param hover Set to true to enable hover interaction.
 * @param playMode The play mode (Normal or Bounce).
 *
 * @sample LottieAnimationPlayer(src = "https://lottie.host/ab2d0b41-6e0e-436e-9d1c-000ee26a2f87/qE0MnnMaj1.json)
 */
@Composable
fun LottieAnimationPlayer(
    modifier: Modifier = Modifier,
    src: String,
    autoPlay: Boolean = true,
    loop: Boolean = true,
    controls: Boolean = false,
    direction: LottieDirection = LottieDirection.Forward,
    hover: Boolean = false,
    playMode: LottiePlayMode = LottiePlayMode.Normal
) {
    TagElement(
        elementBuilder = ElementBuilder.createBuilder("lottie-player"),
        applyAttrs = modifier
            .background(Color.transparent)
            .width(300.px)
            .height(300.px)
            .toAttrs {
                attr(
                    attr = "src",
                    value = src
                )
                if (autoPlay) {
                    attr("autoplay", "")
                }
                if (controls) {
                    attr("controls", "")
                }
                if (loop) {
                    attr("loop", "")
                }
                if (hover) {
                    attr("hover", "")
                }
                attr("direction", direction.direction)
                attr("mode", playMode.name.lowercase())
            },
        content = null
    )
}


enum class LottieDirection(val direction: String) {
    Forward("1"),
    Backward("-1")
}

enum class LottiePlayMode {
    Normal,
    Bounce
}