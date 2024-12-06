package com.goody.dalda.data.dto

data class LoginDto(
    val status: String,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val jwtToken: String,
    val refreshToken: String
)
