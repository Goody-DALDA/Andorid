package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.ui.liquor_details.component.detail_info_component.DetailSectionBeer
import com.goody.dalda.ui.liquor_details.component.detail_info_component.DetailSectionSake
import com.goody.dalda.ui.liquor_details.component.detail_info_component.DetailSectionSoju
import com.goody.dalda.ui.liquor_details.component.detail_info_component.DetailSectionTraditionalLiquor
import com.goody.dalda.ui.liquor_details.component.detail_info_component.DetailSectionWhiskey
import com.goody.dalda.ui.liquor_details.component.detail_info_component.DetailSectionWine
import com.goody.dalda.ui.theme.DaldaTextStyle

@Composable
fun DetailLiquorInfoSection(
    alcoholUIModel: AlcoholUIModel,
    modifier: Modifier = Modifier,
) {
    when (alcoholUIModel) {
        is AlcoholUIModel.Beer -> {
            DetailSectionBeer(
                alcoholUIModel = alcoholUIModel,
                modifier = modifier,
            )
        }

        is AlcoholUIModel.Sake -> {
            DetailSectionSake(
                alcoholUIModel = alcoholUIModel,
                modifier = modifier,
            )
        }

        is AlcoholUIModel.Soju -> {
            DetailSectionSoju(
                alcoholUIModel = alcoholUIModel,
                modifier = modifier,
            )
        }

        is AlcoholUIModel.TraditionalLiquor -> {
            DetailSectionTraditionalLiquor(
                alcoholUIModel = alcoholUIModel,
                modifier = modifier,
            )
        }

        is AlcoholUIModel.Whisky -> {
            DetailSectionWhiskey(
                alcoholUIModel = alcoholUIModel,
                modifier = modifier,
            )
        }

        is AlcoholUIModel.Wine -> {
            DetailSectionWine(
                alcoholUIModel = alcoholUIModel,
                modifier = modifier,
            )
        }
    }
}


@Composable
fun TextTitleValue(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = DaldaTextStyle.body3,
        )
        Text(
            modifier = Modifier.weight(3f),
            text = value,
            style = DaldaTextStyle.body3,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LiquorInfoDetailSectionPrev_beer() {
    DetailLiquorInfoSection(
        modifier = Modifier,
        alcoholUIModel =
        AlcoholUIModel.Beer(
            id = 0,
            name = "카스",
            imgUrl = "http://www.bing.com/search?q=sagittis",
            tag = R.drawable.tag_beer,
            volume = "355ml",
            abv = "4.5%",
            appearance = 2.28f,
            flavor = 4.4f,
            mouthfeel = 2.0f,
            aroma = 3.3f,
            type = "밀맥주",
            country = "독일",
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorInfoDetailSectionPrev_wine() {
    DetailLiquorInfoSection(
        modifier = Modifier,
        alcoholUIModel =
        AlcoholUIModel.Wine(
            id = 0,
            name = "피치니 키안티 리제르바 ‘꼴레지오네 오로’",
            imgUrl = "https://www.shinsegae-lnb.com/upload/product/wine/wine/images/W_005_E.GuigalCotesduRhoneRouge.jpg",
            country = "프랑스 > 론",
            tag = R.drawable.tag_wine,
            volume = "750ml",
            abv = "0.0%",
            ingredient = "시라 49%, 그르나쉬 48%, 무르베드르 3%",
            mouthfeel = 1.0f,
            sugar = 2.0f,
            acid = 4.0f,
            type = "레드 와인",
            comment = "밝게 빛나는 진한 적색을 띠고 있으며 붉은 딸기 류의 풍부한 향과 스파이시한 노트가 느껴진다. 과일 아로마가 풍부하면서도 입 안에서 느껴지는 질감이 풀 바디한 스타일이다. 끝 맛에서 섬세하고 우아한 여운이 남아 좋은 균형감을 보여준다. 35년 된 포도나무에서 수확한 포도를 전통적인 방식으로 양조했으며 수확연도로부터 6~8년 정도 더 두고 숙성시켜 마실 수 있다.",
            pairingFood = "차가운 육류요리나 가금류, 붉은육류요리, 치즈",
            winery = "이 기갈",
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorInfoDetailSectionPrev_traditional_liquor() {
    DetailLiquorInfoSection(
        modifier = Modifier,
        alcoholUIModel =
        AlcoholUIModel.TraditionalLiquor(
            id = 0,
            name = "벗이랑 강황",
            imgUrl = "https://thesool.com/common/imageView.do?targetId=PR00000950&targetNm=PRODUCT",
            country = "한국",
            tag = R.drawable.tag_traditional_liquor,
            volume = "500ml",
            ingredient = "쌀(국내산), 정제수, 강황(국내산), 프락토올리고당(국내산)",
            abv = "15%",
            type = "탁주(고도)",
            comment = "벗이랑은 대전시와 인근지역에서 자연자생 및 청정재배를 통해 채취한 강황, 버찌 등 건강에 이로운 자연식물로 세 번 빚은 삼양 생탁주이다. 색, 향, 미 세가지가 조화롭게 어우러진 프리미엄 삼양주로, 저온 숙성을 거쳐 목넘김이 부드럽고 바디감이 깊은 생탁주 이다.",
            pairingFood = "약과, 약밥, 송편 등 좋은 떡류나 고추장 불고기, 사천 탕수육 등을 추천한다.",
            brewery = "석이원주조",
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorInfoDetailSectionPrev_whiskey() {
    DetailLiquorInfoSection(
        modifier = Modifier,
        alcoholUIModel =
        AlcoholUIModel.Whisky(
            id = 0,
            name = "와일드터키 8년",
            imgUrl = "https://kihyatr7690.cdn-nhncommerce.com/data/goods/22/09/38/1000000120/pm-Wild Turkey 8y.png",
            country = "미국",
            tag = R.drawable.tag_whiskey,
            volume = "700ml",
            price = 68400,
            abv = "50.5%",
            taste = "달콤한 과일맛과 호밀의 강렬한 스파이스, 약한 시나몬, 팔각, 감초, 후추",
            aroma = "풍부한 꿀과 레몬, 버터스카치, 구운 오크",
            finish = "오크와 다크초콜렛의 긴 여운",
            type = "버번 위스키",
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorInfoDetailSectionPrev_sake() {
    DetailLiquorInfoSection(
        modifier = Modifier,
        alcoholUIModel =
        AlcoholUIModel.Sake(
            id = 0,
            name = "츠루우메 유즈",
            imgUrl = "https://kihyatr7690.cdn-nhncommerce.com/data/goods/22/11/45/1000000183/1000000183_detail_032.png",
            country = "일본",
            tag = R.drawable.tag_sake,
            volume = "720ml",
            price = 64000,
            abv = "7.0%",
            taste = "달콤한, 상큼한, 유자",
            aroma = "유자, 상큼한",
            finish = "감칠맛, 부드러운",
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun LiquorInfoDetailSectionPrev_soju() {
    DetailLiquorInfoSection(
        modifier = Modifier,
        alcoholUIModel =
        AlcoholUIModel.Soju(
            id = 0,
            name = "대선소주",
            imgUrl = "https://arqachylpmku8348141.cdn.ntruss.com/app/product/mst_product/8801137520018_L.jpg",
            country = "대한민국",
            tag = R.drawable.tag_soju,
            volume = "360ml",
            price = 1900,
            abv = "16.9%",
            comment = "부산 지역에서 유명한 소주로, 깔끔한 맛이 특징",
        ),
    )
}
