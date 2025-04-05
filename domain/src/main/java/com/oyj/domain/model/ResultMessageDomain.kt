package com.oyj.domain.model

data class ResultMessageDomain(
    val status: String,
    val message: String
) {
    fun isSuccess() = "SUCCESS" == status
}
