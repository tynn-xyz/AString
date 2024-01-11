//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringView")

package xyz.tynn.astring.appcompat

import android.view.View
import androidx.appcompat.widget.TooltipCompat.setTooltipText
import androidx.databinding.BindingAdapter
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets the tooltip for the view
 * @see View.setTooltipText
 */
@BindingAdapter("android:tooltipText")
public fun View.setTooltipText(
    tooltipText: AString?,
): Unit = setTooltipText(
    this,
    aString(tooltipText),
)
