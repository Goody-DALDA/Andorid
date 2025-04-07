package com.oyj.data.dto

import com.oyj.domain.model.ResultMessageEntity

data class LogoutDto(
    val status: String,
    val message: String,
)

fun LogoutDto.toResultMessageDomain(): ResultMessageEntity {
    return ResultMessageEntity(
        status = this.status,
        message = this.message,
    )
}