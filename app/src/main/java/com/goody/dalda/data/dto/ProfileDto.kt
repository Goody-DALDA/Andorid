package com.goody.dalda.data.dto

import com.goody.dalda.ui.model.Profile

data class ProfileDto(
    val status: String,
    val message: String,
    val data: ProfileData,
)

data class ProfileData(
    val nickname: String,
    val email: String,
    val profileImg: String,
)

fun ProfileData.asDomain() = Profile(nickname, email, profileImg)
