package com.oyj.domain.model

data class PostEntity(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val isActive: Boolean,
)

