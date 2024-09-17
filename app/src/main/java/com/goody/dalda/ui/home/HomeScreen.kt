package com.goody.dalda.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.ui.home.component.AlcoholCategory
import com.goody.dalda.ui.home.component.AlcoholSearchBar
import com.goody.dalda.ui.home.component.HomeTopBar
import com.goody.dalda.ui.home.component.WelcomeBanner

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(
                onClickMenu = { /*TODO*/ },
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
                    .padding(bottom = 30.dp)
            )
            AlcoholSearchBar(
                query = "",
                onQueryChange = { /*TODO*/ },
                onSearch = { /*TODO*/ },
                active = false,
                onActiveChange = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            )

            AlcoholCategory(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}
