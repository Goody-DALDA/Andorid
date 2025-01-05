package com.goody.dalda.ui.home.component.iconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.goody.dalda.ui.home.component.IconPack

public val IconPack.IcLink: ImageVector
    get() {
        if (_icLink != null) {
            return _icLink!!
        }
        _icLink = Builder(
            name = "IcLink", defaultWidth = 32.0.dp, defaultHeight = 32.0.dp,
            viewportWidth = 32.0f, viewportHeight = 32.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFC3C3C6)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(16.0f, 0.0f)
                lineTo(16.0f, 0.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 32.0f, 16.0f)
                lineTo(32.0f, 16.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 16.0f, 32.0f)
                lineTo(16.0f, 32.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 0.0f, 16.0f)
                lineTo(0.0f, 16.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 16.0f, 0.0f)
                close()
            }
            group {
                path(
                    fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(16.0f, 10.6667f)
                    lineTo(13.8667f, 12.8f)
                    lineTo(14.9334f, 13.8667f)
                    lineTo(17.0667f, 11.7333f)
                    curveTo(17.9467f, 10.8533f, 19.3867f, 10.8533f, 20.2667f, 11.7333f)
                    curveTo(21.1467f, 12.6133f, 21.1467f, 14.0533f, 20.2667f, 14.9333f)
                    lineTo(18.1334f, 17.0667f)
                    lineTo(19.2f, 18.1334f)
                    lineTo(21.3334f, 16.0f)
                    curveTo(22.8054f, 14.528f, 22.8054f, 12.1387f, 21.3334f, 10.6667f)
                    curveTo(19.8614f, 9.1947f, 17.472f, 9.1947f, 16.0f, 10.6667f)
                    close()
                    moveTo(17.0667f, 18.1334f)
                    lineTo(14.9334f, 20.2667f)
                    curveTo(14.0534f, 21.1467f, 12.6133f, 21.1467f, 11.7333f, 20.2667f)
                    curveTo(10.8533f, 19.3867f, 10.8533f, 17.9467f, 11.7333f, 17.0667f)
                    lineTo(13.8667f, 14.9333f)
                    lineTo(12.8f, 13.8667f)
                    lineTo(10.6667f, 16.0f)
                    curveTo(9.1947f, 17.472f, 9.1947f, 19.8614f, 10.6667f, 21.3334f)
                    curveTo(12.1387f, 22.8054f, 14.528f, 22.8054f, 16.0f, 21.3334f)
                    lineTo(18.1334f, 19.2f)
                    lineTo(17.0667f, 18.1334f)
                    close()
                    moveTo(13.3333f, 17.6f)
                    lineTo(17.6f, 13.3333f)
                    lineTo(18.6667f, 14.4f)
                    lineTo(14.4f, 18.6667f)
                    lineTo(13.3333f, 17.6f)
                    close()
                }
            }
        }
            .build()
        return _icLink!!
    }

private var _icLink: ImageVector? = null
