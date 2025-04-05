package com.oyj.domain.model

data class ProfileDomain(
    val nickname: String = "",
    val email: String = "",
    val profileImg: String = "",
    val isShowConfettiScreen: Boolean = false,
)