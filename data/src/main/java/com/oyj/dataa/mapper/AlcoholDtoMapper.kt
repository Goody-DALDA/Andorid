package com.oyj.dataa.mapper

import com.oyj.dataa.dto.LeaveDto
import com.oyj.dataa.dto.LogoutDto
import com.oyj.dataa.dto.PostDto
import com.oyj.dataa.dto.ProfileData
import com.oyj.dataa.dto.home.Beer
import com.oyj.dataa.dto.home.Data
import com.oyj.dataa.dto.home.Sake
import com.oyj.dataa.dto.home.Soju
import com.oyj.dataa.dto.home.TraditionalLiquor
import com.oyj.dataa.dto.home.Whisky
import com.oyj.dataa.dto.home.Wine
import com.oyj.dataa.dto.search.SearchData
import com.oyj.domain.Alcohol
import com.oyj.domain.model.PostDomain
import com.oyj.domain.model.ProfileDomain
import com.oyj.domain.model.ResultMessageDomain

object AlcoholDtoMapper {

    fun dataToAlcohol(data: List<Data>): List<Alcohol> =
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

    private fun dataToBeer(data: Data): Alcohol.Beer =
        (data as Beer).let {
            Alcohol.Beer(
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

    private fun dataToSake(data: Data): Alcohol.Sake =
        (data as Sake).let {
            Alcohol.Sake(
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

    private fun dataToWhisky(data: Data): Alcohol.Whisky =
        (data as Whisky).let {
            Alcohol.Whisky(
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

    private fun dataToSoju(data: Data): Alcohol.Soju =
        (data as Soju).let {
            Alcohol.Soju(
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

    private fun dataToWine(data: Data): Alcohol.Wine =
        (data as Wine).let {
            Alcohol.Wine(
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

    private fun dataToTraditionalLiquor(data: Data): Alcohol.TraditionalLiquor =
        (data as TraditionalLiquor).let {
            Alcohol.TraditionalLiquor(
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

    fun searchResultDtoToSearchAlcoholData(searchResultDto: SearchData): List<Alcohol> {
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

    fun ProfileData.toDomain(): ProfileDomain {
        return ProfileDomain(
            nickname = this.nickname,
            email = this.email,
            profileImg = this.profileImg,
            isShowConfettiScreen = false
        )
    }

    fun LogoutDto.toResultMessageDomain(): ResultMessageDomain {
        return ResultMessageDomain(
            status = this.status,
            message = this.message,
        )
    }

    fun LeaveDto.toResultMessageDomain(): ResultMessageDomain {
        return ResultMessageDomain(
            status = this.status,
            message = this.message,
        )
    }

    fun PostDto.toPostDomain(): PostDomain {
        return PostDomain(
            id = this.id,
            content = this.content,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            title = this.title,
            isActive = this.isActive,
        )
    }
}