//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringSwitch")

package xyz.tynn.astring.core

import android.widget.Switch
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets the text displayed when the button is not in the checked state
 * @see Switch.setTextOff
 */
public fun Switch.setTextOff(
    textOff: AString?,
): Unit = setTextOff(
    aString(textOff),
)

/**
 * Sets the text displayed when the button is in the checked state
 * @see Switch.setTextOn
 */
public fun Switch.setTextOn(
    textOn: AString?,
): Unit = setTextOn(
    aString(textOn),
)
