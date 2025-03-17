package com.goody.dalda.ui.policy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun PolicyScreen(
    title: String,
    fileName: String,
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
    viewModel: PolicyViewModel = viewModel(),
) {
    val context = LocalContext.current

    val termsOfUseStateNew by viewModel.termsOfUseStateNew.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchTermsOfUse(context.assets, fileName)
    }

    PolicyScreen(
        title = title,
        termsOfUseStateNew = termsOfUseStateNew,
        onClose = onClose,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolicyScreen(
    title: String,
    termsOfUseStateNew: String,
    onClose: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier =
            modifier
                .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        style = DaldaTextStyle.h2,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "close",
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black,
                    ),
            )
        },
        containerColor = colorResource(id = R.color.white),
    ) { innerPadding ->
        PolicyLayout(
            modifier = Modifier.padding(innerPadding),
            text = termsOfUseStateNew,
        )
    }
}

@Composable
fun PolicyLayout(
    modifier: Modifier = Modifier,
    text: String,
) {
    val scroll = rememberScrollState()

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .verticalScroll(scroll),
    ) {
        Text(
            text = text,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            style = DaldaTextStyle.body3,
        )
    }
}

@Preview(widthDp = 400, heightDp = 900)
@Composable
fun PolicyScreenPreview() {
    MaterialTheme {
        PolicyScreen(
            title = "이용약관",
            termsOfUseStateNew = "이용약관내용",
            onClose = {},
        )
    }
}
