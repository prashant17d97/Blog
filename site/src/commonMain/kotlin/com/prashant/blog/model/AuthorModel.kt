package com.prashant.blog.model

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.Id
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorModel(
    @SerialName("aboutU")
    val aboutU: String,
    @SerialName("contactNumber")
    val contactNumber: String,
    @SerialName("countryCode")
    val countryCode: String,
    @SerialName("email")
    val email: String,
    @SerialName(Id)
    val _id: String = "",
    @SerialName("name")
    val name: String,
    @SerialName("socialLinks")
    val socialLinks: List<SocialLink>,
    @SerialName("userImage")
    val userImage: String
) {
    companion object {
        val getEmptyBody: AuthorModel
            get() = AuthorModel(
                aboutU = "",
                contactNumber = "",
                countryCode = "",
                email = "",
                _id = "",
                name = "",
                socialLinks = listOf(),
                userImage = ""
            )
    }}
