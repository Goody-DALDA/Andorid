package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun BasicLiquorInfoSection(
    alcoholData: AlcoholData,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        AsyncImage(
            model = alcoholData.imgUrl,
            contentDescription = "주류 이미지",
            contentScale = ContentScale.FillHeight,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            modifier =
                Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
        )

        BasicInfo(
            alcoholData = alcoholData,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun BasicInfo(
    alcoholData: AlcoholData,
    modifier: Modifier = Modifier,
) {
    when (alcoholData) {
        is AlcoholData.Soju,
        is AlcoholData.Beer,
        is AlcoholData.Sake,
        is AlcoholData.Whisky,
        -> {
            BasicInfoWithCountry(
                alcoholData = alcoholData,
                modifier = modifier,
            )
        }

        is AlcoholData.TraditionalLiquor,
        is AlcoholData.Wine,
        -> {
            BasicInfoWithBrewery(
                alcoholData = alcoholData,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun BasicInfoWithCountry(
    alcoholData: AlcoholData,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        // 카테고리
        Image(
            painter = painterResource(alcoholData.tag),
            contentDescription = "카테고리",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(32.dp),
        )

        // 이름
        Text(
            text = alcoholData.name,
            style = DaldaTextStyle.h1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        // 나라
        Text(
            text = alcoholData.country,
            style = DaldaTextStyle.body3,
        )

        // 도수 / 용량
        RowAbvVolume(
            alcoholData = alcoholData,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
fun BasicInfoWithBrewery(
    alcoholData: AlcoholData,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        // 카테고리
        Image(
            painter = painterResource(alcoholData.tag),
            contentDescription = "카테고리",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(32.dp),
        )

        // 이름
        Text(
            text = alcoholData.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        // 양조장
        if (alcoholData is AlcoholData.TraditionalLiquor) {
            Text(alcoholData.brewery)
        }
        if (alcoholData is AlcoholData.Wine) {
            Text(alcoholData.winery)
        }

        // 도수 / 용량
        RowAbvVolume(
            alcoholData = alcoholData,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
fun RowAbvVolume(
    alcoholData: AlcoholData,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(bottom = 16.dp),
    ) {
        SecondFloorText(
            topText = "도수",
            bottomText = alcoholData.abv,
        )

        VerticalDivider(
            modifier = Modifier.height(40.dp),
        )
        SecondFloorText(
            topText = "용량",
            bottomText = alcoholData.volume,
        )
    }
}

@Composable
fun SecondFloorText(
    topText: String,
    bottomText: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = topText,
            style = DaldaTextStyle.body2,
        )
        Text(
            text = bottomText,
            style = DaldaTextStyle.body1,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LiquorImageSectionPrev_soju() {
    BasicLiquorInfoSection(
        alcoholData =
            AlcoholData.Soju(
                id = 7959,
                name = "대선",
                imgUrl = "http://www.bing.com/search?q=sagittis",
                tag = R.drawable.tag_soju,
                volume = "100ml",
                price = 2900,
                abv = "18%",
                comment = "소주임",
            ),
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorImageSectionPrev_beer() {
    BasicLiquorInfoSection(
        alcoholData =
            AlcoholData.Beer(
                id = 7959,
                name = "1664 블랑",
                imgUrl = "http://www.bing.com/search?q=sagittis",
                tag = R.drawable.tag_beer,
                abv = "18%",
                appearance = 18f,
                flavor = 18f,
                mouthfeel = 18f,
                aroma = 18f,
                country = "독일",
                type = "밀맥주",
            ),
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorImageSectionPrev_sake() {
    BasicLiquorInfoSection(
        alcoholData =
            AlcoholData.Sake(
                id = 7959,
                name = "닷사이 준마이 다이긴죠 23",
                imgUrl = "http://www.bing.com/search?q=sagittis",
                tag = R.drawable.tag_sake,
                volume = "360ml",
                price = 200000,
                abv = "18%",
                taste = "달콤함",
                aroma = "향기",
                finish = "맛",
                country = "일본",
            ),
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorImageSectionPrev_wine() {
    BasicLiquorInfoSection(
        alcoholData =
            AlcoholData.Wine(
                id = 0,
                name = "이기갈 꼬뜨 뒤 론 화이트",
                imgUrl = "http://www.bing.com/search?q=sagittis",
                tag = R.drawable.tag_wine,
                volume = "750ml",
                abv = "",
                country = "프랑스",
                ingredient = "",
                mouthfeel = 0.0f,
                sugar = 0.0f,
                acid = 0.0f,
                type = "",
                comment = "",
                pairingFood = "",
                winery = "",
            ),
    )
}
