package com.goody.dalda.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.goody.dalda.R

object DaldaTextStyle {
    private val daldaFontFamily =
        FontFamily(
            Font(R.font.pretendard_black, FontWeight.Black),
            Font(R.font.pretendard_bold, FontWeight.Bold),
            Font(R.font.pretendard_extra_bold, FontWeight.ExtraBold),
            Font(R.font.pretendard_extra_light, FontWeight.ExtraLight),
            Font(R.font.pretendard_light, FontWeight.Light),
            Font(R.font.pretendard_medium, FontWeight.Medium),
            Font(R.font.pretendard_regular, FontWeight.Normal),
            Font(R.font.pretendard_semi_bold, FontWeight.SemiBold),
            Font(R.font.pretendard_thin, FontWeight.Thin),
        )

    val h1 =
        TextStyle(
            fontSize = 24.sp,
            lineHeight = 31.2.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(600),
        )

    val h2 =
        TextStyle(
            fontSize = 20.sp,
            lineHeight = 26.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(600),
        )

    val h3 =
        TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.8.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(600),
        )

    val h4 =
        TextStyle(
            fontSize = 15.sp,
            lineHeight = 19.5.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(600),
            letterSpacing = 0.15.sp,
        )

    val subtitle1 =
        TextStyle(
            fontSize = 14.sp,
            lineHeight = 21.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(600),
            letterSpacing = 0.14.sp,
        )

    val subtitle2 =
        TextStyle(
            fontSize = 13.sp,
            lineHeight = 19.5.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(500),
            letterSpacing = 0.13.sp,
        )

    val body1 =
        TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(400),
            letterSpacing = 0.16.sp,
        )

    val body2 =
        TextStyle(
            fontSize = 14.sp,
            lineHeight = 21.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(400),
            letterSpacing = 0.14.sp,
        )

    val body3 =
        TextStyle(
            fontSize = 12.sp,
            lineHeight = 18.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(400),
            letterSpacing = 0.12.sp,
        )

    val body4 =
        TextStyle(
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(400),
            letterSpacing = 0.16.sp,
        )

    val label1 =
        TextStyle(
            fontSize = 14.sp,
            lineHeight = 21.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(600),
            letterSpacing = 0.14.sp,
        )

    val label2 =
        TextStyle(
            fontSize = 12.sp,
            lineHeight = 18.sp,
            fontFamily = daldaFontFamily,
            fontWeight = FontWeight(600),
            letterSpacing = 0.14.sp,
        )
}
