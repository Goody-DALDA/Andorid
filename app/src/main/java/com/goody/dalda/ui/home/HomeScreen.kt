package com.goody.dalda.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.ui.home.component.AlcoholCategory
import com.goody.dalda.ui.home.component.HomeTopBar
import com.goody.dalda.ui.home.component.WelcomeBanner

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(
                onClickMenu = { /*TODO*/ }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            WelcomeBanner(
                userName = "Dalda",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            )
            AlcoholCategory(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp, horizontal = 20.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
