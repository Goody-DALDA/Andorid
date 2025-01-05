package com.goody.dalda.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.ui.component.AutoResizedText
import com.goody.dalda.ui.component.OrangeColorButton

@Composable
fun LoginBanner(
    text: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        AutoResizedText(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .weight(2f),
        )

        OrangeColorButton(
            text = "로그인하기 >",
            onClick = onClick,
            modifier = Modifier.weight(1f),
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun LoginBannerPreview() {
    LoginBanner(
        text = "달다에 로그인하고\n나만의 술도감을 만들어보세요"
    )
}
