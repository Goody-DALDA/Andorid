package com.goody.dalda.data.local

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.database.dao.BookmarkDao
import com.goody.dalda.data.database.entity.BookmarkEntity
import javax.inject.Inject

class BookmarkLocalDataSourceImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao,
) : BookmarkLocalDataSource {
    override suspend fun insertAlcohol(alcoholData: AlcoholData) {
        val bookmarkEntity = alcoholDataToBookmarkEntity(alcoholData)
        return bookmarkDao.insertAlcohol(bookmarkEntity)
    }

    override suspend fun deleteAlcohol(alcoholData: AlcoholData) {
        val bookmarkEntity = alcoholDataToBookmarkEntity(alcoholData)
        return bookmarkDao.deleteAlcohol(bookmarkEntity)
    }

    override suspend fun getBookmarkAlcoholList(): List<AlcoholData> {
        val bookmarkEntityList = bookmarkDao.getAllBookMark()
        return bookmarkEntityList.map {
            bookmarkToAlcoholData(it)
        }
    }

    override suspend fun isBookMark(alcoholData: AlcoholData): Boolean =
        bookmarkDao.isBookMark(alcoholData.id, alcoholData.name)

    private fun alcoholDataToBookmarkEntity(alcoholData: AlcoholData): BookmarkEntity =
        when (alcoholData) {
            is AlcoholData.Beer -> {
                BookmarkEntity(
                    id = alcoholData.id,
                    name = alcoholData.name,
                    imgUrl = alcoholData.imgUrl,
                    country = alcoholData.country,
                    volume = alcoholData.volume,
                    abv = alcoholData.abv,
                    category = "beer",
                    appearance = alcoholData.appearance,
                    flavor = alcoholData.flavor,
                    mouthfeel = alcoholData.mouthfeel,
                    aromaFlot = alcoholData.aroma,
                    type = alcoholData.type,
                )
            }

            is AlcoholData.Sake -> {
                BookmarkEntity(
                    id = alcoholData.id,
                    name = alcoholData.name,
                    imgUrl = alcoholData.imgUrl,
                    country = alcoholData.country,
                    volume = alcoholData.volume,
                    abv = alcoholData.abv,
                    category = "sake",
                    aromaStr = alcoholData.aroma,
                    price = alcoholData.price,
                    taste = alcoholData.taste,
                    finish = alcoholData.finish,
                )
            }

            is AlcoholData.Soju -> {
                BookmarkEntity(
                    id = alcoholData.id,
                    name = alcoholData.name,
                    imgUrl = alcoholData.imgUrl,
                    country = alcoholData.country,
                    volume = alcoholData.volume,
                    abv = alcoholData.abv,
                    category = "soju",
                    price = alcoholData.price,
                    comment = alcoholData.comment,
                )
            }

            is AlcoholData.TraditionalLiquor -> {
                BookmarkEntity(
                    id = alcoholData.id,
                    name = alcoholData.name,
                    imgUrl = alcoholData.imgUrl,
                    country = alcoholData.country,
                    volume = alcoholData.volume,
                    abv = alcoholData.abv,
                    category = "traditionalliquor",
                    type = alcoholData.type,
                    comment = alcoholData.comment,
                    ingredient = alcoholData.ingredient,
                    pairingFood = alcoholData.pairingFood,
                    brewery = alcoholData.brewery,
                )
            }

            is AlcoholData.Wine -> {
                BookmarkEntity(
                    id = alcoholData.id,
                    name = alcoholData.name,
                    imgUrl = alcoholData.imgUrl,
                    country = alcoholData.country,
                    volume = alcoholData.volume,
                    abv = alcoholData.abv,
                    category = "wine",
                    mouthfeel = alcoholData.mouthfeel,
                    type = alcoholData.type,
                    comment = alcoholData.comment,
                    ingredient = alcoholData.ingredient,
                    pairingFood = alcoholData.pairingFood,
                    sugar = alcoholData.sugar,
                    acid = alcoholData.acid,
                    winery = alcoholData.winery,
                )
            }

            is AlcoholData.Whisky -> {
                BookmarkEntity(
                    id = alcoholData.id,
                    name = alcoholData.name,
                    imgUrl = alcoholData.imgUrl,
                    country = alcoholData.country,
                    volume = alcoholData.volume,
                    abv = alcoholData.abv,
                    category = "whisky",
                    aromaStr = alcoholData.aroma,
                    type = alcoholData.type,
                    price = alcoholData.price,
                    taste = alcoholData.taste,
                    finish = alcoholData.finish,
                )
            }
        }

    private fun bookmarkToAlcoholData(bookmarkEntity: BookmarkEntity): AlcoholData =
        when (bookmarkEntity.category) {
            "beer" -> {
                AlcoholData.Beer(
                    id = bookmarkEntity.id,
                    name = bookmarkEntity.name,
                    imgUrl = bookmarkEntity.imgUrl,
                    country = bookmarkEntity.country,
                    volume = bookmarkEntity.volume,
                    abv = bookmarkEntity.abv,
                    appearance = bookmarkEntity.appearance!!,
                    flavor = bookmarkEntity.flavor!!,
                    mouthfeel = bookmarkEntity.mouthfeel!!,
                    aroma = bookmarkEntity.aromaFlot!!,
                    type = bookmarkEntity.type!!,
                )
            }

            "sake" -> {
                AlcoholData.Sake(
                    id = bookmarkEntity.id,
                    name = bookmarkEntity.name,
                    imgUrl = bookmarkEntity.imgUrl,
                    country = bookmarkEntity.country,
                    volume = bookmarkEntity.volume,
                    abv = bookmarkEntity.abv,
                    aroma = bookmarkEntity.aromaStr!!,
                    price = bookmarkEntity.price!!,
                    taste = bookmarkEntity.taste!!,
                    finish = bookmarkEntity.finish!!,
                )
            }

            "soju" -> {
                AlcoholData.Soju(
                    id = bookmarkEntity.id,
                    name = bookmarkEntity.name,
                    imgUrl = bookmarkEntity.imgUrl,
                    country = bookmarkEntity.country,
                    volume = bookmarkEntity.volume,
                    abv = bookmarkEntity.abv,
                    price = bookmarkEntity.price!!,
                    comment = bookmarkEntity.comment!!,
                )
            }

            "traditionalliquor" -> {
                AlcoholData.TraditionalLiquor(
                    id = bookmarkEntity.id,
                    name = bookmarkEntity.name,
                    imgUrl = bookmarkEntity.imgUrl,
                    country = bookmarkEntity.country,
                    volume = bookmarkEntity.volume,
                    abv = bookmarkEntity.abv,
                    type = bookmarkEntity.type!!,
                    comment = bookmarkEntity.comment!!,
                    ingredient = bookmarkEntity.ingredient!!,
                    pairingFood = bookmarkEntity.pairingFood!!,
                    brewery = bookmarkEntity.brewery!!,
                )
            }

            "wine" -> {
                AlcoholData.Wine(
                    id = bookmarkEntity.id,
                    name = bookmarkEntity.name,
                    imgUrl = bookmarkEntity.imgUrl,
                    country = bookmarkEntity.country,
                    volume = bookmarkEntity.volume,
                    abv = bookmarkEntity.abv,
                    mouthfeel = bookmarkEntity.mouthfeel!!,
                    type = bookmarkEntity.type!!,
                    comment = bookmarkEntity.comment!!,
                    ingredient = bookmarkEntity.ingredient!!,
                    pairingFood = bookmarkEntity.pairingFood!!,
                    sugar = bookmarkEntity.sugar!!,
                    acid = bookmarkEntity.acid!!,
                    winery = bookmarkEntity.winery!!,
                )
            }

            "whisky" -> {
                AlcoholData.Whisky(
                    id = bookmarkEntity.id,
                    name = bookmarkEntity.name,
                    imgUrl = bookmarkEntity.imgUrl,
                    country = bookmarkEntity.country,
                    volume = bookmarkEntity.volume,
                    abv = bookmarkEntity.abv,
                    aroma = bookmarkEntity.aromaStr!!,
                    type = bookmarkEntity.type!!,
                    price = bookmarkEntity.price!!,
                    taste = bookmarkEntity.taste!!,
                    finish = bookmarkEntity.finish!!,
                )
            }

            else -> {
                throw IllegalArgumentException("Unknown category: ${bookmarkEntity.category}")
            }
        }
}
