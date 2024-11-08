package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R

@Composable
fun LiquorImageSection(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://picsum.photos/id/217/100/100",
            contentDescription = "설명",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.height(200.dp),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 카테고리
            Text("소주")

            // 이름
            Text("대선")

            // 주소장
            Text("대선 주조")
        }
        Row(
            modifier = Modifier.padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SecondFloorText(
                topText = "도수",
                bottomText = "20%"
            )

            VerticalDivider(
                modifier = Modifier.height(40.dp)
            )
            SecondFloorText(
                topText = "용량",
                bottomText = "360ml"
            )
        }

    }
}

@Composable
fun SecondFloorText(
    modifier: Modifier = Modifier,
    topText: String,
    bottomText: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(topText)
        Text(bottomText)
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun LiquorImageSectionPrev() {
    LiquorImageSection()
}
