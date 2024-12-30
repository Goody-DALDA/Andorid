package com.goody.dalda.ui.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R
import com.goody.dalda.ui.component.AutoResizedText

@Composable
fun AlcoholCard(
    modifier: Modifier = Modifier,
    imgUrl: String,
    name: String,
    category: String,
    abv: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = imgUrl,
            contentDescription = "",
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(152.dp)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        )
        AutoResizedText(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        Row(
            modifier = Modifier.align(Alignment.Start),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AutoResizedText(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
            )

            AutoResizedText(
                text = abv,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlcoholCardPreview() {
    AlcoholCard(
        imgUrl = "",
        name = "소주",
        category = "소주",
        abv = "13.00"
    )
}
