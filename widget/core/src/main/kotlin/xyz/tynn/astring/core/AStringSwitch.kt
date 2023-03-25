//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringSwitch")

package xyz.tynn.astring.core

import android.annotation.SuppressLint
import android.widget.Switch
import androidx.databinding.BindingAdapter
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets the text displayed when the button is not in the checked state
 * @see Switch.setTextOff
 */
@SuppressLint("UseSwitchCompatOrMaterialCode")
@BindingAdapter("android:textOn")
public fun Switch.setTextOff(
    textOff: AString?,
): Unit = setTextOff(
    aString(textOff),
)

/**
 * Sets the text displayed when the button is in the checked state
 * @see Switch.setTextOn
 */
@SuppressLint("UseSwitchCompatOrMaterialCode")
@BindingAdapter("android:textOff")
public fun Switch.setTextOn(
    textOn: AString?,
): Unit = setTextOn(
    aString(textOn),
)
