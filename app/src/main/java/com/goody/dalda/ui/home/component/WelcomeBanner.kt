package com.goody.dalda.ui.home.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.goody.dalda.R

@Composable
fun WelcomeBanner(userName: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        WelcomeComment(
            userName = userName,
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .weight(5f)
        )

        Spacer(modifier = Modifier.weight(1f))

        AsyncImage(
            model = "",
            contentDescription = stringResource(id = R.string.description_user_profile_img),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Composable
private fun WelcomeComment(userName: String, modifier: Modifier = Modifier) {
    val welcomeMessage = stringResource(id = R.string.text_welcome_comment, userName)

    val annotatedString = buildAnnotatedString {
        val startIndex = welcomeMessage.indexOf(userName)
        val endIndex = startIndex + userName.length

        append(welcomeMessage.substring(0, startIndex))

        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(userName)
        }

        append(welcomeMessage.substring(endIndex))
    }

    Text(
        text = annotatedString,
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun WelcomeBannerPreview() {
    WelcomeBanner(
        userName = "삼겹살에 소주"
    )
}

@Preview(showBackground = true)
@Composable
private fun WelcomeCommentPreview() {
    WelcomeComment(
        userName = "오겹살에 콜라"
    )
}
