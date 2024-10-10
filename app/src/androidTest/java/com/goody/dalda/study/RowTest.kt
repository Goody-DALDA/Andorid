package com.goody.dalda.study

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.goody.dalda.R

@Composable
fun RowTest(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "RowTest",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .background(Color.Red)

        )

        Spacer(
            modifier = Modifier
                .weight(1f)
                .background(Color.Green)
        )

        AsyncImage(
            model = "",
            contentDescription = stringResource(id = R.string.description_user_profile_img),
            placeholder = ColorPainter(Color.Blue),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun RowTest1(modifier: Modifier = Modifier) {
    Column {
        RowTest(modifier = Modifier.weight(1f))
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "WeightTest",
            modifier = Modifier.weight(1f)
        )

    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun RowTestPreview() {
    RowTest(
        modifier = Modifier.fillMaxSize()
    )
}


@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun RowTest1Preview() {
    RowTest1()
}

