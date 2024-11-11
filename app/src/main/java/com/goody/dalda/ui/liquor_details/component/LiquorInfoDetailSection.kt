package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
        is AlcoholData.Beer -> TODO()
        is AlcoholData.Sake -> TODO()
        is AlcoholData.Soju -> TODO()
        is AlcoholData.TraditionalLiquor -> TODO()
        is AlcoholData.Whiskey -> TODO()
        is AlcoholData.Wine -> TODO()
    }
}

@Composable
fun DetailSectionBeer(
    modifier: Modifier = Modifier,
    alcoholData: AlcoholData.Beer
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val valueList = listOf(
            "아로마" to alcoholData.aroma,
            "색감" to alcoholData.appearance,
            "맛" to alcoholData.flavor,
            "바디감" to alcoholData.mouthfeel
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
fun DrawBarGraph(
    modifier: Modifier = Modifier,
    value: Float
) {
    val isFills = MutableList(5) { false }
    val a = Math.round(value) / 2
    for (i in 0 until a) {
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
            modifier = Modifier.weight(9f),
            value = value.second
        )
    }
}

@Preview
@Composable
private fun LiquorInfoDetailSectionPrev() {
//    LiquorInfoDetailSection()
}

@Preview
@Composable
private fun DrawBarGraphPrev() {
    DrawBarGraph(
        value = 5f
    )
}

@Preview(showBackground = true)
@Composable
private fun DetailSectionBeerPrev() {
    DetailSectionBeer(
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
