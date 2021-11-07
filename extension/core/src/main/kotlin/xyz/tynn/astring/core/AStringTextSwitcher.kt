//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringTextSwitcher")

package xyz.tynn.astring.core

import android.widget.TextSwitcher
import xyz.tynn.astring.AString

/**
 *  Sets the text of the text view that is currently showing
 * @see TextSwitcher.setCurrentText
 */
public fun TextSwitcher.setCurrentText(
    text: AString,
): Unit = setCurrentText(
    text(context),
)

/**
 * Sets the text of the next view and switches to the next view
 * @see TextSwitcher.setText
 */
public fun TextSwitcher.setText(
    text: AString,
): Unit = setText(
    text(context),
)
