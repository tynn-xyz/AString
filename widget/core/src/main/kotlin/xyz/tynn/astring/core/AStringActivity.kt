//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringActivity")

package xyz.tynn.astring.core

import android.app.Activity
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets the title of the activity
 *
 * @see Activity.setTitle
 */
public fun Activity.setTitle(
    title: AString?,
): Unit = setTitle(
    aString(title),
)
