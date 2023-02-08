//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToggleButton")

package xyz.tynn.astring.core

import android.widget.ToggleButton
import xyz.tynn.astring.AString

/**
 * Sets the text displayed when the button is not in the checked state
 * @see ToggleButton.setTextOff
 */
public fun ToggleButton.setTextOff(
    textOff: AString,
): Unit = setTextOff(
    textOff(context),
)

/**
 * Sets the text displayed when the button is in the checked state
 * @see ToggleButton.setTextOn
 */
public fun ToggleButton.setTextOn(
    textOn: AString,
): Unit = setTextOn(
    textOn(context),
)
