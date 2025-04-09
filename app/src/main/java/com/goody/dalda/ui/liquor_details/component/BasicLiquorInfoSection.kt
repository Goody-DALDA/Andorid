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
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun BasicLiquorInfoSection(
    alcoholUIModel: AlcoholUIModel,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        AsyncImage(
            model = alcoholUIModel.imgUrl,
            contentDescription = "주류 이미지",
            contentScale = ContentScale.FillHeight,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            modifier =
                Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
        )

        BasicInfo(
            alcoholUIModel = alcoholUIModel,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun BasicInfo(
    alcoholUIModel: AlcoholUIModel,
    modifier: Modifier = Modifier,
) {
    when (alcoholUIModel) {
        is AlcoholUIModel.Soju,
        is AlcoholUIModel.Beer,
        is AlcoholUIModel.Sake,
        is AlcoholUIModel.Whisky,
        -> {
            BasicInfoWithCountry(
                alcoholUIModel = alcoholUIModel,
                modifier = modifier,
            )
        }

        is AlcoholUIModel.TraditionalLiquor,
        is AlcoholUIModel.Wine,
        -> {
            BasicInfoWithBrewery(
                alcoholUIModel = alcoholUIModel,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun BasicInfoWithCountry(
    alcoholUIModel: AlcoholUIModel,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        // 카테고리
        Image(
            painter = painterResource(alcoholUIModel.tag),
            contentDescription = "카테고리",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(32.dp),
        )

        // 이름
        Text(
            text = alcoholUIModel.name,
            style = DaldaTextStyle.h1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        // 나라
        Text(
            text = alcoholUIModel.country,
            style = DaldaTextStyle.body3,
        )

        // 도수 / 용량
        RowAbvVolume(
            alcoholUIModel = alcoholUIModel,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
fun BasicInfoWithBrewery(
    alcoholUIModel: AlcoholUIModel,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        // 카테고리
        Image(
            painter = painterResource(alcoholUIModel.tag),
            contentDescription = "카테고리",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(32.dp),
        )

        // 이름
        Text(
            text = alcoholUIModel.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        // 양조장
        if (alcoholUIModel is AlcoholUIModel.TraditionalLiquor) {
            Text(alcoholUIModel.brewery)
        }
        if (alcoholUIModel is AlcoholUIModel.Wine) {
            Text(alcoholUIModel.winery)
        }

        // 도수 / 용량
        RowAbvVolume(
            alcoholUIModel = alcoholUIModel,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
fun RowAbvVolume(
    alcoholUIModel: AlcoholUIModel,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(bottom = 16.dp),
    ) {
        SecondFloorText(
            topText = "도수",
            bottomText = alcoholUIModel.abv,
        )

        VerticalDivider(
            modifier = Modifier.height(40.dp),
        )
        SecondFloorText(
            topText = "용량",
            bottomText = alcoholUIModel.volume,
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
        alcoholUIModel =
            AlcoholUIModel.Soju(
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
        alcoholUIModel =
            AlcoholUIModel.Beer(
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
        alcoholUIModel =
            AlcoholUIModel.Sake(
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
        alcoholUIModel =
            AlcoholUIModel.Wine(
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
