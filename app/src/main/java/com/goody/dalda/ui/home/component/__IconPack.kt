package com.goody.dalda.ui.home.component

import androidx.compose.ui.graphics.vector.ImageVector
import com.goody.dalda.ui.home.component.iconpack.IcCamera
import com.goody.dalda.ui.home.component.iconpack.IcComingSoon
import com.goody.dalda.ui.home.component.iconpack.IcInsta
import com.goody.dalda.ui.home.component.iconpack.IcLink
import com.goody.dalda.ui.home.component.iconpack.IcMenuDot
import kotlin.collections.List as ____KtList

public object IconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val IconPack.AllIcons: ____KtList<ImageVector>
    get() {
        if (__AllIcons != null) {
            return __AllIcons!!
        }
        __AllIcons = listOf(IcInsta, IcLink, IcMenuDot, IcCamera, IcComingSoon)
        return __AllIcons!!
    }
