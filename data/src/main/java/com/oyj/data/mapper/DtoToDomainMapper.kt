package com.oyj.data.mapper

import com.oyj.data.dto.home.Beer
import com.oyj.data.dto.home.Data
import com.oyj.data.dto.home.Sake
import com.oyj.data.dto.home.Soju
import com.oyj.data.dto.home.TraditionalLiquor
import com.oyj.data.dto.home.Whisky
import com.oyj.data.dto.home.Wine
import com.oyj.data.dto.search.SearchData
import com.oyj.domain.model.AlcoholEntity

object DtoToDomainMapper {

    fun dataToAlcohol(data: List<Data>): List<AlcoholEntity> =
        data.map {
            when (it) {
                is Beer -> dataToBeer(it)
                is Sake -> dataToSake(it)
                is Soju -> dataToSoju(it)
                is TraditionalLiquor -> dataToTraditionalLiquor(it)
                is Whisky -> dataToWhisky(it)
                is Wine -> dataToWine(it)
            }
        }

    private fun dataToBeer(data: Data): AlcoholEntity.Beer =
        (data as Beer).let {
            AlcoholEntity.Beer(
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
                volume = "",
            )
        }

    private fun dataToSake(data: Data): AlcoholEntity.Sake =
        (data as Sake).let {
            AlcoholEntity.Sake(
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

    private fun dataToWhisky(data: Data): AlcoholEntity.Whisky =
        (data as Whisky).let {
            AlcoholEntity.Whisky(
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

    private fun dataToSoju(data: Data): AlcoholEntity.Soju =
        (data as Soju).let {
            AlcoholEntity.Soju(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                abv = it.abv,
                price = extractNumber(it.price),
                volume = it.volume,
                comment = it.comment,
                country = "대한민국",
            )
        }

    private fun dataToWine(data: Data): AlcoholEntity.Wine =
        (data as Wine).let {
            AlcoholEntity.Wine(
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
                abv = it.abv ?: "",
            )
        }

    private fun dataToTraditionalLiquor(data: Data): AlcoholEntity.TraditionalLiquor =
        (data as TraditionalLiquor).let {
            AlcoholEntity.TraditionalLiquor(
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
                country = "대한민국",
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

    fun searchResultDtoToSearchAlcoholData(searchResultDto: SearchData): List<AlcoholEntity> {
        return listOf(
            searchResultDto.soju,
            searchResultDto.beer,
            searchResultDto.sake,
            searchResultDto.wine,
            searchResultDto.whisky,
            searchResultDto.traditionalLiquor
        ).flatMap { alcoholList ->
            dataToAlcohol(alcoholList)
        }
    }
}