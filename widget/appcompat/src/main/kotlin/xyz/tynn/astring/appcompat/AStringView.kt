//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringView")

package xyz.tynn.astring.appcompat

import android.view.View
import androidx.appcompat.widget.TooltipCompat.setTooltipText
import xyz.tynn.astring.AString

/**
 * Sets the tooltip for the view
 * @see View.setTooltipText
 */
public fun View.setTooltipText(
    tooltipText: AString,
): Unit = setTooltipText(
    this,
    tooltipText(context),
)
