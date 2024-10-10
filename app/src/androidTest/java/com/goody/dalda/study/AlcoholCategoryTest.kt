package com.goody.dalda.study

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholCategoryStatus
import com.goody.dalda.data.AlcoholType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AlcoholCategoryTest(modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier,
        maxItemsInEachRow = 4
    ) {
        AlcoholType.entries.forEach { alcoholType ->
            if (alcoholType.categoryStatus != AlcoholCategoryStatus.NONE) {
                AlcoholTap(
                    alcoholType = alcoholType,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(0.5f),
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun AlcoholTap(alcoholType: AlcoholType, modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "",
            contentDescription = stringResource(id = R.string.description_user_profile_img),
            placeholder = ColorPainter(Color.Blue),
            modifier = Modifier.weight(2f)
        )
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = alcoholType.alcoholName,
                modifier = Modifier
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun AlcoholCategoryTestPreview() {
    AlcoholCategoryTest()
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun AlcoholTapPreview() {
    AlcoholTap(
        alcoholType = AlcoholType.BEER,
        modifier = Modifier,
        onClick = {}
    )
}

