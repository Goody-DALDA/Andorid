package com.goody.dalda.data

import androidx.annotation.DrawableRes
import com.goody.dalda.R

sealed class AlcoholData(
    open val id: Int,
    open val name: String,
    open val imgUrl: String,
    open val country: String,
    open val volume: Int,
    open val abv: Float,
    @DrawableRes open val tag: Int,
) {
    data class Beer(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        @DrawableRes override val tag: Int = R.drawable.tag_beer,
        override val volume: Int = 0,
        override val abv: Float,
        val appearance: Float,
        val taste: Float,
        val mouthfeel: Float,
        val aroma: Float,
        val type: String,
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        tag = tag,
        volume = volume,
        abv = abv
    )

    data class Sake(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        @DrawableRes override val tag: Int = R.drawable.tag_sake,
        override val volume: Int,
        val price: Int,
        override val abv: Float,
        val taste: String,
        val aroma: String,
        val finish: String,
        val type: String
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        tag = tag,
        volume = volume,
        abv = abv
    )

    data class Soju(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String = "대한민국",
        @DrawableRes override val tag: Int = R.drawable.tag_soju,
        override val volume: Int,
        val price: Int,
        override val abv: Float,
        val comment: String,
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        tag = tag,
        volume = volume,
        abv = abv
    )

    data class TraditionalLiquor(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String = "대한민국",
        @DrawableRes override val tag: Int = R.drawable.tag_traditional_liquor,
        override val volume: Int,
        val ingredient: String,
        override val abv: Float,
        val type: String,
        val comment: String,
        val pairingFood: String,
        val brewery: String
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        tag = tag,
        volume = volume,
        abv = abv
    )

    data class Wine(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        @DrawableRes override val tag: Int = R.drawable.tag_wine,
        override val volume: Int,
        override val abv: Float = 0f,
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
        country = country,
        tag = tag,
        volume = volume,
        abv = abv
    )

    data class Whiskey(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        @DrawableRes override val tag: Int = R.drawable.tag_whiskey,
        override val volume: Int,
        val price: Int,
        override val abv: Float,
        val taste: String,
        val aroma: String,
        val finish: String,
        val type: String
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        tag = tag,
        volume = volume,
        abv = abv
    )
}
