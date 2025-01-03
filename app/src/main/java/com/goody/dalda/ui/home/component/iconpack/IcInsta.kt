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

public val IconPack.IcInsta: ImageVector
    get() {
        if (_icInsta != null) {
            return _icInsta!!
        }
        _icInsta = Builder(
            name = "IcInsta", defaultWidth = 32.0.dp, defaultHeight = 32.0.dp,
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
                    moveTo(16.0f, 9.4406f)
                    curveTo(18.1375f, 9.4406f, 18.3906f, 9.45f, 19.2313f, 9.4875f)
                    curveTo(20.0125f, 9.5219f, 20.4344f, 9.6531f, 20.7156f, 9.7625f)
                    curveTo(21.0875f, 9.9063f, 21.3563f, 10.0813f, 21.6344f, 10.3594f)
                    curveTo(21.9156f, 10.6406f, 22.0875f, 10.9063f, 22.2313f, 11.2781f)
                    curveTo(22.3406f, 11.5594f, 22.4719f, 11.9844f, 22.5063f, 12.7625f)
                    curveTo(22.5438f, 13.6062f, 22.5531f, 13.8594f, 22.5531f, 15.9938f)
                    curveTo(22.5531f, 18.1313f, 22.5438f, 18.3844f, 22.5063f, 19.225f)
                    curveTo(22.4719f, 20.0063f, 22.3406f, 20.4281f, 22.2313f, 20.7094f)
                    curveTo(22.0875f, 21.0813f, 21.9125f, 21.35f, 21.6344f, 21.6281f)
                    curveTo(21.3531f, 21.9094f, 21.0875f, 22.0813f, 20.7156f, 22.225f)
                    curveTo(20.4344f, 22.3344f, 20.0094f, 22.4656f, 19.2313f, 22.5f)
                    curveTo(18.3875f, 22.5375f, 18.1344f, 22.5469f, 16.0f, 22.5469f)
                    curveTo(13.8625f, 22.5469f, 13.6094f, 22.5375f, 12.7688f, 22.5f)
                    curveTo(11.9875f, 22.4656f, 11.5656f, 22.3344f, 11.2844f, 22.225f)
                    curveTo(10.9125f, 22.0813f, 10.6438f, 21.9063f, 10.3656f, 21.6281f)
                    curveTo(10.0844f, 21.3469f, 9.9125f, 21.0813f, 9.7688f, 20.7094f)
                    curveTo(9.6594f, 20.4281f, 9.5281f, 20.0031f, 9.4937f, 19.225f)
                    curveTo(9.4563f, 18.3813f, 9.4469f, 18.1281f, 9.4469f, 15.9938f)
                    curveTo(9.4469f, 13.8563f, 9.4563f, 13.6031f, 9.4937f, 12.7625f)
                    curveTo(9.5281f, 11.9812f, 9.6594f, 11.5594f, 9.7688f, 11.2781f)
                    curveTo(9.9125f, 10.9063f, 10.0875f, 10.6375f, 10.3656f, 10.3594f)
                    curveTo(10.6469f, 10.0781f, 10.9125f, 9.9063f, 11.2844f, 9.7625f)
                    curveTo(11.5656f, 9.6531f, 11.9906f, 9.5219f, 12.7688f, 9.4875f)
                    curveTo(13.6094f, 9.45f, 13.8625f, 9.4406f, 16.0f, 9.4406f)
                    close()
                    moveTo(16.0f, 8.0f)
                    curveTo(13.8281f, 8.0f, 13.5563f, 8.0094f, 12.7031f, 8.0469f)
                    curveTo(11.8531f, 8.0844f, 11.2688f, 8.2219f, 10.7625f, 8.4187f)
                    curveTo(10.2344f, 8.625f, 9.7875f, 8.8969f, 9.3438f, 9.3438f)
                    curveTo(8.8969f, 9.7875f, 8.625f, 10.2344f, 8.4187f, 10.7594f)
                    curveTo(8.2219f, 11.2688f, 8.0844f, 11.85f, 8.0469f, 12.7f)
                    curveTo(8.0094f, 13.5563f, 8.0f, 13.8281f, 8.0f, 16.0f)
                    curveTo(8.0f, 18.1719f, 8.0094f, 18.4438f, 8.0469f, 19.2969f)
                    curveTo(8.0844f, 20.1469f, 8.2219f, 20.7313f, 8.4187f, 21.2375f)
                    curveTo(8.625f, 21.7656f, 8.8969f, 22.2125f, 9.3438f, 22.6562f)
                    curveTo(9.7875f, 23.1f, 10.2344f, 23.375f, 10.7594f, 23.5781f)
                    curveTo(11.2688f, 23.775f, 11.85f, 23.9125f, 12.7f, 23.95f)
                    curveTo(13.5531f, 23.9875f, 13.825f, 23.9969f, 15.9969f, 23.9969f)
                    curveTo(18.1688f, 23.9969f, 18.4406f, 23.9875f, 19.2938f, 23.95f)
                    curveTo(20.1438f, 23.9125f, 20.7281f, 23.775f, 21.2344f, 23.5781f)
                    curveTo(21.7594f, 23.375f, 22.2063f, 23.1f, 22.65f, 22.6562f)
                    curveTo(23.0938f, 22.2125f, 23.3688f, 21.7656f, 23.5719f, 21.2406f)
                    curveTo(23.7688f, 20.7313f, 23.9063f, 20.15f, 23.9438f, 19.3f)
                    curveTo(23.9813f, 18.4469f, 23.9906f, 18.175f, 23.9906f, 16.0031f)
                    curveTo(23.9906f, 13.8313f, 23.9813f, 13.5594f, 23.9438f, 12.7063f)
                    curveTo(23.9063f, 11.8563f, 23.7688f, 11.2719f, 23.5719f, 10.7656f)
                    curveTo(23.375f, 10.2344f, 23.1031f, 9.7875f, 22.6563f, 9.3438f)
                    curveTo(22.2125f, 8.9f, 21.7656f, 8.625f, 21.2406f, 8.4219f)
                    curveTo(20.7313f, 8.225f, 20.15f, 8.0875f, 19.3f, 8.05f)
                    curveTo(18.4438f, 8.0094f, 18.1719f, 8.0f, 16.0f, 8.0f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(16.0f, 11.8906f)
                    curveTo(13.7313f, 11.8906f, 11.8906f, 13.7313f, 11.8906f, 16.0f)
                    curveTo(11.8906f, 18.2688f, 13.7313f, 20.1094f, 16.0f, 20.1094f)
                    curveTo(18.2688f, 20.1094f, 20.1094f, 18.2688f, 20.1094f, 16.0f)
                    curveTo(20.1094f, 13.7313f, 18.2688f, 11.8906f, 16.0f, 11.8906f)
                    close()
                    moveTo(16.0f, 18.6656f)
                    curveTo(14.5281f, 18.6656f, 13.3344f, 17.4719f, 13.3344f, 16.0f)
                    curveTo(13.3344f, 14.5281f, 14.5281f, 13.3344f, 16.0f, 13.3344f)
                    curveTo(17.4719f, 13.3344f, 18.6656f, 14.5281f, 18.6656f, 16.0f)
                    curveTo(18.6656f, 17.4719f, 17.4719f, 18.6656f, 16.0f, 18.6656f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(21.2312f, 11.7281f)
                    curveTo(21.2312f, 12.2594f, 20.8f, 12.6875f, 20.2719f, 12.6875f)
                    curveTo(19.7406f, 12.6875f, 19.3125f, 12.2562f, 19.3125f, 11.7281f)
                    curveTo(19.3125f, 11.1969f, 19.7438f, 10.7687f, 20.2719f, 10.7687f)
                    curveTo(20.8f, 10.7687f, 21.2312f, 11.2f, 21.2312f, 11.7281f)
                    close()
                }
            }
        }
            .build()
        return _icInsta!!
    }

private var _icInsta: ImageVector? = null
