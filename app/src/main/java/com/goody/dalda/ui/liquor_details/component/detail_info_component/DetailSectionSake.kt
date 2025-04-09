package com.goody.dalda.ui.liquor_details.component.detail_info_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.ui.liquor_details.component.TextTitleValue
import com.goody.dalda.ui.theme.DaldaTextStyle
import java.text.DecimalFormat

@Composable
fun DetailSectionSake(
    alcoholUIModel: AlcoholUIModel.Sake,
    modifier: Modifier = Modifier,
) {
    val dec = DecimalFormat("#,###")

    val infoList = listOf(
            "권장 소비자가" to dec.format(alcoholUIModel.price) + "원",
            "제조지역" to alcoholUIModel.country,
        )

    val tasteInfoList = listOf(
            "아로마" to alcoholUIModel.aroma,
            "맛" to alcoholUIModel.taste,
            "마무리" to alcoholUIModel.finish,
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
            text = "맛 표현",
            style = DaldaTextStyle.h2,
        )

        for (tasteInfo in tasteInfoList) {
            TextTitleValue(
                modifier = Modifier,
                title = tasteInfo.first,
                value = tasteInfo.second,
            )
        }
    }
}