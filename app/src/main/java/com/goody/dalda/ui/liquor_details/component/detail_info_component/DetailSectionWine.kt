package com.goody.dalda.ui.liquor_details.component.detail_info_component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.ui.liquor_details.component.DrawBarGraphWithTitle
import com.goody.dalda.ui.liquor_details.component.TextTitleValue
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun DetailSectionWine(
    alcoholData: AlcoholData.Wine,
    modifier: Modifier = Modifier,
) {
    val graphDataList = listOf(
            "단맛" to alcoholData.sugar,
            "신맛" to alcoholData.acid,
            "바디감" to alcoholData.mouthfeel,
        )

    val infoList = listOf(
            "포도종" to alcoholData.ingredient,
            "종류" to alcoholData.type,
            "제조지역" to alcoholData.country,
            "양조장" to alcoholData.winery,
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

        if(alcoholData.comment.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.gray_80),
                        shape = RoundedCornerShape(12.dp),
                    )
                    .padding(12.dp),
                text = alcoholData.comment,
                style = DaldaTextStyle.body3,
            )
        }

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

        for (value in graphDataList) {
            DrawBarGraphWithTitle(
                modifier = Modifier,
                value = value,
            )
        }

        if(alcoholData.pairingFood.isNotEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "푸드 페어링",
                style = DaldaTextStyle.h2,
            )

            Text(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.primary_pale),
                        shape = RoundedCornerShape(12.dp),
                    )
                    .padding(12.dp)
                    .fillMaxWidth(),
                text = alcoholData.pairingFood,
                style = DaldaTextStyle.body3,
            )
        }
    }
}