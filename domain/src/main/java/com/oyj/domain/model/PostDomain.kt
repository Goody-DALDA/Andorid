package com.oyj.domain.model

data class PostDomain(
    val id: Int,
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val isActive: Boolean,
)

