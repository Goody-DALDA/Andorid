package com.goody.dalda.data.dto.blog

data class BlogSearchDto(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int,
)
