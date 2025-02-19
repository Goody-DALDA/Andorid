package com.goody.dalda.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified

@Composable
fun AutoResizedText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = style.color,
) {
    var resizedTextStyle by remember {
        mutableStateOf(style)
    }

    var shouldDraw by remember {
        mutableStateOf(false)
    }

    val defaultFontSize = MaterialTheme.typography.bodyMedium.fontSize

    Text(
        text = text,
        modifier =
        modifier.drawWithContent {
            if (shouldDraw) {
                drawContent()
            }
        },
        color = color,
        softWrap = false,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                if (style.fontSize.isUnspecified) {
                    resizedTextStyle =
                        style.copy(
                            fontSize = defaultFontSize,
                        )
                }
                resizedTextStyle =
                    style.copy(
                        fontSize = resizedTextStyle.fontSize * 0.95f,
                    )
            } else {
                shouldDraw = true
            }
        },
        style = resizedTextStyle,
    )
}

@Preview
@Composable
private fun AutoResizedTextPreview_1() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier =
            Modifier
                .width(230.dp)
                .background((Color.LightGray)),
        ) {
            AutoResizedText(
                text = "Hello, World!",
                style = MaterialTheme.typography.headlineLarge,
            )
        }
    }
}

@Preview
@Composable
private fun AutoResizedTextPreview_2() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier =
            Modifier
                .width(180.dp)
                .background((Color.LightGray)),
        ) {
            AutoResizedText(
                text = "Hello, World!",
                style = MaterialTheme.typography.headlineLarge,
            )
        }
    }
}
