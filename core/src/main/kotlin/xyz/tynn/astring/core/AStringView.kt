//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringView")

package xyz.tynn.astring.core

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.R
import android.view.View
import android.widget.TextSwitcher
import androidx.core.view.ViewCompat.setAccessibilityPaneTitle
import androidx.core.view.ViewCompat.setTooltipText
import xyz.tynn.astring.AString

/**
 * Sets the accessibility pane title
 * @see View.setAccessibilityPaneTitle
 */
fun View.setAccessibilityPaneTitle(
    accessibilityPaneTitle: AString,
) = setAccessibilityPaneTitle(
    this,
    accessibilityPaneTitle(context),
)

/**
 * Sets the content description
 * @see View.setContentDescription
 */
fun View.setContentDescription(
    contentDescription: AString,
) = setContentDescription(
    contentDescription(context),
)

/**
 * Sets the state description
 * @see View.setStateDescription
 */
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

/**
 * Sets the tooltip for the view
 * @see View.setTooltipText
 */
fun View.setTooltipText(
    tooltipText: AString,
) = setTooltipText(
    this,
    tooltipText(context),
)
