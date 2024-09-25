package com.goody.dalda.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.data.AlcoholCategoryStatus
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.ui.component.AutoResizedText


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AlcoholCategory(
    columnCount: Int = 4,
    modifier: Modifier = Modifier,
    onClickAlcohol: () -> Unit = {},
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.run { spacedBy(24.dp) },
        maxItemsInEachRow = columnCount,
    ) {
        AlcoholType.entries.forEach { alcoholType ->
            if (alcoholType.categoryStatus != AlcoholCategoryStatus.NONE) {
                AlcoholTap(
                    alcoholType = alcoholType,
                    modifier = Modifier
                        .weight(1f),
                    onClick = onClickAlcohol
                )
            }
        }
    }
}

@Composable
fun AlcoholTap(alcoholType: AlcoholType, modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = alcoholType.image),
            contentDescription = "",
            modifier = Modifier
                .width(80.dp)
                .height(78.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxSize()
                .clickable { onClick() }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AutoResizedText(
                text = alcoholType.alcoholName,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlcoholTapPreview() {
    AlcoholTap(
        alcoholType = AlcoholType.SOJU,
        modifier = Modifier,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun AlcoholCategoryPreview(modifier: Modifier = Modifier) {
    AlcoholCategory(
        modifier = Modifier
    )
}
