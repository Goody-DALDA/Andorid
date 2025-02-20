package com.goody.dalda.ui.spirits_collection

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.ui.component.button.RoundSquareButton
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun LiquorCollectionScreen(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit = {},
    viewModel: LiquorCollectionViewModel = viewModel()
) {

    LiquorCollectionScreen(
        onClickButton = onClickButton
    )
}

@Composable
fun LiquorCollectionScreen(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.img_alcohols),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "준비중이예요.\n다음 업데이트에서 만나요!",
            style = DaldaTextStyle.body1,
            textAlign = TextAlign.Center
        )

        RoundSquareButton(
            modifier = Modifier
                .width(141.dp)
                .height(42.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.gray_80),
                    shape = RoundedCornerShape(4.dp)
                ),
            text = "메인으로 돌아가기",
            textColorRes = R.color.gray_50,
            buttonColorRes = R.color.white,
            roundedCornerShapeValue = 4,
            onClick = onClickButton,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LiquorCollectionScreenPreview() {
    LiquorCollectionScreen(
        modifier = Modifier,
        onClickButton = {}
    )
}