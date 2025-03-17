package com.goody.dalda.ui.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.component.button.RoundSquareButton
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun ErrorPageScreen(
    modifier: Modifier = Modifier,
    errorMessage: String,
    buttonTitle: String,
    onClickButton: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.img_break_glass),
            contentDescription = stringResource(id = R.string.description_break_glass_img),
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = errorMessage,
            style = DaldaTextStyle.body1,
            textAlign = TextAlign.Center
        )

        RoundSquareButton(
            modifier = Modifier
                .width(141.dp)
                .height(42.dp),
            text = buttonTitle,
            textColorRes = R.color.white,
            buttonColorRes = R.color.primary,
            roundedCornerShapeValue = 4,
            onClick = onClickButton,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorPageScreenPreview() {
    ErrorPageScreen(
        modifier = Modifier,
        errorMessage = "Error message",
        buttonTitle = "Try again",
        onClickButton = {}
    )
}