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
import com.goody.dalda.ui.home.component.iconpack.IcComingSoon


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AlcoholCategory(
    modifier: Modifier = Modifier,
    columnCount: Int = 4,
    onClickAlcohol: (AlcoholType) -> Unit = {},
) {
    FlowRow(
        verticalArrangement = Arrangement.run { spacedBy(24.dp) },
        maxItemsInEachRow = columnCount,
        modifier = modifier,
    ) {
        AlcoholType.entries.forEach { alcoholType ->
            if (alcoholType.categoryStatus != AlcoholCategoryStatus.NONE) {
                AlcoholTap(
                    alcoholType = alcoholType,
                    onClick = { onClickAlcohol(alcoholType) },
                    modifier = Modifier
                        .weight(1f),
                )
            }
        }
    }
}

@Composable
fun AlcoholTap(
    modifier: Modifier,
    alcoholType: AlcoholType,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                if (alcoholType.categoryStatus == AlcoholCategoryStatus.RELEASE) {
                    onClick()
                }
            }
    ) {
        Box(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = alcoholType.image),
                contentDescription = "",
                modifier = Modifier
                    .width(80.dp)
                    .height(78.dp)
                    .fillMaxSize()
            )

            if (alcoholType.categoryStatus != AlcoholCategoryStatus.RELEASE) {
                Image(
                    imageVector = IconPack.IcComingSoon,
                    contentDescription = "",
                    modifier = Modifier
                        .width(80.dp)
                        .height(78.dp)
                        .fillMaxSize()
                )
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(),
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
