package com.goody.dalda.ui.liquor_details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.ui.liquor_details.component.LiquorDetailTopBar
import com.goody.dalda.ui.liquor_details.component.LiquorInfoDetailSection
import com.goody.dalda.ui.liquor_details.component.LiquorInfoSection

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LiquorDetailsScreen(
    modifier: Modifier = Modifier,
    alcoholData: AlcoholData
) {

    var isDropDownMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier,
        topBar = {
            LiquorDetailTopBar(
                isDropDownMenuExpanded = isDropDownMenuExpanded,
                omNavigationClick = { },
                onClickMenu = { isDropDownMenuExpanded = it }
            )
        }
    ) { innerPadding ->
        Column{
            LiquorInfoSection(
                modifier = Modifier
                    .padding(innerPadding)
                    .height(400.dp),
                alcoholData = alcoholData
            )


            LiquorInfoDetailSection(
                modifier = Modifier,
                alcoholData = alcoholData
            )
        }
    }
}

@Preview
@Composable
private fun LiquorDetailsScreenPrev() {
    LiquorDetailsScreen(
        alcoholData = AlcoholData.Beer(
            id = 0,
            name = "카스",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_beer,
            volume = 355,
            abv = 4.5f,
            appearance = 2.28f,
            flavor = 4.4f,
            mouthfeel = 2.0f,
            aroma = 3.3f,
            type = "밀맥주",
            country = "독일"
        )
    )
}
