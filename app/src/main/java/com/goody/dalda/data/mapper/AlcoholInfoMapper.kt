package com.goody.dalda.data.mapper

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.dto.home.Beer
import com.goody.dalda.data.dto.home.Data
import com.goody.dalda.data.dto.home.Sake
import com.goody.dalda.data.dto.home.Soju
import com.goody.dalda.data.dto.home.TraditionalLiquor
import com.goody.dalda.data.dto.home.Whisky
import com.goody.dalda.data.dto.home.Wine

object AlcoholInfoMapper {
    fun dataToAlcoholData(data: List<Data>): List<AlcoholData> {
        return data.map {
            when (it) {
                is Beer -> dataToBeer(it)
                is Sake -> dataToSake(it)
                is Whisky -> dataToWhisky(it)
                is Soju -> dataToSoju(it)
                is Wine -> dataToWine(it)
                is TraditionalLiquor -> dataToTraditionalLiquor(it)
            }
        }
    }

    fun dataToBeer(data: Data): AlcoholData.Beer {
        return (data as Beer).let {
            AlcoholData.Beer(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                country = it.country,
                abv = strAbvToFloatAbv(it.abv),
                appearance = it.appearance,
                flavor = it.flavor,
                mouthfeel = it.mouthfeel,
                aroma = it.aroma,
                type = it.type,
            )
        }
    }

    fun dataToSake(data: Data): AlcoholData.Sake {
        return (data as Sake).let {
            AlcoholData.Sake(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                country = it.country,
                abv = strAbvToFloatAbv(it.abv),
                price = it.price.toInt(),
                taste = it.taste,
                aroma = it.aroma,
                finish = it.finish,
                volume = strVolumeToIntVolume(it.volume)
            )
        }
    }

    fun dataToWhisky(data: Data): AlcoholData.Whisky {
        return (data as Whisky).let {
            AlcoholData.Whisky(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                country = it.country,
                abv = strAbvToFloatAbv(it.abv),
                aroma = it.aroma,
                finish = it.finish,
                price = it.price.toInt(),
                taste = it.taste,
                type = it.type,
                volume = strVolumeToIntVolume(it.volume)
            )
        }
    }

    fun dataToSoju(data: Data): AlcoholData.Soju {
        return (data as Soju).let {
            AlcoholData.Soju(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                abv = strAbvToFloatAbv(it.abv),
                price = it.price.toInt(),
                volume = strVolumeToIntVolume(it.volume),
                comment = it.comment,
            )
        }
    }

    fun dataToWine(data: Data): AlcoholData.Wine {
        return (data as Wine).let {
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
                volume = strVolumeToIntVolume(it.volume),
                winery = it.winery,
            )
        }
    }

    fun dataToTraditionalLiquor(data: Data): AlcoholData.TraditionalLiquor {
        return (data as TraditionalLiquor).let {
            AlcoholData.TraditionalLiquor(
                id = it.id,
                name = it.name,
                imgUrl = it.img,
                abv = strAbvToFloatAbv(it.abv),
                volume = strVolumeToIntVolume(it.volume),
                comment = it.comment,
                ingredient = it.ingredients,
                type = it.type,
                pairingFood = it.pairingFood,
                brewery = it.brewery,
            )
        }
    }

    private fun strAbvToFloatAbv(adv: String): Float {
        return adv.substring(0, adv.length - 1).toFloat()
    }

    private fun strVolumeToIntVolume(volume: String): Int {
        return volume.substring(0, volume.length - 2).toInt()
    }
}


