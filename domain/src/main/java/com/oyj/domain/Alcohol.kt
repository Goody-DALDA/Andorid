package com.oyj.domain

sealed class Alcohol(
    open val id: Int,
    open val name: String,
    open val imgUrl: String,
    open val country: String,
    open val volume: String,
    open val abv: String,
) {
    data class Beer(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        override val volume: String,
        override val abv: String,
        val appearance: Float,
        val flavor: Float,
        val mouthfeel: Float,
        val aroma: Float,
        val type: String,
    ) : Alcohol(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        volume = volume,
        abv = abv,
    )

    data class Sake(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        override val volume: String,
        override val abv: String,
        val price: Int,
        val taste: String,
        val aroma: String,
        val finish: String,
    ) : Alcohol(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        volume = volume,
        abv = abv,
    )

    data class Soju(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        override val volume: String,
        override val abv: String,
        val price: Int,
        val comment: String,
    ) : Alcohol(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        volume = volume,
        abv = abv,
    )

    data class TraditionalLiquor(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        override val volume: String,
        override val abv: String,
        val ingredient: String,
        val type: String,
        val comment: String,
        val pairingFood: String,
        val brewery: String,
    ) : Alcohol(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        volume = volume,
        abv = abv,
    )

    data class Wine(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        override val volume: String,
        override val abv: String,
        val ingredient: String,
        val mouthfeel: Float,
        val sugar: Float,
        val acid: Float,
        val type: String,
        val comment: String,
        val pairingFood: String,
        val winery: String,
    ) : Alcohol(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        volume = volume,
        abv = abv,
    )

    data class Whisky(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        override val volume: String,
        override val abv: String,
        val price: Int,
        val taste: String,
        val aroma: String,
        val finish: String,
        val type: String,
    ) : Alcohol(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        volume = volume,
        abv = abv,
    )
}
