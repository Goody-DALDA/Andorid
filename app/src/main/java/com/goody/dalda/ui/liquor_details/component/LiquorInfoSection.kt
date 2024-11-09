package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData

@Composable
fun LiquorInfoSection(
    modifier: Modifier = Modifier,
    alcoholData: AlcoholData
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

        BasicInfo(
            alcoholData = alcoholData
        )
    }
}

@Composable
fun BasicInfo(
    modifier: Modifier = Modifier,
    alcoholData: AlcoholData
) {
    when (alcoholData) {
        is AlcoholData.Soju,
        is AlcoholData.Beer,
        is AlcoholData.Sake,
        is AlcoholData.Whiskey -> {
            BasicInfoWithCountry(
                alcoholData = alcoholData
            )
        }

        is AlcoholData.TraditionalLiquor,
        is AlcoholData.Wine -> {
            BasicInfoWithBrewery(
                alcoholData = alcoholData
            )
        }
    }
}

@Composable
fun BasicInfoWithCountry(
    modifier: Modifier = Modifier,
    alcoholData: AlcoholData
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 카테고리
        Image(
            modifier = Modifier.width(32.dp),
            painter = painterResource(alcoholData.tag),
            contentDescription = "카테고리",
            contentScale = ContentScale.FillWidth,
        )

        // 이름
        Text(text = alcoholData.name)

        // 나라
        Text(alcoholData.country)
    }

    // 도수 / 용량
    RowAbvVolume(
        alcoholData = alcoholData
    )
}

@Composable
fun BasicInfoWithBrewery(
    modifier: Modifier = Modifier,
    alcoholData: AlcoholData
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 카테고리
        Image(
            modifier = Modifier.width(32.dp),
            painter = painterResource(alcoholData.tag),
            contentDescription = "카테고리",
            contentScale = ContentScale.FillWidth,
        )

        // 이름
        Text(text = alcoholData.name)

        // 양조장
        if (alcoholData is AlcoholData.TraditionalLiquor) {
            Text(alcoholData.brewery)
        }
        if (alcoholData is AlcoholData.Wine) {
            Text(alcoholData.brewery)
        }
    }

    // 도수 / 용량
    RowAbvVolume(
        alcoholData = alcoholData
    )
}

@Composable
fun RowAbvVolume(
    modifier: Modifier = Modifier,
    alcoholData: AlcoholData
) {
    Row(
        modifier = Modifier.padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SecondFloorText(
            topText = "도수",
            bottomText = if (alcoholData.abv != 0f) alcoholData.abv.toString() + "%" else ""
        )

        VerticalDivider(
            modifier = Modifier.height(40.dp)
        )
        SecondFloorText(
            topText = "용량",
            bottomText = if (alcoholData.volume != 0) alcoholData.volume.toString() + "ml" else ""
        )
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

@Preview(showBackground = true)
@Composable
private fun LiquorImageSectionPrev_soju() {
    LiquorInfoSection(
        alcoholData = AlcoholData.Soju(
            id = 7959,
            name = "대선",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_soju,
            volume = 100,
            price = 2900,
            abv = 18f,
            comment = "소주임"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorImageSectionPrev_beer() {
    LiquorInfoSection(
        alcoholData = AlcoholData.Beer(
            id = 7959,
            name = "1664 블랑",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_beer,
            abv = 18f,
            appearance = 18f,
            taste = 18f,
            mouthfeel = 18f,
            aroma = 18f,
            country = "독일",
            type = "밀맥주",
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorImageSectionPrev_sake() {
    LiquorInfoSection(
        alcoholData = AlcoholData.Sake(
            id = 7959,
            name = "닷사이 준마이 다이긴죠 23",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_sake,
            volume = 360,
            price = 200000,
            abv = 18f,
            taste = "달콤함",
            aroma = "향기",
            finish = "맛",
            type = "사케",
            country = "일본"
        )
    )
}
