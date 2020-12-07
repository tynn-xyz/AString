//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringView")

package xyz.tynn.astring.core

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.R
import android.view.View
import androidx.core.view.ViewCompat.setAccessibilityPaneTitle
import androidx.core.view.ViewCompat.setTooltipText
import xyz.tynn.astring.AString

fun View.setAccessibilityPaneTitle(
    accessibilityPaneTitle: AString,
) = setAccessibilityPaneTitle(
    this,
    accessibilityPaneTitle(context),
)

fun View.setContentDescription(
    contentDescription: AString,
) = setContentDescription(
    contentDescription(context),
)

fun View.setStateDescription(
    stateDescription: AString,
) = setStateDescription(
    this,
    stateDescription(context),
)

// TODO replace with ViewCompat 1.5.0
private fun setStateDescription(v: View, s: CharSequence?) {
    if (SDK_INT >= R) v.stateDescription = s
}

fun View.setTooltipText(
    tooltipText: AString,
) = setTooltipText(
    this,
    tooltipText(context),
)
