package com.oyj.data.dto

data class LoginDto(
    val status: String,
    val message: String,
    val data: LoginData,
)

data class LoginData(
    val accessToken: String,
    val isNewUser: Boolean,
)
