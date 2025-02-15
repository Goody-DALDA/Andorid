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
import com.goody.dalda.ui.liquor_details.component.TextTitleValue
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun DetailSectionTraditionalLiquor(
    alcoholData: AlcoholData.TraditionalLiquor,
    modifier: Modifier = Modifier,
) {
    val infoList =
        listOf(
            "스타일" to alcoholData.type,
            "재료" to alcoholData.ingredient,
            "양조장" to alcoholData.brewery,
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

        Text(
            modifier =
            Modifier
                .background(
                    colorResource(id = R.color.gray_80),
                    shape = RoundedCornerShape(12.dp),
                )
                .padding(12.dp),
            text = alcoholData.comment,
            style = DaldaTextStyle.body3,
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
            text = "푸드 페어링",
            style = DaldaTextStyle.h2,
        )

        Text(
            modifier =
            Modifier
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