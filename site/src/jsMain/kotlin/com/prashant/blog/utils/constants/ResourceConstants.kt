package com.prashant.blog.utils.constants

object ResourceConstants {

    object FooterSocialIcons {
        const val Facebook = "/iconresources/Facebook.svg"
        const val Twitter = "/iconresources/Twitter.svg"
        const val Instagram = "/iconresources/Instagram.svg"
        const val Vk = "/iconresources/Vk.svg"
        const val Pintrest = "/iconresources/Pinterest.svg"
        const val Pic = "/iconresources/Pic.png"
        const val SiteIcon = "/iconresources/SiteIcon.jpg"
        const val RandomImg = "/iconresources/random.png"
        const val ReadingCard = "/iconresources/ReadingCard.png"
        const val Popular = "/iconresources/Popular.png"
        const val SuggestionOne = "/iconresources/SuggestionOne.png"
        const val SuggestionTwo = "/iconresources/SuggestionTwo.png"
        const val EssentialsCard = "/iconresources/EssentialsCard.png"

        val socialMediaIcons = listOf(Facebook, Twitter, Instagram, Vk, Pintrest)
    }

    object CSSIds {
        const val cssImgClassId = "img-fluid"
        const val cssCardId = "card"
        const val cssIconId = "iconFilter"
        const val cssInputId = "input"
        const val cssHoverAttribute = ":hover"
        const val cssFocusAttribute = ":focus"

    }

    object MenuItems {
        const val Popular = "Popular"
        const val New = "New"
        const val ReadingList = "Reading List"
        const val Essentials = "Essentials"
        const val Suggested = "Suggested"
        const val Topics = "Topics"
        const val Subscribe = "Subscribe"
        const val Random = "Random"
        const val DarkMode = "Dark mode"


    }

    val String.contentDescription: String
        get() = convertStringToTitleCase(this)

    private fun convertStringToTitleCase(input: String): String {
        val words = input.split("_", ".").map {
            it.replaceFirstChar { char ->
                if (char.isLowerCase()) char.titlecase() else char.toString()
            }
        }
        return words.joinToString(" ")
    }
}