package com.goody.dalda.data.mapper

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.dto.home.Beer
import com.goody.dalda.data.dto.home.Data
import com.goody.dalda.data.dto.home.Sake
import com.goody.dalda.data.dto.home.Soju
import com.goody.dalda.data.dto.home.TraditionalLiquor
import com.goody.dalda.data.dto.home.Wine
import com.goody.dalda.data.dto.home.whisky

object AlcoholDataMapper {
    fun dataToAlcoholData(data: List<Data>): List<AlcoholData> =
        data.map {
            when (it) {
                is Beer -> dataToBeer(it)
                is Sake -> dataToSake(it)
                is whisky -> dataToWhisky(it)
                is Soju -> dataToSoju(it)
                is Wine -> dataToWine(it)
                is TraditionalLiquor -> dataToTraditionalLiquor(it)
            }
        }

    private fun dataToBeer(data: Data): AlcoholData.Beer =
        (data as Beer).let {
            AlcoholData.Beer(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                country = it.country,
                abv = it.abv,
                appearance = it.appearance,
                flavor = it.flavor,
                mouthfeel = it.mouthfeel,
                aroma = it.aroma,
                type = it.type,
            )
        }

    private fun dataToSake(data: Data): AlcoholData.Sake =
        (data as Sake).let {
            if (it.taste.isEmpty()) {
                println("dataToSake: ${it.name}")
            }
            AlcoholData.Sake(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                country = it.country,
                abv = it.abv,
                price = extractNumber(it.price),
                taste = it.taste.ifEmpty { "" },
                aroma = it.aroma.ifEmpty { "" },
                finish = it.finish.ifEmpty { "" },
                volume = it.volume,
            )
        }

    private fun dataToWhisky(data: Data): AlcoholData.Whisky =
        (data as whisky).let {
            AlcoholData.Whisky(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                country = it.country,
                abv = it.abv,
                aroma = it.aroma,
                finish = it.finish,
                price = extractNumber(it.price),
                taste = it.taste,
                type = it.type,
                volume = it.volume,
            )
        }

    private fun dataToSoju(data: Data): AlcoholData.Soju =
        (data as Soju).let {
            AlcoholData.Soju(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                abv = it.abv,
                price = extractNumber(it.price),
                volume = it.volume,
                comment = it.comment,
            )
        }

    private fun dataToWine(data: Data): AlcoholData.Wine =
        (data as Wine).let {
            AlcoholData.Wine(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                country = it.country,
                acid = it.acid,
                mouthfeel = it.body,
                comment = it.comment,
                ingredient = it.ingredients,
                pairingFood = it.pairingFood,
                sugar = it.sugar,
                type = it.type,
                volume = it.volume,
                winery = it.winery,
                abv = "",
            )
        }

    private fun dataToTraditionalLiquor(data: Data): AlcoholData.TraditionalLiquor =
        (data as TraditionalLiquor).let {
            AlcoholData.TraditionalLiquor(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                abv = it.abv,
                volume = it.volume,
                comment = it.comment,
                ingredient = it.ingredients,
                type = it.type,
                pairingFood = it.pairingFood,
                brewery = it.brewery,
            )
        }

    private fun extractNumber(strPrice: String): Int =
        if (strPrice.isEmpty()) {
            0
        } else {
            strPrice
                .replace(
                    regex = "[^0-9]".toRegex(),
                    replacement = "",
                ).toInt()
        }
}
