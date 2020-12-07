//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringTextSwitcher")

package xyz.tynn.astring.core

import android.widget.TextSwitcher
import xyz.tynn.astring.AString

fun TextSwitcher.setCurrentText(
    text: AString,
) = setCurrentText(
    text(context),
)

fun TextSwitcher.setText(
    text: AString,
) = setText(
    text(context),
)
