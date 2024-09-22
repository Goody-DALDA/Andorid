package com.goody.dalda.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R
import com.goody.dalda.ui.component.AutoResizedText

@Composable
fun AlcoholCard(
    imgUrl: String,
    name: String,
    category: String,
    alcohol: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = imgUrl,
            contentDescription = "",
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(152.dp)
        )
        AutoResizedText(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
        )
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AutoResizedText(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
            )

            AutoResizedText(
                text = "$alcohol%",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlcoholCardPreview() {
    AlcoholCard(
        imgUrl = "", name = "소주", category = "소주", alcohol = "13.00"
    )
}
