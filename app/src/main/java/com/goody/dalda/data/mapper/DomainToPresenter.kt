package com.goody.dalda.data.mapper

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.dto.PostDto
import com.goody.dalda.data.dto.ProfileData
import com.goody.dalda.ui.model.Post
import com.goody.dalda.ui.model.Profile
import com.oyj.domain.model.AlcoholEntity

object DomainToPresenter {
    fun AlcoholEntity.toAlcoholData() : AlcoholData {
        return when(this) {
            is AlcoholEntity.Beer -> AlcoholData.Beer(
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
            is AlcoholEntity.Sake -> AlcoholData.Sake(
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
            is AlcoholEntity.Soju -> AlcoholData.Soju(
                id = id,
                name = name,
                imgUrl = imgUrl,
                country = country,
                volume = volume,
                abv = abv,
                price = price,
                comment = comment
            )
            is AlcoholEntity.TraditionalLiquor -> AlcoholData.TraditionalLiquor(
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
            is AlcoholEntity.Whisky -> AlcoholData.Whisky(
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
            is AlcoholEntity.Wine -> AlcoholData.Wine(
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

    fun List<AlcoholEntity>.toAlcoholDataList(): List<AlcoholData> {
        return this.map { it.toAlcoholData() }
    }

    fun PostDto.asDomain() = Post(id, title, content, createdAt, updatedAt, isActive)

    fun ProfileData.asDomain() = Profile(nickname, email, profileImg)
}