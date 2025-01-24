package com.goody.dalda.ui.member

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goody.dalda.R
import com.goody.dalda.ui.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithdrawScreen(
    viewModel: WithdrawViewModel = viewModel(),
    onSuccess: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier =
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "탈퇴하기",
                        style = MaterialTheme.typography.titleMedium,
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
            )
        },
    ) { innerPadding ->
        WithdrawLayout(
            viewModel,
            Modifier.padding(innerPadding),
        )
    }
    val context = LocalContext.current
    when (val state = viewModel.state.value) {
        is UiState.Loading -> {
        }

        is UiState.Success -> {
            Toast.makeText(context, state.data, Toast.LENGTH_SHORT).show()
            onSuccess()
        }

        is UiState.Error -> {
            Toast.makeText(context, state.exception?.message, Toast.LENGTH_SHORT).show()
        }

        else -> {}
    }
}

@Composable
fun WithdrawLayout(
    viewModel: WithdrawViewModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        WithdrawGuide("삼겹살에소주")

        Column(modifier = Modifier) {
            val context = LocalContext.current
            val checkState = remember { mutableStateOf(false) }

            TermsCheckbox(checkState)

            WithdrawButton(
                onClick = {
                    if (checkState.value) {
                        viewModel.requestWithdraw()
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
            style = MaterialTheme.typography.titleLarge,
        )

        Text(
            text = "탈퇴하실 경우 좋아요한 술의 정보 및 기록한 술도감이 모두 삭제되고 데이터는 복구되지 않습니다.",
            style =
            MaterialTheme.typography.bodyMedium.copy(
                color = colorResource(R.color.text),
            ),
            modifier = Modifier.padding(top = 18.dp),
        )
    }
}

@Composable
private fun TermsCheckbox(checkState: MutableState<Boolean>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier.clickable {
            checkState.value = !checkState.value
        },
    ) {
        Checkbox(
            checked = checkState.value,
            onCheckedChange = { checkState.value = it },
            colors =
            CheckboxDefaults.colors(
                checkedColor = colorResource(R.color.primary),
            ),
            modifier = Modifier,
        )

        Text(
            text = "탈퇴 유의사항을 확인했으며 이에 동의합니다.",
            style =
            MaterialTheme.typography.bodyMedium.copy(
                color = colorResource(R.color.gray_50),
            ),
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
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = BorderStroke(1.dp, Color(0xFFDDDDDF)),
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Text(
            text = "탈퇴하기",
            color = Color(0xFF8E8E93),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WithdrawScreenPreview() {
    MaterialTheme {
        WithdrawScreen()
    }
}
