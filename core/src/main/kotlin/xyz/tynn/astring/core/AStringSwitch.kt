//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringSwitch")

package xyz.tynn.astring.core

import android.widget.Switch
import xyz.tynn.astring.AString

fun Switch.setTextOff(
    textOff: AString,
) = setTextOff(
    textOff(context),
)

fun Switch.setTextOn(
    textOn: AString,
) = setTextOn(
    textOn(context),
)
