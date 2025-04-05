package com.oyj.dataa.dto


data class NoticeDto(
    val status: String,
    val message: String,
    val data: List<PostDto>,
)

data class PostDto(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val isActive: Boolean,
)

