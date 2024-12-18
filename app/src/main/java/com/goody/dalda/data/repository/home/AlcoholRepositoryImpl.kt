package com.goody.dalda.data.repository.home

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.dto.home.AlcoholInfoDto
import com.goody.dalda.data.dto.home.Beer
import com.goody.dalda.data.dto.home.Sake
import com.goody.dalda.data.dto.home.Soju
import com.goody.dalda.data.dto.home.TraditionalLiquor
import com.goody.dalda.data.dto.home.Whisky
import com.goody.dalda.data.dto.home.Wine
import com.goody.dalda.data.mapper.AlcoholInfoMapper.dataToAlcoholData
import com.goody.dalda.data.remote.home.AlcoholInfoRemoteDataSource
import javax.inject.Inject

class AlcoholRepositoryImpl @Inject constructor(
    private val alcoholInfoRemoteDataSource: AlcoholInfoRemoteDataSource
) : AlcoholRepository {
    override suspend fun getAlcoholInfo(category: String): List<AlcoholData> {
        val response = alcoholInfoRemoteDataSource.getAlcoholInfo(
            category = category
        )

        if (response.isSuccessful) {
            val alcoholInfoDto = requireNotNull(response.body()) { "Response body is null" }
            return alcoholInfoDtoToAlcoholData(category, alcoholInfoDto)
        } else {
            throw Exception("Failed to get alcohol info")
        }
    }

    private fun alcoholInfoDtoToAlcoholData(
        category: String,
        alcoholInfoDto: AlcoholInfoDto
    ): List<AlcoholData> {
        when (category.lowercase()) {
            "soju" -> {
                return dataToAlcoholData(alcoholInfoDto.data.filterIsInstance<Soju>())
            }

            "whisky" -> {
                return dataToAlcoholData(alcoholInfoDto.data.filterIsInstance<Whisky>())
            }

            "beer" -> {
                return dataToAlcoholData(alcoholInfoDto.data.filterIsInstance<Beer>())
            }

            "wine" -> {
                return dataToAlcoholData(alcoholInfoDto.data.filterIsInstance<Wine>())
            }

            "traditionalLiquor" -> {
                return dataToAlcoholData(alcoholInfoDto.data.filterIsInstance<TraditionalLiquor>())
            }

            "sake" -> {
                return dataToAlcoholData(alcoholInfoDto.data.filterIsInstance<Sake>())
            }

            else -> {
                throw Exception("empty category")
            }
        }
    }


}
