package com.goody.dalda.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.goody.dalda.R

@Composable
fun ContentsTitle(
    title: String,
    actionText: String,
    onActionClick: () -> Unit = {}
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = actionText,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .clickable { onActionClick() }
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
