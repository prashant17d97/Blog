package com.prashant.blog.widgets

import androidx.compose.runtime.Composable
import com.prashant.blog.model.SocialLink
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.navigation.Link
import org.jetbrains.compose.web.css.px

@Composable
fun SocialMediaIcons(
    modifier: Modifier = Modifier,
    socialLink: List<SocialLink>,
) {
    Row(
        modifier = modifier.flexGrow(0.7f).gap(10.px),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        socialLink.forEach { social ->
            Link(
                path = social.platformLink,
                openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB
            ) {
                FaCustomIcon(
                    iconName = social.platform.lowercase(),
                    classname = "brands",
                    size = "2xl"
                )
            }
        }
    }
}