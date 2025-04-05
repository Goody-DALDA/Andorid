package com.oyj.data.dto

data class LeaveDto(
    val status: String,
    val message: String,
) {
    fun isSuccess() = "SUCCESS" == status
}
