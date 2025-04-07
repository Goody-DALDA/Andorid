package com.goody.dalda.data.model

import com.oyj.domain.model.ProfileEntity

data class Profile(
    val nickname: String = "",
    val email: String = "",
    val profileImg: String = "",
    val isShowConfettiScreen: Boolean = false,
)

fun ProfileEntity.toUIModel() : Profile {
    return Profile(
        nickname = this.nickname,
        email = this.email,
        profileImg = this.profileImg,
        isShowConfettiScreen = this.isShowConfettiScreen,
    )
}