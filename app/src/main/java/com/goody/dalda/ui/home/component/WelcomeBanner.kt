package com.goody.dalda.ui.home.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.goody.dalda.R
import com.goody.dalda.ui.component.AutoResizedText

@Composable
fun WelcomeBanner(
    userName: String,
    userImg: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
    ) {
        WelcomeComment(
            userName = userName,
            modifier =
            Modifier
                .weight(224f),
        )

        Spacer(
            modifier =
            Modifier
                .weight(26f),
        )
        AsyncImage(
            model = userImg,
            contentDescription = stringResource(id = R.string.description_user_profile_img),
            placeholder = painterResource(id = R.drawable.ic_profile_sample),
            contentScale = ContentScale.Crop,
            modifier =
            Modifier
                .weight(70f)
                .aspectRatio(1f)
                .clip(CircleShape),
        )
    }
}

@Composable
private fun WelcomeComment(
    userName: String,
    modifier: Modifier = Modifier,
) {
    val welcomeMessage = stringResource(id = R.string.text_welcome_comment, userName)

    val annotatedString =
        buildAnnotatedString {
            val startIndex = welcomeMessage.indexOf(userName)
            val endIndex = startIndex + userName.length

            append(welcomeMessage.substring(0, startIndex))

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(userName)
            }

            append(welcomeMessage.substring(endIndex))
        }.toString()

    AutoResizedText(
        text = annotatedString,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier,
    )
}

@Preview(
    showBackground = true,
    heightDp = 120,
)
@Composable
private fun WelcomeBannerPreview() {
    WelcomeBanner(
        userName = "삼겹살에 소주",
        userImg = "https://picsum.photos/200/300",
    )
}

@Preview(showBackground = true)
@Composable
private fun WelcomeCommentPreview() {
    WelcomeComment(
        userName = "오겹살에 콜라",
    )
}
