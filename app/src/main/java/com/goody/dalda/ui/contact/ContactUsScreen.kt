package com.goody.dalda.ui.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.onBackground),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "문의하기",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ContactUsLayout(
            Modifier.padding(innerPadding),
        )
    }
}

@Composable
fun ContactUsLayout(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

        Column(modifier = Modifier.weight(1f)) {
            val emailState = rememberSaveable { mutableStateOf("") }
            EmailContainer(emailState)

            val textState = rememberSaveable { mutableStateOf("") }
            InquiryContainer(textState)

            InquiryGuide()
        }

        InquirySendButton(onClick = {})
    }
}

@Composable
private fun InquirySendButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = colorResource(R.color.primary)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(50.dp)
    ) {
        Text(
            text = "문의 내용 보내기",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
private fun InquiryGuide() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "",
            tint = colorResource(R.color.gray_50),
            modifier = Modifier
                .width(12.dp)
                .height(12.dp)
        )

        Text(
            text = "구체적으로 작성하면 개선이 더 빠르게 이루어져요.",
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.gray_50),
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
private fun EmailContainer(emailState: MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "이메일",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(top = 20.dp)
        )

        OutlinedTextField(
            value = emailState.value,
            onValueChange = { text -> emailState.value = text },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            singleLine = true,
            shape = MaterialTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(R.color.gray_80)
            ),
            placeholder = {
                Text(
                    text = "이메일을 입력해주세요.",
                    color = colorResource(R.color.gray_50),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        )
    }
}

@Composable
private fun InquiryContainer(textState: MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "문의 사항",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(top = 20.dp)
        )

        OutlinedTextField(
            value = textState.value,
            onValueChange = { text -> textState.value = text },
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(top = 10.dp),
            singleLine = true,
            shape = MaterialTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(R.color.gray_80)
            ),
            placeholder = {
                Text(
                    text = "문의하실 내용을 입력해주세요.",
                    color = colorResource(R.color.gray_50),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactUsScreenPreview() {
    MaterialTheme {
        ContactUsScreen()
    }
}