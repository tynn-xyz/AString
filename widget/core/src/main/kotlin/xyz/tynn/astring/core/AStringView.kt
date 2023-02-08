//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringView")

package xyz.tynn.astring.core

import android.view.View
import androidx.core.view.ViewCompat.setAccessibilityPaneTitle
import androidx.core.view.ViewCompat.setTooltipText
import xyz.tynn.astring.AString
import androidx.core.view.ViewCompat.setStateDescription as setStateDescriptionCompat

/**
 * Sets the accessibility pane title
 * @see View.setAccessibilityPaneTitle
 */
public fun View.setAccessibilityPaneTitle(
    accessibilityPaneTitle: AString,
): Unit = setAccessibilityPaneTitle(
    this,
    accessibilityPaneTitle(context),
)

/**
 * Sets the content description
 * @see View.setContentDescription
 */
public fun View.setContentDescription(
    contentDescription: AString,
): Unit = setContentDescription(
    contentDescription(context),
)

/**
 * Sets the state description
 * @see View.setStateDescription
 */
public fun View.setStateDescription(
    stateDescription: AString,
): Unit = setStateDescriptionCompat(
    this,
    stateDescription(context),
)

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
