package com.goody.dalda.data.repository.home

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.dto.home.AlcoholDataDto
import com.goody.dalda.data.dto.home.Beer
import com.goody.dalda.data.dto.home.Sake
import com.goody.dalda.data.dto.home.Soju
import com.goody.dalda.data.dto.home.TraditionalLiquor
import com.goody.dalda.data.dto.home.Wine
import com.goody.dalda.data.dto.home.Wisky
import com.goody.dalda.data.dto.search.SearchData
import com.goody.dalda.data.mapper.AlcoholDataMapper.dataToAlcoholData
import com.goody.dalda.data.remote.home.AlcoholDataRemoteDataSource
import com.goody.dalda.data.repository.SearchAlcoholData
import javax.inject.Inject

class AlcoholRepositoryImpl @Inject constructor(
    private val alcoholDataRemoteDataSource: AlcoholDataRemoteDataSource
) : AlcoholRepository {
    override suspend fun getAlcoholData(category: String): List<AlcoholData> {
        val response = alcoholDataRemoteDataSource.getAlcoholData(
            category = category
        )

        if (response.isSuccessful) {
            val alcoholDataDto = requireNotNull(response.body()) { "Response body is null" }
            return alcoholDataDtoToAlcoholData(category, alcoholDataDto)
        } else {
            throw Exception("Failed to get alcohol info")
        }
    }

    override suspend fun getSearchedAlcoholData(query: String): SearchAlcoholData {
        val response = alcoholDataRemoteDataSource.getSearchedAlcoholData(query = query)

        if (response.isSuccessful) {
            val searchResultDto = requireNotNull(response.body()) { "Response body is null" }
            return searchResultDtoToSearchAlcoholData(searchResultDto.searchData)
        } else {
            throw Exception("Failed to get searched alcohol info")
        }
    }

    override suspend fun getRecommendAlcoholList(query: String): List<String> {
        val response = alcoholDataRemoteDataSource.getRecommendAlcoholList(query)

        if (response.isSuccessful) {
            val searchResultDto = requireNotNull(response.body()) { "Response body is null" }
            return searchResultDto.recommendAlcoholInfoList.map {
                it.name
            }
        } else {
            throw Exception("Failed to get recommend alcohol list")
        }
    }

    private fun alcoholDataDtoToAlcoholData(
        category: String,
        alcoholDataDto: AlcoholDataDto
    ): List<AlcoholData> {
        when (category.lowercase()) {
            "soju" -> {
                return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<Soju>())
            }

            "wisky" -> {
                return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<Wisky>())
            }

            "beer" -> {
                return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<Beer>())
            }

            "wine" -> {
                return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<Wine>())
            }

            "traditionalliquor" -> {
                return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<TraditionalLiquor>())
            }

            "sake" -> {
                return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<Sake>())
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
