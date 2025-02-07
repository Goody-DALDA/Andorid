package com.goody.dalda.ui.member

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.ui.state.UiState
import com.goody.dalda.ui.theme.DaldaTextStyle
import kotlinx.coroutines.launch

@Composable
fun WithdrawScreen(
    viewModel: WithdrawViewModel = viewModel(),
    onSuccess: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val stateNew by viewModel.uiState.collectAsStateWithLifecycle()
    val checkState by viewModel.checkState.collectAsStateWithLifecycle()

    WithdrawScreen(
        state = stateNew,
        checkState = checkState,
        onClickWithdraw = {
            viewModel.requestWithdrawNew()
        },
        onSuccess = onSuccess,
        onClickCheckBox = {
            viewModel.checkBoxState()
        },
        onCheckedChange = {
            viewModel.checkedChange(it)
        },
        modifier = modifier,
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithdrawScreen(
    state: UiState<String>,
    checkState: Boolean,
    onClickWithdraw: () -> Unit = {},
    onSuccess: () -> Unit = {},
    onClickCheckBox: () -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val snackBarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier =
            modifier
                .fillMaxSize(),
        containerColor = colorResource(R.color.white),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "탈퇴하기",
                        style = DaldaTextStyle.h2,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
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
        snackbarHost = {
            coroutineScope.launch {
                when (state) {
                    is UiState.Empty -> TODO()
                    is UiState.Error -> {
                        snackBarState.showSnackbar(state.exception?.message ?: "에러 발생")
                    }

                    is UiState.Loading -> TODO()
                    is UiState.Success -> {
                        onSuccess()
                        snackBarState.showSnackbar(state.data)
                    }

                    is UiState.Uninitialized -> TODO()
                }
            }
        },
    ) { innerPadding ->
        WithdrawLayout(
            checkState = checkState,
            onClickWithdraw = onClickWithdraw,
            onClickCheckBox = onClickCheckBox,
            onCheckedChange = onCheckedChange,
            Modifier.padding(innerPadding),
        )
    }
}

@Composable
fun WithdrawLayout(
    checkState: Boolean,
    onClickWithdraw: () -> Unit = {},
    onClickCheckBox: () -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        WithdrawGuide("삼겹살에소주")

        Column(modifier = Modifier) {
            val context = LocalContext.current

            TermsCheckbox(
                checkState,
                onClickCheckBox = onClickCheckBox,
                onCheckedChange = onCheckedChange,
            )

            WithdrawButton(
                onClick = {
                    if (checkState) {
                        onClickWithdraw()
                    } else {
                        Toast.makeText(context, "약관에 동의 해주세요.", Toast.LENGTH_SHORT).show()
                    }
                },
            )
        }
    }
}

@Composable
private fun WithdrawGuide(nickname: String) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(
            text = "$nickname 님,\n정말로 탈퇴하시겠어요?",
            style = DaldaTextStyle.h2,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.text),
        )

        Text(
            text = "탈퇴하실 경우 좋아요한 술의 정보 및 기록한 술도감이 모두 삭제되고 데이터는 복구되지 않습니다.",
            style = DaldaTextStyle.body2,
            color = colorResource(R.color.text),
            modifier = Modifier.padding(top = 18.dp),
        )
    }
}

@Composable
private fun TermsCheckbox(
    checkState: Boolean,
    onClickCheckBox: () -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier.clickable {
                onClickCheckBox()
            },
    ) {
        Checkbox(
            checked = checkState,
            onCheckedChange = { onCheckedChange(it) },
            colors =
                CheckboxDefaults.colors(
                    checkedColor = colorResource(R.color.primary),
                ),
            modifier = Modifier,
        )

        Text(
            text = "탈퇴 유의사항을 확인했으며 이에 동의합니다.",
            style = DaldaTextStyle.body2,
            color = colorResource(R.color.gray_50),
        )
    }
}

@Composable
private fun WithdrawButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.extraSmall,
        colors =
            ButtonColors(
                containerColor = colorResource(id = R.color.white),
                contentColor = colorResource(id = R.color.white),
                disabledContainerColor = colorResource(id = R.color.white),
                disabledContentColor = colorResource(id = R.color.white),
            ),
        border = BorderStroke(1.dp, Color(0xFFDDDDDF)),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
    ) {
        Text(
            text = "탈퇴하기",
            style = DaldaTextStyle.h3,
            color = colorResource(R.color.gray_50),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WithdrawScreenPreview() {
    val state = UiState.Success("탈퇴 성공")
    val checkState = false

    MaterialTheme {
        WithdrawScreen(
            state = state,
            checkState = checkState,
            onClickWithdraw = {},
            onSuccess = {},
            onClickCheckBox = {},
            onCheckedChange = {},
        )
    }
}
