package com.goody.dalda.data

sealed class AlcoholData(
    open val id: Int,
    open val name: String,
    open val imgUrl: String,
    open val country: String
) {
    data class Beer(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        val volume: String = "다양한 용량",
        val abv: Float,
        val appearance: Float,
        val taste: Float,
        val mouthfeel: Float,
        val aroma: Float,
        val type: String
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country
    )

    data class Sake(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        val volume: Int,
        val price: Int,
        val abv: Float,
        val taste: String,
        val aroma: String,
        val finish: String,
        val type: String
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country
    )

    data class Soju(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String = "대한민국",
        val volume: Int,
        val price: Int,
        val abv: Float,
        val comment: String,
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country
    )

    data class TraditionalLiquor(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String = "대한민국",
        val volume: Int,
        val ingredient: String,
        val abv: Float,
        val type: String,
        val comment: String,
        val pairingFood: String,
        val brewery: String
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country
    )

    data class Wine(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        val volume: Int,
        val ingredient: String,
        val mouthfeel: Float,
        val sugar: Float,
        val acid: Float,
        val type: String,
        val comment: String,
        val pairingFood: String,
        val brewery: String
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country
    )

    data class Whiskey(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        val volume: Int,
        val price: Int,
        val abv: Float,
        val taste: String,
        val aroma: String,
        val finish: String,
        val type: String
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country
    )
}
