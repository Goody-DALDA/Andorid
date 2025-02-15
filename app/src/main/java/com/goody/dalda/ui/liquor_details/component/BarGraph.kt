package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R
import com.goody.dalda.ui.theme.DaldaTextStyle

sealed class BarGraphState {
    data object Start : BarGraphState()
    data object Middle : BarGraphState()
    data object End : BarGraphState()
}

private const val BAR_COUNT = 5

/**
 * 바 그래프를 그리는 Composable 함수
 *
 * @param value 바 그래프의 값 (0.0f ~ 1.0f)
 * @param state 바 그래프의 상태 (시작, 중간, 끝)
 * @param height 바 그래프의 높이 (dp 단위)
 * @param color 바 그래프의 색상
 * @param modifier 추가적인 수정자
 */

@Composable
fun BarGraph(
    value: Float,
    state: BarGraphState = BarGraphState.Middle,
    height: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    val clampedValue = remember(value) {
        value.coerceIn(0f, 1f)
    }

    val customModifier = getCustomModifierByState(state, modifier, height)

    Box(
        modifier = customModifier
            .height(height.dp)
            .fillMaxSize()
            .drawWithCache {
                onDrawBehind {
                    drawRect(Color.White)

                    drawRect(
                        color = color,
                        size = Size(
                            width = size.width * clampedValue,
                            height = size.height
                        )
                    )
                }
            }
    )
}

@Composable
private fun getCustomModifierByState(
    state: BarGraphState,
    modifier: Modifier,
    height: Int
): Modifier {
    val startShape = RoundedCornerShape(
        topStart = height.dp,
        bottomStart = height.dp
    )

    val endShape = RoundedCornerShape(
        topEnd = height.dp,
        bottomEnd = height.dp
    )

    return when (state) {
        BarGraphState.Start -> modifier
            .clip(startShape)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.gray_80),
                shape = startShape
            )

        BarGraphState.Middle -> modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.gray_80)
            )

        BarGraphState.End -> modifier
            .clip(endShape)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.gray_80),
                shape = endShape
            )
    }
}

@Composable
fun DrawBarGraph(
    value: Float,
    barCount: Int,
    modifier: Modifier = Modifier,
) {
    val fillWeightList = remember(value, barCount) {
        List(barCount) { index ->
            minOf(maxOf(value - index, 0f), 1f)
        }
    }

    var barGraphState: BarGraphState

    Row(
        modifier = modifier,
    ) {
        for (i in 0 until barCount) {
            barGraphState = when (i) {
                0 -> {
                    BarGraphState.Start
                }

                in 1 until barCount - 1 -> {
                    BarGraphState.Middle
                }

                else -> {
                    BarGraphState.End
                }
            }
            BarGraph(
                value = fillWeightList[i],
                state = barGraphState,
                height = 20,
                color = colorResource(id = R.color.primary),
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
fun DrawBarGraphWithTitle(
    value: Pair<String, Float>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.End,
            text = value.first,
            style = DaldaTextStyle.body2,
        )
        DrawBarGraph(
            modifier = Modifier.weight(7f),
            value = value.second,
            barCount = BAR_COUNT,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BarGraphStartPrev() {
    BarGraph(
        value = 0.2f,
        state = BarGraphState.Start,
        height = 40,
        color = colorResource(id = R.color.primary_light),
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun BarGraphEndPrev() {
    BarGraph(
        value = 1f,
        state = BarGraphState.End,
        height = 40,
        color = colorResource(id = R.color.primary_light),
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun BarGraphMidPrev() {
    BarGraph(
        value = 0.8f,
        state = BarGraphState.Middle,
        height = 40,
        color = colorResource(id = R.color.primary_light),
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun BarGraphEmptyPrev() {
    BarGraph(
        value = 0f,
        state = BarGraphState.Start,
        height = 40,
        color = colorResource(id = R.color.primary_light),
        modifier = Modifier
    )
}