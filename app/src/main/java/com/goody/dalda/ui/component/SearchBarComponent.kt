package com.goody.dalda.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.ui.home.component.IconPack
import com.goody.dalda.ui.home.component.iconpack.IcCamera

@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    query: String = "",
    placeholder: String = "",
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    onValueChange: (String) -> Unit = {},
    onClickBackIcon: () -> Unit = {},
    onClickTrailingIcon: () -> Unit = {}
) {
    Row(
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .clickable { onClickBackIcon() }
        )
        OutlinedTextField(
            value = query,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .background(
                    color = Color(0xFFF8F8F8),
                    shape = RoundedCornerShape(18.dp)
                ),
            placeholder = { Text(text = placeholder) },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null
                )
            },
            trailingIcon = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (query.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onValueChange("")
                            }
                        )
                    }
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onClickTrailingIcon()
                        }
                    )
                }
            },
            shape = RoundedCornerShape(18.dp),
        )
    }
}

@Preview
@Composable
private fun SearchBarComponentPrev() {
    SearchBarComponent(
        query = "",
        placeholder = "placeholder",
        leadingIcon = Icons.Outlined.Search,
        trailingIcon = IconPack.IcCamera,
        onValueChange = {},
        onClickBackIcon = {},
        onClickTrailingIcon = {}
    )
}
