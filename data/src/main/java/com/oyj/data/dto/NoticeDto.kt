package com.oyj.data.dto

import com.oyj.domain.model.PostEntity


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

fun PostDto.toDomain(): PostEntity {
    return PostEntity(
        id = this.id,
        content = this.content,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        title = this.title,
        isActive = this.isActive,
    )
}