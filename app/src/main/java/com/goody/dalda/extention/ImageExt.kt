package com.goody.dalda.extention

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.view.View
import androidx.camera.core.ImageProxy
import androidx.camera.view.PreviewView
import java.nio.ByteBuffer

fun ImageProxy.toBitmap(): Bitmap {
    val buffer: ByteBuffer = planes[0].buffer
    val bytes = buffer.toByteArray()
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}

fun Bitmap.rotate(degrees: Float): Bitmap =
    Bitmap.createBitmap(this, 0, 0, width, height, Matrix().apply { postRotate(degrees) }, true)

fun Bitmap.resizeWidth(preview: PreviewView): Bitmap {
    val cw = (preview.width * height) / preview.height
    var x = 0

    if(width > cw) x = (width - cw) / 2

    return Bitmap.createBitmap(this, x, 0, cw, height)
}

private fun ByteBuffer.toByteArray(): ByteArray {
    rewind()    // 버퍼의 포지션을 0으로 되돌림
    val data = ByteArray(remaining())
    get(data)   // 바이트 버퍼를 바이트 배열로 복사함
    return data // 바이트 배열 반환함
}

fun Bitmap.cropBitmap(parent: View, rect: View): Bitmap {
    val width = width
    val height = height
    val rectWidth = (rect.width * width) / parent.width
    val rectHeight = (rect.height * height) / parent.height

    if(width < rectWidth && height < rectHeight)
        return this

    var x = 0
    var y = 0

    if(width > rectWidth) x = (width - rectWidth) / 2

    if(height > rectHeight) y = (height - rectHeight)/2

    var cw = rectWidth
    var ch = rectHeight

    if(rectWidth > width)
        cw = width

    if(rectHeight > height)
        ch = height


    return Bitmap.createBitmap(this, x, y, cw, ch)
}