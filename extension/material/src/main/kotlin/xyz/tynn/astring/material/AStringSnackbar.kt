//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringSnackbar")

package xyz.tynn.astring.material

import android.view.View
import android.view.View.OnClickListener
import com.google.android.material.snackbar.BaseTransientBottomBar.Duration
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Makes a Snackbar to display a message
 *
 * An empty string is used if [text] resolved to `null`
 *
 * @see Snackbar.make
 */
@JvmName("make")
public fun makeSnackbar(
    view: View,
    text: AString?,
    @Duration duration: Int,
): Snackbar = make(
    view,
    view.aString(text) ?: "",
    duration,
)

/**
 * Sets the action to be displayed in this [Snackbar]
 *
 * @see Snackbar.setAction
 */
public fun Snackbar.setAction(
    text: AString?,
    listener: OnClickListener?,
): Snackbar = setAction(
    context.aString(text),
    listener,
)

/**
 * Updates the text in this [Snackbar]
 *
 * An empty string is used if [text] resolved to `null`
 *
 * @see Snackbar.setText
 */
public fun Snackbar.setText(
    text: AString?,
): Snackbar = setText(
    context.aString(text) ?: "",
)
