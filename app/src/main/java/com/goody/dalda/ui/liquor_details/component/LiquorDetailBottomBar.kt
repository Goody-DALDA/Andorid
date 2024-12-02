package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.component.OrangeColorButton

@Composable
fun LiquorDetailBottomBar(modifier: Modifier = Modifier) {
    BottomAppBar(
        modifier = Modifier,
        content = {
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    painter = painterResource(id = R.drawable.img_empty_heart),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )

                OrangeColorButton(
                    modifier = Modifier
                        .weight(5f)
                        .fillMaxHeight(),
                    text = stringResource(id = R.string.text_add_pictorial_book)
                )
            }
        }
    )
}

@Preview
@Composable
private fun LiquorDetailBottomBarPrev() {
    LiquorDetailBottomBar()
}
