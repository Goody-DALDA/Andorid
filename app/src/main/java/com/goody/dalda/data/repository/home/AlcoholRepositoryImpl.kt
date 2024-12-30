package com.goody.dalda.data.repository.home

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.dto.home.AlcoholInfoDto
import com.goody.dalda.data.dto.home.Beer
import com.goody.dalda.data.dto.home.Sake
import com.goody.dalda.data.dto.home.Soju
import com.goody.dalda.data.dto.home.TraditionalLiquor
import com.goody.dalda.data.dto.home.Wine
import com.goody.dalda.data.dto.home.Wisky
import com.goody.dalda.data.dto.search.SearchData
import com.goody.dalda.data.mapper.AlcoholInfoMapper.dataToAlcoholData
import com.goody.dalda.data.remote.home.AlcoholInfoRemoteDataSource
import com.goody.dalda.data.repository.SearchAlcoholData
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

    override suspend fun getSearchedAlcoholInfo(query: String): SearchAlcoholData {
        val response = alcoholInfoRemoteDataSource.getSearchedAlcoholInfo(query = query)

        if (response.isSuccessful) {
            val searchResultDto = requireNotNull(response.body()) { "Response body is null" }
            return searchResultDtoToSearchAlcoholData(searchResultDto.searchData)
        } else {
            throw Exception("Failed to get searched alcohol info")
        }
    }

    override suspend fun getRecommendAlcoholList(query: String): List<String> {
        val response = alcoholInfoRemoteDataSource.getRecommendAlcoholList(query)

        if (response.isSuccessful) {
            val searchResultDto = requireNotNull(response.body()) { "Response body is null" }
            return searchResultDto.recommendAlcoholInfoList.map {
                it.name
            }
        } else {
            throw Exception("Failed to get recommend alcohol list")
        }
    }

    private fun alcoholInfoDtoToAlcoholData(
        category: String,
        alcoholInfoDto: AlcoholInfoDto
    ): List<AlcoholData> {
        when (category.lowercase()) {
            "soju" -> {
                return dataToAlcoholData(alcoholInfoDto.alcoholInfoData.filterIsInstance<Soju>())
            }

            "wisky" -> {
                return dataToAlcoholData(alcoholInfoDto.alcoholInfoData.filterIsInstance<Wisky>())
            }

            "beer" -> {
                return dataToAlcoholData(alcoholInfoDto.alcoholInfoData.filterIsInstance<Beer>())
            }

            "wine" -> {
                return dataToAlcoholData(alcoholInfoDto.alcoholInfoData.filterIsInstance<Wine>())
            }

            "traditionalliquor" -> {
                return dataToAlcoholData(alcoholInfoDto.alcoholInfoData.filterIsInstance<TraditionalLiquor>())
            }

            "sake" -> {
                return dataToAlcoholData(alcoholInfoDto.alcoholInfoData.filterIsInstance<Sake>())
            }

            else -> {
                throw Exception("empty category")
            }
        }
    }

    private fun searchResultDtoToSearchAlcoholData(searchResultDto: SearchData): SearchAlcoholData {
        return SearchAlcoholData(
            sojuList = dataToAlcoholData(searchResultDto.soju).map { it as AlcoholData.Soju },
            beerList = dataToAlcoholData(searchResultDto.beer).map { it as AlcoholData.Beer },
            sakeList = dataToAlcoholData(searchResultDto.sake).map { it as AlcoholData.Sake },
            wineList = dataToAlcoholData(searchResultDto.wine).map { it as AlcoholData.Wine },
            wiskyList = dataToAlcoholData(searchResultDto.wisky).map { it as AlcoholData.Wisky },
            traditionalLiquorList = dataToAlcoholData(searchResultDto.traditionalLiquor).map { it as AlcoholData.TraditionalLiquor }
        )
    }

}
