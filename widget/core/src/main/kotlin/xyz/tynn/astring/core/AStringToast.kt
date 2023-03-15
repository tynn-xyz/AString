//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToast")

package xyz.tynn.astring.core

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import android.widget.Toast.*
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Makes a standard toast that contains the text
 *
 * @see Toast.makeText
 */
@SuppressLint("ShowToast")
@JvmName("makeText")
public fun makeToast(
    context: Context,
    text: AString?,
    @ToastDuration duration: Int,
): Toast = makeText(
    context,
    context.aString(text),
    duration,
)
