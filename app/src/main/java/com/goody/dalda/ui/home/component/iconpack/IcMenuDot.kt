package com.goody.dalda.ui.home.component.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.goody.dalda.ui.home.component.IconPack

public val IconPack.IcMenuDot: ImageVector
    get() {
        if (_icMenuDot != null) {
            return _icMenuDot!!
        }
        _icMenuDot =
            Builder(
                name = "IcMenuDot", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f,
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(12.0f, 5.0f)
                    moveToRelative(-2.0f, 0.0f)
                    arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f)
                    arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f)
                }
                path(
                    fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(12.0f, 12.0f)
                    moveToRelative(-2.0f, 0.0f)
                    arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f)
                    arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f)
                }
                path(
                    fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero,
                ) {
                    moveTo(12.0f, 19.0f)
                    moveToRelative(-2.0f, 0.0f)
                    arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f)
                    arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f)
                }
            }
                .build()
        return _icMenuDot!!
    }

private var _icMenuDot: ImageVector? = null
