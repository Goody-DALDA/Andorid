package com.goody.dalda.data

import androidx.annotation.DrawableRes
import com.goody.dalda.R

enum class AlcoholType(
    val alcoholName: String,
    @DrawableRes val image: Int,
    val categoryStatus: AlcoholCategoryStatus
) {
    SOJU(
        alcoholName = "소주",
        image = R.drawable.img_soju,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    BEER(
        alcoholName = "맥주",
        image = R.drawable.img_beer,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    TRADITIONAL(
        alcoholName = "전통주",
        image = R.drawable.img_traditional_alcohol,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    BOILERMAKER(
        alcoholName = "폭탄주",
        image = R.drawable.img_bomb,
        categoryStatus = AlcoholCategoryStatus.WAITING
    ),
    WINE(
        alcoholName = "와인",
        image = R.drawable.img_wine,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    WHISKEY(
        alcoholName = "위스키",
        image = R.drawable.img_wisky,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    SAKE(
        alcoholName = "사케",
        image = R.drawable.img_sake,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    COCKTAIL(
        alcoholName = "칵테일",
        image = R.drawable.img_cocktail,
        categoryStatus = AlcoholCategoryStatus.WAITING
    )
}
