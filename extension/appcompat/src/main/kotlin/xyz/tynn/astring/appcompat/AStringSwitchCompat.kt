//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringSwitchCompat")

package xyz.tynn.astring.appcompat

import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets the text displayed when the button is not in the checked state
 * @see SwitchCompat.setTextOff
 */
@BindingAdapter("android:textOff")
public fun SwitchCompat.setTextOff(
    textOff: AString?,
): Unit = setTextOff(
    aString(textOff),
)

/**
 * Sets the text displayed when the button is in the checked state
 * @see SwitchCompat.setTextOn
 */
@BindingAdapter("android:textOn")
public fun SwitchCompat.setTextOn(
    textOn: AString?,
): Unit = setTextOn(
    aString(textOn),
)
