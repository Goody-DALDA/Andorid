package com.goody.dalda.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholCategoryStatus
import com.goody.dalda.data.AlcoholType


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AlcoholCategory(
    columnCount: Int = 5,
    modifier: Modifier = Modifier,
    onClickAlcohol: () -> Unit = {},
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = columnCount
    ) {
        AlcoholType.entries.forEach { alcoholType ->
            if (alcoholType.categoryStatus != AlcoholCategoryStatus.NONE) {
                AlcoholTap(
                    alcoholType = alcoholType,
                    modifier = Modifier.size(80.dp),
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
            .size(80.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = alcoholType.image,
            contentDescription = "",
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .weight(3f)
                .align(Alignment.CenterHorizontally)
                .fillMaxSize()
                .clickable { onClick() }
        )

        Text(
            text = alcoholType.alcoholName,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

//@OptIn(ExperimentalLayoutApi::class)
//@Composable
//fun BankSelectRow(
//    onClickBankType: (BankType) -> Unit = {},
//) {
//    FlowRow(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(4.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalArrangement = Arrangement.spacedBy(4.dp),
//        maxItemsInEachRow = COLUMN_COUNT
//    ) {
//        BankType.entries.forEach { bankType ->
//            if (bankType == BankType.NOT_SELECTED) return@forEach
//            CardSelector(
//                bankType = bankType,
//                onClick = { onClickBankType(bankType) }
//            )
//        }
//    }
//}

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
        modifier = modifier
    )
}
