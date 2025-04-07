package com.oyj.domain.model

data class ResultMessageEntity(
    val status: String,
    val message: String
) {
    fun isSuccess() = "SUCCESS" == status
}
