package com.goody.dalda.ui.icon

import androidx.compose.ui.graphics.vector.ImageVector
import com.goody.dalda.ui.icon.iconpack.IcCamera
import com.goody.dalda.ui.icon.iconpack.IcComingSoon
import com.goody.dalda.ui.icon.iconpack.IcEmptyCard
import com.goody.dalda.ui.icon.iconpack.IcInsta
import com.goody.dalda.ui.icon.iconpack.IcLink
import com.goody.dalda.ui.icon.iconpack.IcMenuDot
import kotlin.collections.List as ____KtList

public object IconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val IconPack.AllIcons: ____KtList<ImageVector>
    get() {
        if (__AllIcons != null) {
            return __AllIcons!!
        }
        __AllIcons = listOf(IcInsta, IcLink, IcMenuDot, IcCamera, IcComingSoon, IcEmptyCard)
        return __AllIcons!!
    }
