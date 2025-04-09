package com.oyj.domain.model

data class ProfileEntity(
    val nickname: String = "",
    val email: String = "",
    val profileImg: String = "",
    val isShowConfettiScreen: Boolean = false,
)