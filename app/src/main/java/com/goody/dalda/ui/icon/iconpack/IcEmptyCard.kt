package com.goody.dalda.ui.icon.iconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goody.dalda.ui.icon.IconPack
import kotlin.Unit

public val IconPack.IcEmptyCard: ImageVector
    get() {
        if (_icEmptyCard != null) {
            return _icEmptyCard!!
        }
        _icEmptyCard = Builder(name = "IcEmptyCard", defaultWidth = 44.0.dp, defaultHeight =
                122.0.dp, viewportWidth = 44.0f, viewportHeight = 122.0f).apply {
            path(fill = SolidColor(Color(0xFFDDDDDF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(15.86f, 0.4f)
                curveTo(14.19f, 0.4f, 12.84f, 1.75f, 12.84f, 3.42f)
                verticalLineTo(8.65f)
                curveTo(12.84f, 9.8f, 13.48f, 10.79f, 14.41f, 11.31f)
                lineTo(12.55f, 39.26f)
                lineTo(10.45f, 40.38f)
                curveTo(4.55f, 43.53f, 0.86f, 49.68f, 0.86f, 56.37f)
                verticalLineTo(112.54f)
                curveTo(0.86f, 117.54f, 4.92f, 121.6f, 9.92f, 121.6f)
                horizontalLineTo(34.08f)
                curveTo(39.08f, 121.6f, 43.14f, 117.54f, 43.14f, 112.54f)
                verticalLineTo(56.37f)
                curveTo(43.14f, 49.68f, 39.45f, 43.53f, 33.55f, 40.38f)
                lineTo(31.45f, 39.26f)
                lineTo(29.59f, 11.31f)
                curveTo(30.52f, 10.79f, 31.16f, 9.8f, 31.16f, 8.65f)
                verticalLineTo(3.42f)
                curveTo(31.16f, 1.75f, 29.81f, 0.4f, 28.14f, 0.4f)
                horizontalLineTo(15.86f)
                close()
            }
        }
        .build()
        return _icEmptyCard!!
    }

private var _icEmptyCard: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = IconPack.IcEmptyCard, contentDescription = "")
    }
}
