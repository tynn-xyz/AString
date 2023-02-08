//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToast")

package xyz.tynn.astring.core

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import android.widget.Toast.*
import androidx.annotation.IntDef
import xyz.tynn.astring.AString
import java.util.Objects.requireNonNull
import kotlin.annotation.AnnotationRetention.SOURCE

@Retention(SOURCE)
@IntDef(LENGTH_LONG, LENGTH_SHORT)
private annotation class ToastDuration

/**
 * Makes a standard toast that contains the text
 *
 * @see Toast.makeText
 */
@SuppressLint("ShowToast")
@JvmName("makeText")
public fun makeToast(
    context: Context,
    text: AString,
    @ToastDuration duration: Int,
): Toast = makeText(
    context,
    text(context),
    duration,
)

/**
 * Updates the text in this [Toast]
 *
 * The [context] is required due to the private [Context] reference within the [Toast]
 *
 * @see Toast.setText
 */
@Deprecated(
    "Use AString directly",
    ReplaceWith(
        "setText(context.aString(text))",
        "xyz.tynn.astring.aString"
    ),
)
public fun Toast.setText(
    context: Context,
    text: AString,
): Unit = setText(
    text(requireNonNull(context, "context")),
)
