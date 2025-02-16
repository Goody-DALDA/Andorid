package com.goody.dalda.study

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.goody.dalda.R

@Composable
fun WeightTest(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f),
        )
        CardImage(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CardImage(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            model = "",
            contentDescription = stringResource(id = R.string.description_user_profile_img),
            placeholder = ColorPainter(Color.Blue),
            modifier = Modifier.weight(2f),
        )
        Text(
            text = "CardImage",
            modifier =
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Red),
        )
        Text(
            text = "CardImage",
            modifier =
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Green),
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
private fun WeightTestPreview() {
    WeightTest()
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
private fun CardImagePreview() {
    CardImage()
}
