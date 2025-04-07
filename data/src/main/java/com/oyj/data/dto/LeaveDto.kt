package com.oyj.data.dto

import com.oyj.domain.model.ResultMessageEntity

data class LeaveDto(
    val status: String,
    val message: String,
)

fun LeaveDto.toResultMessageDomain(): ResultMessageEntity {
    return ResultMessageEntity(
        status = this.status,
        message = this.message,
    )
}