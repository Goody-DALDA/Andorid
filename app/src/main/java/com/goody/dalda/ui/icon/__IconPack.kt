package com.goody.dalda.ui.icon

import androidx.compose.ui.graphics.vector.ImageVector
import com.goody.dalda.ui.icon.iconpack.Camera
import kotlin.collections.List as ____KtList

public object IconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val IconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Camera)
    return __AllIcons!!
  }
