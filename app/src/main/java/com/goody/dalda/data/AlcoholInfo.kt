package com.goody.dalda.data

data class AlcoholInfo(
    val id: Int,
    val name: String,
    val type: AlcoholType,
    val abv: Float,
    val starScore: Float
)
