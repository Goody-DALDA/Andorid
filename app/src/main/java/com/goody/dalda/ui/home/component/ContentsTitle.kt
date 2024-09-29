package com.goody.dalda.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.R
import com.goody.dalda.ui.component.AutoResizedText

@Composable
fun ContentsTitle(
    modifier: Modifier = Modifier,
    title: String,
    actionText: String,
    onActionClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AutoResizedText(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.weight(1f))

            AutoResizedText(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .clickable { onActionClick() },
                text = actionText,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContentsTitlePreview() {
    ContentsTitle(
        title = stringResource(id = R.string.text_my_favorite_alcohol),
        actionText = stringResource(id = R.string.text_whole_view)
    )
}

