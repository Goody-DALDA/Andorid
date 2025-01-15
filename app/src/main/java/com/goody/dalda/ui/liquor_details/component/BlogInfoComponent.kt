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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BlogInfoComponent(
    title: String,
    description: String,
    descriptionMaxLine: Int,
    postingDates: String,
    blogLink: String,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable { onClick(blogLink) }
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        Text(
            text = description,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = descriptionMaxLine,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = postingDates,
                fontSize = 10.sp,
                color = Color.Gray,
                textAlign = TextAlign.End,
                modifier = Modifier.align(Alignment.Bottom)
            )
            VerticalDivider(
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .height(10.dp)
            )
            Text(
                text = "네이버 블로그",
                fontSize = 9.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Bottom)
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
        descriptionMaxLine = 3,
        postingDates = "2021.09.09",
        blogLink = "https://blog.naver.com/abc"
    )
}