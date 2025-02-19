package com.goody.dalda.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun SimpleMessageDialog(
    modifier: Modifier = Modifier,
    text: String,
    buttonText: String,
    onClickCancel: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = onClickCancel,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        )
    ) {
        Card(
            modifier = modifier,
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = 30.dp,
                        bottom = 20.dp,
                        start = 20.dp,
                        end = 20.dp
                    )
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = text,
                    style = DaldaTextStyle.body1,
                    textAlign = TextAlign.Center
                )
                OrangeColorButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = buttonText,
                    onClick = onClickCancel
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SimpleMessageDialogPrev() {
    SimpleMessageDialog(
        modifier = Modifier
            .width(320.dp)
            .height(160.dp),
        text = "준비중이예요.\n다음 업데이트에서 만나요!",
        buttonText = "기대할게요!",
    )
}

@Preview(showBackground = true)
@Composable
private fun SimpleMessageDialogPrevLongText() {
    SimpleMessageDialog(
        text = "매우 긴 메시지를 표시하는 경우 매우 긴 메시지를 표시하는 경우\n여러 줄에 걸쳐 표시될 수 있습니다.",
        buttonText = "확인",
    )
}