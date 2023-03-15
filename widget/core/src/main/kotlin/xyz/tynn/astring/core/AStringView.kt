//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringView")

package xyz.tynn.astring.core

import android.view.View
import androidx.core.view.ViewCompat.*
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets the accessibility pane title
 * @see View.setAccessibilityPaneTitle
 */
public fun View.setAccessibilityPaneTitle(
    accessibilityPaneTitle: AString?,
): Unit = setAccessibilityPaneTitle(
    this,
    aString(accessibilityPaneTitle),
)

/**
 * Sets the content description
 * @see View.setContentDescription
 */
public fun View.setContentDescription(
    contentDescription: AString?,
): Unit = setContentDescription(
    aString(contentDescription),
)

/**
 * Sets the state description
 * @see View.setStateDescription
 */
public fun View.setStateDescription(
    stateDescription: AString?,
): Unit = setStateDescription(
    this,
    aString(stateDescription),
)

/**
 * Sets the tooltip for the view
 * @see View.setTooltipText
 */
public fun View.setTooltipText(
    tooltipText: AString?,
): Unit = setTooltipText(
    this,
    aString(tooltipText),
)
