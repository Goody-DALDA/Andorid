package com.goody.dalda.data.repository

import com.goody.dalda.data.AlcoholData

data class SearchAlcoholData(
    val sojuList: List<AlcoholData.Soju> = emptyList(),
    val beerList: List<AlcoholData.Beer> = emptyList(),
    val sakeList: List<AlcoholData.Sake> = emptyList(),
    val wineList: List<AlcoholData.Wine> = emptyList(),
    val wiskyList: List<AlcoholData.Wisky> = emptyList(),
    val traditionalLiquorList: List<AlcoholData.TraditionalLiquor> = emptyList(),
) {
    fun getAllAlcoholData() =
        sojuList.asSequence().plus(beerList).plus(sakeList).plus(wineList).plus(wiskyList)
            .plus(traditionalLiquorList)
            .toList()
}
