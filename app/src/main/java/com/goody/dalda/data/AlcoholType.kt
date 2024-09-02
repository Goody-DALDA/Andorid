package com.goody.dalda.data

import androidx.annotation.DrawableRes

enum class AlcoholType(
    val alcoholName: String,
    @DrawableRes val image: Int,
    val categoryStatus: AlcoholCategoryStatus
) {
    SOJU(
        alcoholName = "소주",
        image = 0,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    BEER(
        alcoholName = "맥주",
        image = 0,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    TRADITIONAL(
        alcoholName = "전통주",
        image = 0,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    BOILERMAKER(
        alcoholName = "폭탄주",
        image = 0,
        categoryStatus = AlcoholCategoryStatus.WAITING
    ),
    WINE(
        alcoholName = "와인",
        image = 0,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    WHISKEY(
        alcoholName = "위스키",
        image = 0,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    LIQUOR(
        alcoholName = "양주",
        image = 0,
        categoryStatus = AlcoholCategoryStatus.RELEASE
    ),
    COCKTAIL(
        alcoholName = "칵테일",
        image = 0,
        categoryStatus = AlcoholCategoryStatus.WAITING
    ),
    ETC(
        alcoholName = "기타",
        image = 0,
        categoryStatus = AlcoholCategoryStatus.NONE
    )
}
