package com.prashant.blog.enums

enum class Theme(
    val hex: String,
    val rgb: RGB
) {
    Primary(
        hex = "#00A2FF",
        rgb = RGB(r = 0, g = 162, b = 255)
    ),
    Secondary(
        hex = "#001019",
        rgb = RGB(r = 0, g = 16, b = 25)
    ),
    Tertiary(
        hex = "#001925",
        rgb = RGB(r = 0, g = 25, b = 37)
    ),
    LightGray(
        hex = "#FAFAFA",
        rgb = RGB(r = 250, g = 250, b = 250)
    ),
    Gray(
        hex = "#E9E9E9",
        rgb = RGB(r = 233, g = 233, b = 233)
    ),
    DarkGray(
        hex = "#646464",
        rgb = RGB(r = 100, g = 100, b = 100)
    ),
    HalfWhite(
        hex = "#FFFFFF",
        rgb = RGB(r = 255, g = 255, b = 255, a = 0.5f)
    ),
    HalfBlack(
        hex = "#000000",
        rgb = RGB(r = 0, g = 0, b = 0, a = 0.5f)
    ),
    White(
        hex = "#FFFFFF",
        rgb = RGB(r = 255, g = 255, b = 255)
    ),
    Green(
        hex = "#00FF94",
        rgb = RGB(r = 0, g = 255, b = 148)
    ),
    Yellow(
        hex = "#FFEC45",
        rgb = RGB(r = 255, g = 236, b = 69)
    ),
    Red(
        hex = "#FF6359",
        rgb = RGB(r = 255, g = 99, b = 89)
    ),
    Purple(
        hex = "#8B6DFF",
        rgb = RGB(r = 139, g = 109, b = 255)
    ),
    Sponsored(
        hex = "#3300FF",
        rgb = RGB(r = 51, g = 0, b = 255)
    )
}

data class RGB(
    val r: Int,
    val g: Int,
    val b: Int,
    val a: Float = 1.0f,
)