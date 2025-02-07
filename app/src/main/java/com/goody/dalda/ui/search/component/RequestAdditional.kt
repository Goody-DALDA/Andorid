package com.goody.dalda.ui.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.component.OrangeColorButton
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun RequestAdditional(
    modifier: Modifier = Modifier,
    onClickRequest: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier =
            modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_break_glass),
            contentDescription = "",
            modifier = Modifier.size(180.dp),
        )

        Text(
            text = stringResource(id = R.string.text_no_search_result),
            style = DaldaTextStyle.body1,
        )
        OrangeColorButton(
            text = stringResource(id = R.string.text_request_additional),
            onClick = onClickRequest,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RequestAdditionalPrev() {
    RequestAdditional()
}
