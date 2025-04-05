package com.oyj.dataa.dto

data class LogoutDto(
    val status: String,
    val message: String,
) {
    fun isSuccess() = "SUCCESS" == status
}
