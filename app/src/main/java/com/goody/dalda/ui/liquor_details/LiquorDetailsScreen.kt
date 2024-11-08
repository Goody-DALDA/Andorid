package com.goody.dalda.ui.liquor_details

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.ui.liquor_details.component.LiquorDetailTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LiquorDetailsScreen(modifier: Modifier = Modifier) {

    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            LiquorDetailTopBar(
                isDropDownMenuExpanded = isDropDownMenuExpanded,
                omNavigationClick = { },
                onClickMenu = { isDropDownMenuExpanded = it }
            )
        }
    ) { innerPadding ->

    }
}

@Preview
@Composable
private fun LiquorDetailsScreenPrev() {
    LiquorDetailsScreen()
}
