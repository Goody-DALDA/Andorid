package com.goody.dalda.data.dto

import com.goody.dalda.ui.model.Post

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

fun PostDto.asDomain() = Post(id, title, content, createdAt, updatedAt, isActive)
