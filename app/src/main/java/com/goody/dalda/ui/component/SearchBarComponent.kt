package com.goody.dalda.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction.Companion.Search
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.ui.icon.IconPack
import com.goody.dalda.ui.icon.iconpack.IcCamera
import com.goody.dalda.ui.theme.DaldaTextStyle
import kotlinx.coroutines.delay

private const val FOCUS_DELAY_MS = 300L

@Composable
fun SearchBarComponent(
    query: String = "",
    placeholder: String = "",
    isFocus: Boolean = false,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    onValueChange: (String) -> Unit = {},
    onClickBackIcon: () -> Unit = {},
    onClickLeadingIcon: (String) -> Unit = {},
    onClickTrailingIcon: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        delay(FOCUS_DELAY_MS)
        if (isFocus) {
            focusRequester.requestFocus()
        }
    }

    Row(
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .clickable { onClickBackIcon() },
        )
        OutlinedTextField(
            value = query,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .background(
                    color = Color(0xFFF8F8F8),
                    shape = RoundedCornerShape(18.dp),
                )
                .focusRequester(focusRequester),
            placeholder = {
                Text(
                    text = placeholder,
                    style = DaldaTextStyle.body1,
                )
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .clickable {
                            onClickLeadingIcon(query)
                        },
                    imageVector = leadingIcon,
                    contentDescription = null,
                )
            },
            trailingIcon = {
                Row(
                    modifier = Modifier.padding(end = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onValueChange("")
                            }
                            .alpha(
                                if (query.isNotEmpty()) 1f else 0f,
                            ),
                    )

                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onClickTrailingIcon()
                            }
                            .padding(end = 8.dp),
                    )
                }
            },
            shape = RoundedCornerShape(18.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onClickLeadingIcon(query)
                    focusManager.clearFocus()
                },
            ),
        )
    }
}

@Preview
@Composable
private fun SearchBarComponentPrev() {
    SearchBarComponent(
        query = "",
        placeholder = "placeholder",
        isFocus = false,
        leadingIcon = Icons.Outlined.Search,
        trailingIcon = IconPack.IcCamera,
        onValueChange = {},
        onClickBackIcon = {},
        onClickTrailingIcon = {},
    )
}

@Preview
@Composable
private fun SearchBarComponentWithQueryPrev() {
    val query = "query"
    SearchBarComponent(
        query = query,
        placeholder = "placeholder",
        isFocus = false,
        leadingIcon = Icons.Outlined.Search,
        trailingIcon = IconPack.IcCamera,
        onValueChange = {},
        onClickBackIcon = {},
        onClickTrailingIcon = {},
    )
}
