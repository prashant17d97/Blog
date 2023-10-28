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

        val socialMediaIcons = listOf(Facebook, Twitter, Instagram, Vk, Pintrest)

    }

    object MenuItems {

        const val Popular = "Popular"
        const val New = "New"
        const val ReadingList = "Reading List"
        const val Topics = "Topics"
        const val Subscribe = "Subscribe"

        val menuLists = listOf(Popular, New, ReadingList, Topics, Subscribe)
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