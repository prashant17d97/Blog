package com.prashant.blog.components.constants

object ResourceConstants {

    object FooterSocialIcons {
        const val Facebook = "/iconres/Facebook.svg"
        const val Twitter = "/iconres/Twitter.svg"
        const val Instagram = "/iconres/Instagram.svg"
        const val Vk = "/iconres/Vk.svg"
        const val Pintrest = "/iconres/Pinterest.svg"
        const val Pic = "/iconres/Pic.png"
        const val SiteIcon = "/iconres/SiteIcon.jpg"
        const val RandomImg = "/iconres/random.png"
        const val ReadingCard = "/iconres/ReadingCard.png"
        const val Popular = "/iconres/Popular.png"
        const val SuggestionOne = "/iconres/SuggestionOne.png"
        const val SuggestionTwo = "/iconres/SuggestionTwo.png"
        const val EssentialsCard = "/iconres/EssentialsCard.png"

        val socialMediaIcons = listOf(Facebook, Twitter, Instagram, Vk, Pintrest)
    }

    object CSSIds {
        const val cssImgClassId = "img-fluid"
        const val cssCardIt ="card"

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

        val menuLists = listOf(Popular, New, ReadingList, Topics, Subscribe, DarkMode)
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