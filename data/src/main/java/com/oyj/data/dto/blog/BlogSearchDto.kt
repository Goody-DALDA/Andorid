package com.oyj.data.dto.blog

import kotlinx.serialization.Serializable

@Serializable
data class BlogSearchDto(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int,
)
