package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData

@Composable
fun LiquorInfoDetailSection(
    modifier: Modifier = Modifier,
    alcoholData: AlcoholData
) {
    when (alcoholData) {
        is AlcoholData.Beer -> {
            DetailSectionBeer(
                modifier = modifier,
                alcoholData = alcoholData
            )
        }

        is AlcoholData.Sake -> TODO()
        is AlcoholData.Soju -> TODO()
        is AlcoholData.TraditionalLiquor -> TODO()
        is AlcoholData.Whiskey -> TODO()
        is AlcoholData.Wine -> {
            DetailSectionWine(
                modifier = modifier,
                alcoholData = alcoholData
            )
        }
    }
}

@Composable
fun DetailSectionBeer(
    modifier: Modifier = Modifier,
    alcoholData: AlcoholData.Beer
) {
    val valueList = listOf(
        "아로마" to alcoholData.aroma,
        "색감" to alcoholData.appearance,
        "맛" to alcoholData.flavor,
        "바디감" to alcoholData.mouthfeel
    ).map { (label, value) -> label to value / 2 }

    val infoList = listOf(
        "스타일" to alcoholData.type,
        "제조지역" to alcoholData.country
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "술정보",
            style = MaterialTheme.typography.headlineSmall
        )

        for(info in infoList) {
            TextTitleValue(
                modifier = Modifier,
                title = info.first,
                value = info.second
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "맛표현",
            style = MaterialTheme.typography.headlineSmall
        )

        for (value in valueList) {
            DrawBarGraphWithTitle(
                modifier = Modifier,
                value = value
            )
        }
    }
}

@Composable
fun DetailSectionWine(
    modifier: Modifier = Modifier,
    alcoholData: AlcoholData.Wine
) {
    val graphDataList = listOf(
        "단맛" to alcoholData.sugar,
        "신맛" to alcoholData.acid,
        "바디감" to alcoholData.mouthfeel
    )

    val infoList = listOf(
        "포도종" to alcoholData.ingredient,
        "종류" to alcoholData.type,
        "제조지역" to alcoholData.country,
        "양조장" to alcoholData.winery
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "술정보",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            modifier = Modifier
                .background(
                    colorResource(id = R.color.gray_80),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp),
            text = alcoholData.comment,
        )

        for (info in infoList) {
            TextTitleValue(
                modifier = Modifier,
                title = info.first,
                value = info.second
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "맛표현",
            style = MaterialTheme.typography.headlineSmall
        )

        for (value in graphDataList) {
            DrawBarGraphWithTitle(
                modifier = Modifier,
                value = value
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "맛표현",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            modifier = Modifier
                .background(
                    colorResource(id = R.color.primary_pale),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp).fillMaxWidth(),
            text = alcoholData.pairingFood,
        )

    }
}

@Composable
fun TextTitleValue(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
        )
        Text(
            modifier = Modifier.weight(5f),
            text = value
        )
    }
}

@Composable
fun DrawBarGraph(
    modifier: Modifier = Modifier,
    value: Float
) {
    val isFills = MutableList(5) { false }
    for (i in 0 until value.toInt()) {
        isFills[i] = true
    }

    Row(
        modifier = modifier,
    ) {
        // 첫번째
        if (isFills.first()) {
            Image(
                painter = painterResource(id = R.drawable.img_fill_start_bar),
                contentDescription = null,
                modifier = modifier.weight(1f),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.img_empty_start_bar),
                contentDescription = null,
                modifier = modifier.weight(1f),
                contentScale = ContentScale.Crop
            )
        }

        // 중간
        for (i in 1 until isFills.size - 1) {
            if (isFills[i]) {
                Image(
                    painter = painterResource(id = R.drawable.img_fill_mid_bar),
                    contentDescription = null,
                    modifier = modifier.weight(1f),
                    contentScale = ContentScale.Crop

                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.img_empty_mid_bar),
                    contentDescription = null,
                    modifier = modifier.weight(1f),
                    contentScale = ContentScale.Crop

                )
            }
        }

        // 마지막
        if (isFills.last()) {
            Image(
                painter = painterResource(id = R.drawable.img_fill_end_bar),
                contentDescription = null,
                modifier = modifier.weight(1f),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.img_empty_end_bar),
                contentDescription = null,
                modifier = modifier.weight(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun DrawBarGraphWithTitle(
    modifier: Modifier = Modifier,
    value: Pair<String, Float>
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            text = value.first
        )
        DrawBarGraph(
            modifier = Modifier.weight(7f),
            value = value.second
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LiquorInfoDetailSectionPrev_Beer() {
    LiquorInfoDetailSection(
        modifier = Modifier,
        alcoholData = AlcoholData.Beer(
            id = 0,
            name = "카스",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_beer,
            volume = 355,
            abv = 4.5f,
            appearance = 2.28f,
            flavor = 4.4f,
            mouthfeel = 2.0f,
            aroma = 3.3f,
            type = "밀맥주",
            country = "독일"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorInfoDetailSectionPrev_Wine() {
    LiquorInfoDetailSection(
        modifier = Modifier,
        alcoholData = AlcoholData.Wine(
            id = 0,
            name = "피치니 키안티 리제르바 ‘꼴레지오네 오로’",
            imgUrl = "https://www.shinsegae-lnb.com/upload/product/wine/wine/images/W_005_E.GuigalCotesduRhoneRouge.jpg",
            country = "프랑스 > 론",
            tag = R.drawable.tag_wine,
            volume = 750,
            abv = 0.0f,
            ingredient = "시라 49%, 그르나쉬 48%, 무르베드르 3%",
            mouthfeel = 1.0f,
            sugar = 2.0f,
            acid = 4.0f,
            type = "레드 와인",
            comment = "밝게 빛나는 진한 적색을 띠고 있으며 붉은 딸기 류의 풍부한 향과 스파이시한 노트가 느껴진다. 과일 아로마가 풍부하면서도 입 안에서 느껴지는 질감이 풀 바디한 스타일이다. 끝 맛에서 섬세하고 우아한 여운이 남아 좋은 균형감을 보여준다. 35년 된 포도나무에서 수확한 포도를 전통적인 방식으로 양조했으며 수확연도로부터 6~8년 정도 더 두고 숙성시켜 마실 수 있다.",
            pairingFood = "차가운 육류요리나 가금류, 붉은육류요리, 치즈",
            winery = "이 기갈"
        )
    )
}
