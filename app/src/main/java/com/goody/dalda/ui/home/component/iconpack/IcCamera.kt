package com.goody.dalda.ui.home.component.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.goody.dalda.ui.home.component.IconPack

public val IconPack.IcCamera: ImageVector
    get() {
        if (_icCamera != null) {
            return _icCamera!!
        }
        _icCamera = Builder(name = "IcCamera", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF8E8E93)),
                    strokeLineWidth = 1.6f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(12.0f, 13.0f)
                moveToRelative(-4.2f, 0.0f)
                arcToRelative(4.2f, 4.2f, 0.0f, true, true, 8.4f, 0.0f)
                arcToRelative(4.2f, 4.2f, 0.0f, true, true, -8.4f, 0.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF8E8E93)),
                    strokeLineWidth = 1.6f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(5.0f, 6.0f)
                horizontalLineTo(8.0f)
                lineTo(9.5f, 4.0f)
                horizontalLineTo(14.5f)
                lineTo(16.0f, 6.0f)
                horizontalLineTo(19.0f)
                curveTo(20.1046f, 6.0f, 21.0f, 6.8954f, 21.0f, 8.0f)
                verticalLineTo(18.0f)
                curveTo(21.0f, 19.1046f, 20.1046f, 20.0f, 19.0f, 20.0f)
                horizontalLineTo(5.0f)
                curveTo(3.8954f, 20.0f, 3.0f, 19.1046f, 3.0f, 18.0f)
                verticalLineTo(8.0f)
                curveTo(3.0f, 6.8954f, 3.8954f, 6.0f, 5.0f, 6.0f)
                close()
            }
        }
        .build()
        return _icCamera!!
    }

private var _icCamera: ImageVector? = null
