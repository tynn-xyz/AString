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
) = setStateDescriptionCompat(
    this,
    stateDescription(context),
)

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
