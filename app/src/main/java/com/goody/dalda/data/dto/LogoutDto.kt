package com.goody.dalda.data.dto

data class LogoutDto(
    val status: String,
    val message: String
) {
    fun isSuccess() = "SUCCESS" == status
}