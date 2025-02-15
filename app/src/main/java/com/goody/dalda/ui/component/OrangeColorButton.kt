package com.goody.dalda.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun OrangeColorButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
) {
    Button(
        modifier =
        modifier.background(
            colorResource(id = R.color.primary),
            shape = RoundedCornerShape(6.dp),
        ),
        colors =
        ButtonDefaults.buttonColors(
            colorResource(id = R.color.primary),
        ),
        onClick = onClick,
    ) {
        AutoResizedText(
            text = text,
            style = DaldaTextStyle.label1,
            color = Color.White,
        )
    }
}

@Preview
@Composable
private fun CenterTextButtonPreview() {
    OrangeColorButton(
        modifier = Modifier.background(Color.White),
        text = "로그인하기 >",
    )
}
