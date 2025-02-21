package com.goody.dalda.ui.component.button

import androidx.annotation.ColorRes
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.component.AutoResizedText
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun RoundSquareButton(
    modifier: Modifier = Modifier,
    text: String,
    @ColorRes textColorRes: Int,
    @ColorRes buttonColorRes: Int,
    roundedCornerShapeValue: Int,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(roundedCornerShapeValue.dp),
        colors =
        ButtonDefaults.buttonColors(
            colorResource(id = buttonColorRes),
        ),
        onClick = onClick,
    ) {
        AutoResizedText(
            text = text,
            style = DaldaTextStyle.label1,
            color = colorResource(id = textColorRes),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RoundSquareButtonPreview() {
    RoundSquareButton(
        modifier = Modifier.border(
            width = 1.dp,
            color = colorResource(id = R.color.black),
            shape = RoundedCornerShape(4.dp)
        ),
        text = "감사합니다.",
        textColorRes = R.color.text,
        buttonColorRes = R.color.white,
        roundedCornerShapeValue = 4,
        onClick = {}
    )
}