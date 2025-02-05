package com.goody.dalda.ui.home.component.navigationdrawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.component.AutoResizedText
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun NavigationHeader(
    modifier: Modifier = Modifier,
    userName: String,
    userEmail: String,
    onClickCloseIcon: () -> Unit = {},
    onClickProfile: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.description_close_icon),
            modifier = Modifier.clickable { onClickCloseIcon() },
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        Row(
            modifier = Modifier.clickable { onClickProfile() },
        ) {
            Column {
                AutoResizedText(
                    text = userName,
                    style = DaldaTextStyle.h3,
                    modifier = Modifier.padding(bottom = 3.dp),
                )
                AutoResizedText(
                    text = userEmail,
                    style = DaldaTextStyle.body4,
                    color = Color(0xFF8E8E93),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.description_close_icon),
                modifier =
                Modifier
                    .clickable { onClickCloseIcon() }
                    .align(Alignment.CenterVertically),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NavigationHeaderPreview() {
    NavigationHeader(
        modifier = Modifier,
        userName = "삼겹살에 소주",
        userEmail = "oyj7677@gmail.com",
        onClickCloseIcon = {},
    )
}
