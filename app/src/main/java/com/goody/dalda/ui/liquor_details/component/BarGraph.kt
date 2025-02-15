package com.goody.dalda.ui.liquor_details.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.R

sealed class BarGraphState {
    data object Start : BarGraphState()
    data object Middle : BarGraphState()
    data object End : BarGraphState()
}

@Composable
fun BarGraph(
    value: Float,
    state: BarGraphState = BarGraphState.Middle,
    height: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    val customModifier = when (state) {
        BarGraphState.Start -> modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = height.dp,
                    bottomStart = height.dp
                )
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.gray_80),
                shape = RoundedCornerShape(
                    topStart = height.dp,
                    bottomStart = height.dp
                )
            )

        BarGraphState.Middle -> modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.gray_80)
            )

        BarGraphState.End -> modifier
            .clip(
                shape = RoundedCornerShape(
                    topEnd = height.dp,
                    bottomEnd = height.dp
                )
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.gray_80),
                shape = RoundedCornerShape(
                    topEnd = height.dp,
                    bottomEnd = height.dp
                )
            )
    }

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
                            width = size.width * value,
                            height = size.height
                        )
                    )
                }
            }
    )
}

@Preview(showBackground = true)
@Composable
private fun BarGraphStartPrev() {
    BarGraph(
        value = 1f,
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
        value = 1f,
        state = BarGraphState.Middle,
        height = 40,
        color = colorResource(id = R.color.primary_light),
        modifier = Modifier
    )
}