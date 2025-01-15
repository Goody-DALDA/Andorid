package com.goody.dalda.ui.bookmark.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData

@Composable
fun BookmarkAlcoholCard(
    alcoholData: AlcoholData,
    onClickCard: (AlcoholData) -> Unit = {},
    onClickBookmark: (AlcoholData) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickCard(alcoholData) }
    ) {
        AsyncImage(
            model = alcoholData.imgUrl,
            contentDescription = "주류 이미지",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.aspectRatio(1f)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(vertical = 4.dp)
                .weight(1f)
        ) {
            Text(
                text = alcoholData.name,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
            )
            Image(
                painter = painterResource(alcoholData.tag),
                contentDescription = "주류 이미지",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.height(20.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.img_heart_filled),
            contentDescription = "주류 이미지",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(20.dp)
                .fillMaxHeight()
                .clickable { onClickBookmark(alcoholData) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BookmarkCardPreview() {
    BookmarkAlcoholCard(
        modifier = Modifier.height(50.dp),
        alcoholData = AlcoholData.Beer(
            id = 1,
            name = "참이슬참이슬참이슬",
            imgUrl = "https://cdn.pixabay.com/photo/2016/11/29/05/45/alcohol-1869862_960_720.jpg",
            volume = "360ml",
            country = "한국",
            abv = "16.9%",
            appearance = 8.9f,
            flavor = 10.11f,
            mouthfeel = 12.13f,
            aroma = 14.15f,
            type = "mentitum"
        )
    )
}