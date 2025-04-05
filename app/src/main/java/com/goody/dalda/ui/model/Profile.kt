package com.goody.dalda.ui.model

import com.oyj.domain.model.ProfileDomain

data class Profile(
    val nickname: String = "",
    val email: String = "",
    val profileImg: String = "",
    val isShowConfettiScreen: Boolean = false,
)

fun ProfileDomain.toAppModel() : Profile {
    return Profile(
        nickname = this.nickname,
        email = this.email,
        profileImg = this.profileImg,
        isShowConfettiScreen = this.isShowConfettiScreen,
    )
}