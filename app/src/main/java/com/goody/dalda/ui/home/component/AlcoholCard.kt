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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
        modifier = modifier
            .clickable { onClick(alcoholData) },
    ) {
        AsyncImage(
            model = alcoholData.imgUrl,
            contentDescription = stringResource(id = R.string.description_alcohol_image),
            placeholder = rememberVectorPainter(image = IconPack.IcEmptyCard),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .border(
                    width = CARD_BORDER_WIDTH,
                    color = colorResource(id = R.color.gray_80),
                    shape = RoundedCornerShape(CARD_CORNER_RADIUS)
                )
                .padding(getImagePadding(alcoholData)),
        )
        AutoResizedText(
            text = alcoholData.name,
            style = DaldaTextStyle.h4,
            modifier = Modifier.align(Alignment.Start),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(TAG_TEXT_SPACING),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.Start),
        ) {
            Image(
                painter = painterResource(getTagImgRes(alcoholData)),
                contentDescription = stringResource(id = R.string.description_alcohol_tag),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(TAG_HEIGHT),
            )

            AutoResizedText(
                text = alcoholData.abv,
                style = DaldaTextStyle.subtitle2,
                color = Color.Gray
            )
        }
    }
}

private fun getImagePadding(alcoholData: AlcoholData): Dp =
    when (alcoholData) {
        is AlcoholData.Beer -> BEER_CARD_IMAGE_PADDING
        else -> 0.dp
    }

private fun getTagImgRes(alcoholData: AlcoholData) =
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

private val CARD_BORDER_WIDTH = 1.dp
private val CARD_CORNER_RADIUS = 8.dp
private val BEER_CARD_IMAGE_PADDING = 8.dp
private val TAG_HEIGHT = 16.dp
private val TAG_TEXT_SPACING = 4.dp