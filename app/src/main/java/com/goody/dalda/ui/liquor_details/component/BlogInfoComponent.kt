package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goody.dalda.R
import com.goody.dalda.ui.theme.DaldaTextStyle

private const val CONTENT_BOTTOM_PADDING_SIZE = 4
private const val VERTICAL_DIVIDER_HEIGHT = 10
private const val TITLE_MAX_LINE = 1
private const val DESCRIPTION_MAX_LINE = 3

@Composable
fun BlogInfoComponent(
    title: String,
    description: String,
    postingDates: String,
    blogLink: String,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable { onClick(blogLink) },
    ) {
        Text(
            text = title,
            maxLines = TITLE_MAX_LINE,
            overflow = TextOverflow.Ellipsis,
            style = DaldaTextStyle.body2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = CONTENT_BOTTOM_PADDING_SIZE.dp),
        )
        Text(
            text = description,
            overflow = TextOverflow.Ellipsis,
            maxLines = DESCRIPTION_MAX_LINE,
            style = DaldaTextStyle.body3,
            color = colorResource(id = R.color.gray_40),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = CONTENT_BOTTOM_PADDING_SIZE.dp),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = postingDates,
                color = colorResource(id = R.color.gray_60),
                textAlign = TextAlign.End,
                style = DaldaTextStyle.body4,
                modifier = Modifier.align(Alignment.Bottom),
            )
            VerticalDivider(
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = CONTENT_BOTTOM_PADDING_SIZE.dp)
                    .height(VERTICAL_DIVIDER_HEIGHT.dp),
            )
            Text(
                text = stringResource(R.string.text_blog_naver),
                color = colorResource(id = R.color.gray_60),
                style = DaldaTextStyle.body4,
                modifier = Modifier.align(Alignment.Bottom),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BlogInfoComponentPrev() {
    BlogInfoComponent(
        title = "title",
        description = "2년 전쯤 단짝 친구 4명이서 부산 여행을 다녀온 남편 그때는 2명만 애 아빠였는데 지금은 모두 애 아빠가 되어 쉽게 여행을 가지 못함. 한동안 부산 여행 이야기 많이 하다 어느 날 회사앞 세계맥주 할인점",
        postingDates = "2021.09.09",
        blogLink = "https://blog.naver.com/abc",
    )
}
