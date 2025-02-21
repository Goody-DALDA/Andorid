package com.goody.dalda.ui.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun OtherAlcoholRecommend(
    category: String,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier =
        modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_alcohols),
            contentDescription = stringResource(id = R.string.description_liquors_img),
            modifier = Modifier.size(180.dp),
        )

        Text(
            text = stringResource(id = R.string.text_other_alcohol, category),
            style = DaldaTextStyle.subtitle1
        )

        Button(
            onClick = { onClick(category) },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
            modifier =
            modifier
                .background(
                    colorResource(id = R.color.white),
                    shape = RoundedCornerShape(8.dp),
                )
                .border(
                    width = 1.dp, // 테두리 두께
                    color = Color.Black, // 테두리 색상
                    shape = RoundedCornerShape(8.dp), // 둥근 모서리
                ),
        ) {
            Text(
                text = "보러가기",
                style = DaldaTextStyle.subtitle1,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OtherAlcoholRecommendPrev() {
    OtherAlcoholRecommend(
        category = "소주",
    )
}
