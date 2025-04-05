package com.oyj.data.dto

data class ProfileDto(
    val status: String,
    val message: String,
    val data: ProfileData?,
)

data class ProfileData(
    val nickname: String,
    val email: String,
    val profileImg: String,
)

