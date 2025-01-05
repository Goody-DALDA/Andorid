package com.goody.dalda.data

import androidx.annotation.DrawableRes
import com.goody.dalda.R
import kotlinx.serialization.Serializable

@Serializable
sealed class AlcoholData(
    open val id: Int,
    open val name: String,
    open val imgUrl: String,
    open val country: String,
    open val volume: String,
    open val abv: String,
    @DrawableRes open val tag: Int,
) : java.io.Serializable {
    data class Beer(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        override val volume: String = "0ml",
        override val abv: String,
        @DrawableRes override val tag: Int = R.drawable.tag_beer,
        val appearance: Float,
        val flavor: Float,
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
        override val volume: String,
        override val abv: String,
        @DrawableRes override val tag: Int = R.drawable.tag_sake,
        val price: Int,
        val taste: String,
        val aroma: String,
        val finish: String,
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
        override val volume: String,
        override val abv: String,
        @DrawableRes override val tag: Int = R.drawable.tag_soju,
        val price: Int,
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
        override val volume: String,
        override val abv: String,
        @DrawableRes override val tag: Int = R.drawable.tag_traditional_liquor,
        val ingredient: String,
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
        override val volume: String,
        override val abv: String,
        @DrawableRes override val tag: Int = R.drawable.tag_wine,
        val ingredient: String,
        val mouthfeel: Float,
        val sugar: Float,
        val acid: Float,
        val type: String,
        val comment: String,
        val pairingFood: String,
        val winery: String
    ) : AlcoholData(
        id = id,
        name = name,
        imgUrl = imgUrl,
        country = country,
        tag = tag,
        volume = volume,
        abv = abv
    )

    data class Wisky(
        override val id: Int,
        override val name: String,
        override val imgUrl: String,
        override val country: String,
        override val volume: String,
        override val abv: String,
        @DrawableRes override val tag: Int = R.drawable.tag_whiskey,
        val price: Int,
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
