package com.goody.dalda.data

import androidx.annotation.DrawableRes
import com.goody.dalda.R
import com.oyj.domain.model.AlcoholEntity
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
            abv = abv,
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
            abv = abv,
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
            abv = abv,
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
        val brewery: String,
    ) : AlcoholData(
            id = id,
            name = name,
            imgUrl = imgUrl,
            country = country,
            tag = tag,
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
        @DrawableRes override val tag: Int = R.drawable.tag_wine,
        val ingredient: String,
        val mouthfeel: Float,
        val sugar: Float,
        val acid: Float,
        val type: String,
        val comment: String,
        val pairingFood: String,
        val winery: String,
    ) : AlcoholData(
            id = id,
            name = name,
            imgUrl = imgUrl,
            country = country,
            tag = tag,
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
        @DrawableRes override val tag: Int = R.drawable.tag_whiskey,
        val price: Int,
        val taste: String,
        val aroma: String,
        val finish: String,
        val type: String,
    ) : AlcoholData(
            id = id,
            name = name,
            imgUrl = imgUrl,
            country = country,
            tag = tag,
            volume = volume,
            abv = abv,
        )
}

/**
 * App 모델 -> Domain 모델
 * */
fun AlcoholData.toDomainModel(): AlcoholEntity = when (this) {
    is AlcoholData.Beer -> AlcoholEntity.Beer(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        appearance = this.appearance,
        flavor = this.flavor,
        mouthfeel = this.mouthfeel,
        aroma = this.aroma,
        type = this.type
    )
    is AlcoholData.Sake -> AlcoholEntity.Sake(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        price = this.price,
        taste = this.taste,
        aroma = this.aroma,
        finish = this.finish
    )
    is AlcoholData.Soju -> AlcoholEntity.Soju(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        price = this.price,
        comment = this.comment
    )
    is AlcoholData.TraditionalLiquor -> AlcoholEntity.TraditionalLiquor(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        ingredient = this.ingredient,
        type = this.type,
        comment = this.comment,
        pairingFood = this.pairingFood,
        brewery = this.brewery
    )
    is AlcoholData.Wine -> AlcoholEntity.Wine(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        ingredient = this.ingredient,
        mouthfeel = this.mouthfeel,
        sugar = this.sugar,
        acid = this.acid,
        type = this.type,
        comment = this.comment,
        pairingFood = this.pairingFood,
        winery = this.winery
    )
    is AlcoholData.Whisky -> AlcoholEntity.Whisky(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        price = this.price,
        taste = this.taste,
        aroma = this.aroma,
        finish = this.finish,
        type = this.type
    )
}

/**
 * Domain 모델 -> App 모델
 * */
fun AlcoholEntity.toDataModel(): AlcoholData = when (this) {
    is AlcoholEntity.Beer -> AlcoholData.Beer(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        appearance = this.appearance,
        flavor = this.flavor,
        mouthfeel = this.mouthfeel,
        aroma = this.aroma,
        type = this.type,
        tag = R.drawable.tag_beer
    )
    is AlcoholEntity.Sake -> AlcoholData.Sake(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        price = this.price,
        taste = this.taste,
        aroma = this.aroma,
        finish = this.finish,
        tag = R.drawable.tag_sake
    )
    is AlcoholEntity.Soju -> AlcoholData.Soju(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        price = this.price,
        comment = this.comment,
        tag = R.drawable.tag_soju
    )
    is AlcoholEntity.TraditionalLiquor -> AlcoholData.TraditionalLiquor(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        ingredient = this.ingredient,
        type = this.type,
        comment = this.comment,
        pairingFood = this.pairingFood,
        brewery = this.brewery,
        tag = R.drawable.tag_traditional_liquor
    )
    is AlcoholEntity.Wine -> AlcoholData.Wine(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        ingredient = this.ingredient,
        mouthfeel = this.mouthfeel,
        sugar = this.sugar,
        acid = this.acid,
        type = this.type,
        comment = this.comment,
        pairingFood = this.pairingFood,
        winery = this.winery,
        tag = R.drawable.tag_wine
    )
    is AlcoholEntity.Whisky -> AlcoholData.Whisky(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        price = this.price,
        taste = this.taste,
        aroma = this.aroma,
        finish = this.finish,
        type = this.type,
        tag = R.drawable.tag_whiskey
    )
}

/**
 * Domain 모델 List -> App 모델 List
 * */
fun List<AlcoholEntity>.toDataModelList(): List<AlcoholData> {
    return this.map { it.toDataModel() }
}