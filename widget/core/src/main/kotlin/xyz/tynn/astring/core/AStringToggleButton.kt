//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToggleButton")

package xyz.tynn.astring.core

import android.widget.ToggleButton
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets the text displayed when the button is not in the checked state
 * @see ToggleButton.setTextOff
 */
public fun ToggleButton.setTextOff(
    textOff: AString?,
): Unit = setTextOff(
    aString(textOff),
)

/**
 * Sets the text displayed when the button is in the checked state
 * @see ToggleButton.setTextOn
 */
public fun ToggleButton.setTextOn(
    textOn: AString?,
): Unit = setTextOn(
    aString(textOn),
)
