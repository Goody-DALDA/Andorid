package com.goody.dalda.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.icon.IconPack
import com.goody.dalda.ui.icon.iconpack.Camera

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlcoholSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChange,
        modifier = modifier,
        placeholder = {
            Text(text = stringResource(id = R.string.text_search_alcohol))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.description_search_icon)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = IconPack.Camera,
                contentDescription = stringResource(id = R.string.description_search_camera_icon),
                modifier = Modifier.clickable { onActiveChange(false) }
            )
        },
        shape = RoundedCornerShape(16.dp),
    ) {

    }
}


@Preview
@Composable
private fun SearchBarPreview() {
    AlcoholSearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {}
    )
}
