package com.oyj.data.dto

data class LogoutDto(
    val status: String,
    val message: String,
) {
    fun isSuccess() = "SUCCESS" == status
}
