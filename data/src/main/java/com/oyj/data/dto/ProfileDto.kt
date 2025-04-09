package com.oyj.data.dto

import com.oyj.domain.model.ProfileEntity

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

fun ProfileData.toDomain(): ProfileEntity {
    return ProfileEntity(
        nickname = this.nickname,
        email = this.email,
        profileImg = this.profileImg,
        isShowConfettiScreen = false
    )
}