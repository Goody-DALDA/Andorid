package com.goody.dalda.data.mapper

import com.goody.dalda.data.model.AlcoholUIModel
import com.oyj.domain.model.AlcoholEntity

object DomainToPresenter {
    private fun AlcoholEntity.toAlcoholData() : AlcoholUIModel {
        return when(this) {
            is AlcoholEntity.Beer -> AlcoholUIModel.Beer(
                id = id,
                name = name,
                imgUrl = imgUrl,
                country = country,
                volume = volume,
                abv = abv,
                appearance = appearance,
                flavor = flavor,
                mouthfeel = mouthfeel,
                aroma = aroma,
                type = type
            )
            is AlcoholEntity.Sake -> AlcoholUIModel.Sake(
                id = id,
                name = name,
                imgUrl = imgUrl,
                country = country,
                volume = volume,
                abv = abv,
                price = price,
                taste = taste,
                aroma = aroma,
                finish = finish
            )
            is AlcoholEntity.Soju -> AlcoholUIModel.Soju(
                id = id,
                name = name,
                imgUrl = imgUrl,
                country = country,
                volume = volume,
                abv = abv,
                price = price,
                comment = comment
            )
            is AlcoholEntity.TraditionalLiquor -> AlcoholUIModel.TraditionalLiquor(
                id = id,
                name = name,
                imgUrl = imgUrl,
                country = country,
                volume = volume,
                abv = abv,
                ingredient = ingredient,
                type = type,
                comment = comment,
                pairingFood = pairingFood,
                brewery = brewery
            )
            is AlcoholEntity.Whisky -> AlcoholUIModel.Whisky(
                id = id,
                name = name,
                imgUrl = imgUrl,
                country = country,
                volume = volume,
                abv = abv,
                price = price,
                taste = taste,
                aroma = aroma,
                finish = finish,
                type = type
            )
            is AlcoholEntity.Wine -> AlcoholUIModel.Wine(
                id = id,
                name = name,
                imgUrl = imgUrl,
                country = country,
                volume = volume,
                abv = abv,
                ingredient = ingredient,
                mouthfeel = mouthfeel,
                sugar = sugar,
                acid = acid,
                type = type,
                comment = comment,
                pairingFood = pairingFood,
                winery = winery
            )
        }
    }

    fun List<AlcoholEntity>.toAlcoholDataList(): List<AlcoholUIModel> {
        return this.map { it.toAlcoholData() }
    }
}