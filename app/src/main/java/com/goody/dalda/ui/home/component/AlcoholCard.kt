package com.goody.dalda.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.ui.component.AutoResizedText
import com.goody.dalda.ui.icon.IconPack
import com.goody.dalda.ui.icon.iconpack.IcEmptyCard
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun AlcoholCard(
    alcoholData: AlcoholData,
    onClick: (AlcoholData) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
        modifier
            .clickable { onClick(alcoholData) },
    ) {
        AsyncImage(
            model = alcoholData.imgUrl,
            contentDescription = "",
            placeholder = rememberVectorPainter(image = IconPack.IcEmptyCard),
            contentScale = ContentScale.Fit,
            modifier =
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.gray_80),
                    shape = RoundedCornerShape(8.dp)
                ),
        )
        AutoResizedText(
            text = alcoholData.name,
            style = DaldaTextStyle.h4,
            modifier = Modifier.align(Alignment.Start),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.Start),
        ) {
            Image(
                painter = painterResource(getTagImgRes(alcoholData)),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(16.dp),
            )

            AutoResizedText(
                text = alcoholData.abv,
                style = DaldaTextStyle.subtitle2,
                color = Color.Gray
            )
        }
    }
}

fun getTagImgRes(alcoholData: AlcoholData) =
    when (alcoholData) {
        is AlcoholData.Soju -> R.drawable.tag_soju
        is AlcoholData.Beer -> R.drawable.tag_beer
        is AlcoholData.TraditionalLiquor -> R.drawable.tag_traditional_liquor
        is AlcoholData.Wine -> R.drawable.tag_wine
        is AlcoholData.Sake -> R.drawable.tag_sake
        is AlcoholData.Whisky -> R.drawable.tag_whiskey
    }

@Preview(showBackground = true)
@Composable
private fun AlcoholCardPreview() {
    AlcoholCard(
        alcoholData =
        AlcoholData.Soju(
            id = 0,
            name = "소주",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            volume = "355ml",
            abv = "4.5%",
            price = 1000,
            comment = "맛있어요",
        ),
    )
}
