package com.goody.dalda.data.dto.blog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("bloggerlink")
    val bloggerLink: String,
    @SerialName("bloggername")
    val bloggerName: String,
    val description: String,
    val link: String,
    val postdate: String,
    val title: String,
)
