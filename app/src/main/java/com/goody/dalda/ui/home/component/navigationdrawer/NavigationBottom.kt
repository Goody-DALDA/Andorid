package com.goody.dalda.ui.home.component.navigationdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.component.AutoResizedText
import com.goody.dalda.ui.home.component.IconPack
import com.goody.dalda.ui.home.component.iconpack.IcInsta
import com.goody.dalda.ui.home.component.iconpack.IcLink
import com.goody.dalda.ui.home.data.Menu

@Composable
fun NavigationBottom(
    onClick: (Menu) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    // TODO 상태 호이스팅 - 버전 정보.
    Column(
        modifier = modifier
    ) {
        // 개인정보, 이용약관
        Row(
            modifier = Modifier.height(30.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            AutoResizedText(
                text = stringResource(R.string.text_personal_information_processing_policy),
                modifier = Modifier.clickable { onClick(Menu.PrivacyPolicy) }
            )
            VerticalDivider(
                color = Color(0xffA9A9AD),
                modifier = Modifier
                    .height(20.dp)
                    .padding(horizontal = 10.dp),
            )
            AutoResizedText(text = stringResource(id = R.string.text_terms_and_conditions))
        }
        // 인스타, 링크
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 30.dp, bottom = 10.dp),
        ) {
            Image(
                imageVector = IconPack.IcLink,
                contentDescription = stringResource(id = R.string.description_link_icon),
                Modifier.clickable { onClick(Menu.Link) }
            )
            Image(
                imageVector = IconPack.IcInsta,
                contentDescription = stringResource(id = R.string.description_instagram_icon),
                Modifier.clickable { onClick(Menu.Instagram) }
            )
        }
        // 카피라이트
        Text(text = stringResource(id = R.string.text_copyright))
        // 버전 정보.
        Text(text = "버전 정보 0.1.1.1")
    }
}

@Preview(showBackground = true)
@Composable
private fun NavigationBottomPreview() {
    NavigationBottom()
}
