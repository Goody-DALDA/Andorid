package com.goody.dalda.data.model

import androidx.annotation.DrawableRes
import com.goody.dalda.R
import com.oyj.domain.model.AlcoholEntity
import kotlinx.serialization.Serializable

@Serializable
sealed class AlcoholUIModel(
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
    ) : AlcoholUIModel(
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
    ) : AlcoholUIModel(
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
    ) : AlcoholUIModel(
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
    ) : AlcoholUIModel(
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
    ) : AlcoholUIModel(
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
    ) : AlcoholUIModel(
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
fun AlcoholUIModel.toDomainModel(): AlcoholEntity = when (this) {
    is AlcoholUIModel.Beer -> AlcoholEntity.Beer(
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
    is AlcoholUIModel.Sake -> AlcoholEntity.Sake(
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
    is AlcoholUIModel.Soju -> AlcoholEntity.Soju(
        id = this.id,
        name = this.name,
        imgUrl = this.imgUrl,
        country = this.country,
        volume = this.volume,
        abv = this.abv,
        price = this.price,
        comment = this.comment
    )
    is AlcoholUIModel.TraditionalLiquor -> AlcoholEntity.TraditionalLiquor(
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
    is AlcoholUIModel.Wine -> AlcoholEntity.Wine(
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
    is AlcoholUIModel.Whisky -> AlcoholEntity.Whisky(
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
fun AlcoholEntity.toDataModel(): AlcoholUIModel = when (this) {
    is AlcoholEntity.Beer -> AlcoholUIModel.Beer(
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
    is AlcoholEntity.Sake -> AlcoholUIModel.Sake(
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
    is AlcoholEntity.Soju -> AlcoholUIModel.Soju(
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
    is AlcoholEntity.TraditionalLiquor -> AlcoholUIModel.TraditionalLiquor(
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
    is AlcoholEntity.Wine -> AlcoholUIModel.Wine(
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
    is AlcoholEntity.Whisky -> AlcoholUIModel.Whisky(
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
fun List<AlcoholEntity>.toDataModelList(): List<AlcoholUIModel> {
    return this.map { it.toDataModel() }
}