//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToggleButton")

package xyz.tynn.astring.core

import android.widget.ToggleButton
import xyz.tynn.astring.AString

fun ToggleButton.setTextOff(
    textOff: AString,
) = setTextOff(
    textOff(context),
)

fun ToggleButton.setTextOn(
    textOn: AString,
) = setTextOn(
    textOn(context),
)
