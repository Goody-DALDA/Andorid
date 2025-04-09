package com.oyj.data.source.local


import com.oyj.data.database.dao.BookmarkDao
import com.oyj.data.database.entity.BookmarkEntity
import com.oyj.domain.model.AlcoholEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookmarkLocalDataSourceImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao,
) : BookmarkLocalDataSource {
    override suspend fun insertAlcohol(alcoholEntityData: AlcoholEntity) {
        val bookmarkEntity = alcoholDataToBookmarkEntity(alcoholEntityData)
        return bookmarkDao.insertAlcohol(bookmarkEntity)
    }

    override suspend fun deleteAlcohol(alcoholEntityData: AlcoholEntity) {
        val bookmarkEntity = alcoholDataToBookmarkEntity(alcoholEntityData)
        return bookmarkDao.deleteAlcohol(bookmarkEntity)
    }

    override suspend fun getBookmarkAlcoholList(): Flow<List<AlcoholEntity>> {
        return flow {
            emit(
                bookmarkDao
                    .getAllBookMark()
                    .map {
                        bookmarkToAlcohol(it)
                    }
            )
        }
    }

    override fun isBookMark(alcoholEntityData: AlcoholEntity): Flow<Boolean> =
        bookmarkDao.isBookMark(alcoholEntityData.id, alcoholEntityData.name)

    private fun alcoholDataToBookmarkEntity(alcoholEntityData: AlcoholEntity): BookmarkEntity =
        when (alcoholEntityData) {
            is AlcoholEntity.Beer -> {
                BookmarkEntity(
                    id = alcoholEntityData.id,
                    name = alcoholEntityData.name,
                    imgUrl = alcoholEntityData.imgUrl,
                    country = alcoholEntityData.country,
                    volume = alcoholEntityData.volume,
                    abv = alcoholEntityData.abv,
                    category = "beer",
                    appearance = alcoholEntityData.appearance,
                    flavor = alcoholEntityData.flavor,
                    mouthfeel = alcoholEntityData.mouthfeel,
                    aromaFlot = alcoholEntityData.aroma,
                    type = alcoholEntityData.type,
                )
            }

            is AlcoholEntity.Sake -> {
                BookmarkEntity(
                    id = alcoholEntityData.id,
                    name = alcoholEntityData.name,
                    imgUrl = alcoholEntityData.imgUrl,
                    country = alcoholEntityData.country,
                    volume = alcoholEntityData.volume,
                    abv = alcoholEntityData.abv,
                    category = "sake",
                    aromaStr = alcoholEntityData.aroma,
                    price = alcoholEntityData.price,
                    taste = alcoholEntityData.taste,
                    finish = alcoholEntityData.finish,
                )
            }

            is AlcoholEntity.Soju -> {
                BookmarkEntity(
                    id = alcoholEntityData.id,
                    name = alcoholEntityData.name,
                    imgUrl = alcoholEntityData.imgUrl,
                    country = alcoholEntityData.country,
                    volume = alcoholEntityData.volume,
                    abv = alcoholEntityData.abv,
                    category = "soju",
                    price = alcoholEntityData.price,
                    comment = alcoholEntityData.comment,
                )
            }

            is AlcoholEntity.TraditionalLiquor -> {
                BookmarkEntity(
                    id = alcoholEntityData.id,
                    name = alcoholEntityData.name,
                    imgUrl = alcoholEntityData.imgUrl,
                    country = alcoholEntityData.country,
                    volume = alcoholEntityData.volume,
                    abv = alcoholEntityData.abv,
                    category = "traditionalliquor",
                    type = alcoholEntityData.type,
                    comment = alcoholEntityData.comment,
                    ingredient = alcoholEntityData.ingredient,
                    pairingFood = alcoholEntityData.pairingFood,
                    brewery = alcoholEntityData.brewery,
                )
            }

            is AlcoholEntity.Wine -> {
                BookmarkEntity(
                    id = alcoholEntityData.id,
                    name = alcoholEntityData.name,
                    imgUrl = alcoholEntityData.imgUrl,
                    country = alcoholEntityData.country,
                    volume = alcoholEntityData.volume,
                    abv = alcoholEntityData.abv,
                    category = "wine",
                    mouthfeel = alcoholEntityData.mouthfeel,
                    type = alcoholEntityData.type,
                    comment = alcoholEntityData.comment,
                    ingredient = alcoholEntityData.ingredient,
                    pairingFood = alcoholEntityData.pairingFood,
                    sugar = alcoholEntityData.sugar,
                    acid = alcoholEntityData.acid,
                    winery = alcoholEntityData.winery,
                )
            }

            is AlcoholEntity.Whisky -> {
                BookmarkEntity(
                    id = alcoholEntityData.id,
                    name = alcoholEntityData.name,
                    imgUrl = alcoholEntityData.imgUrl,
                    country = alcoholEntityData.country,
                    volume = alcoholEntityData.volume,
                    abv = alcoholEntityData.abv,
                    category = "whisky",
                    aromaStr = alcoholEntityData.aroma,
                    type = alcoholEntityData.type,
                    price = alcoholEntityData.price,
                    taste = alcoholEntityData.taste,
                    finish = alcoholEntityData.finish,
                )
            }
        }

    private fun bookmarkToAlcohol(bookmarkEntity: BookmarkEntity): AlcoholEntity =
        when (bookmarkEntity.category) {
            "beer" -> {
                AlcoholEntity.Beer(
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
                AlcoholEntity.Sake(
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
                AlcoholEntity.Soju(
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
                AlcoholEntity.TraditionalLiquor(
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
                AlcoholEntity.Wine(
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
                AlcoholEntity.Whisky(
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
