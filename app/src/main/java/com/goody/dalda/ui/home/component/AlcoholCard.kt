package com.goody.dalda.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.goody.dalda.R

@Composable
fun AlcoholCard(
    name: String,
    category: String,
    alcohol: String,
    star: String,
) {
    Column {
        AsyncImage(
            model = "https://source.,.com/random/100x100",
            contentDescription = "",
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            modifier = Modifier

        )
        Text(text = name)
        Row {
            Text(
                text = category, textAlign = TextAlign.Center
            )
            Text(
                text = "$alcohol%",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
        Row {
            Icon(imageVector = Icons.Filled.Star, contentDescription = "별점")
            Text(
                text = star, modifier = Modifier.align(Alignment.Bottom)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlcoholCardPreview() {
    AlcoholCard(
        name = "소주", category = "소주", alcohol = "13.00", star = "4.3"
    )
}
