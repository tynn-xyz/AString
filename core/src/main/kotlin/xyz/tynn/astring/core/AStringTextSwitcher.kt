//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringTextSwitcher")

package xyz.tynn.astring.core

import android.widget.Switch
import android.widget.TextSwitcher
import xyz.tynn.astring.AString

/**
 *  Sets the text of the text view that is currently showing
 * @see TextSwitcher.setCurrentText
 */
fun TextSwitcher.setCurrentText(
    text: AString,
) = setCurrentText(
    text(context),
)

/**
 * Sets the text of the next view and switches to the next view
 * @see TextSwitcher.setText
 */
fun TextSwitcher.setText(
    text: AString,
) = setText(
    text(context),
)
