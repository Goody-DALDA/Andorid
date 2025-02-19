package com.goody.dalda.extention

import android.os.Build
import android.os.Bundle

fun <T> Bundle.getParcelableCompat(
    key: String,
    clazz: Class<T>,
) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    this.getParcelable(key, clazz)
} else {
    this.getParcelable(key)
}
