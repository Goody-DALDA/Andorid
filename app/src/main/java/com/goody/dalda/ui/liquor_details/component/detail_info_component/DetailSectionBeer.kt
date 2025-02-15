package com.goody.dalda.ui.liquor_details.component.detail_info_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.ui.liquor_details.component.DrawBarGraphWithTitle
import com.goody.dalda.ui.liquor_details.component.TextTitleValue
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun DetailSectionBeer(
    alcoholData: AlcoholData.Beer,
    modifier: Modifier = Modifier,
) {
    val valueList =
        listOf(
            "아로마" to alcoholData.aroma,
            "색감" to alcoholData.appearance,
            "맛" to alcoholData.flavor,
            "바디감" to alcoholData.mouthfeel,
        ).map { (label, value) -> label to value / 2 }

    val infoList =
        listOf(
            "스타일" to alcoholData.type,
            "제조지역" to alcoholData.country,
        )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "술정보",
            style = DaldaTextStyle.h2,
        )

        for (info in infoList) {
            TextTitleValue(
                modifier = Modifier,
                title = info.first,
                value = info.second,
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "맛표현",
            style = DaldaTextStyle.h2,
        )

        for (value in valueList) {
            DrawBarGraphWithTitle(
                modifier = Modifier,
                value = value,
            )
        }
    }
}

// create preview for DetailSectionBeer
@Preview(showBackground = true)
@Composable
private fun DetailSectionBeerPrev() {
    val alcoholData = AlcoholData.Beer(
        id = 0,
        name = "카스",
        imgUrl = "http://www.bing.com/search?q=sagittis",
        tag = R.drawable.tag_beer,
        volume = "355ml",
        abv = "4.5%",
        appearance = 2.28f,
        flavor = 4.4f,
        mouthfeel = 2.0f,
        aroma = 3.3f,
        type = "밀맥주",
        country = "독일",
    )

    DetailSectionBeer(
        alcoholData = alcoholData,
    )
}