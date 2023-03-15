//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringAlertDialog")

package xyz.tynn.astring.core

import android.app.AlertDialog
import android.content.DialogInterface.*
import android.os.Message
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets a listener to be invoked when the button is pressed
 *
 * This method has no effect if called after [AlertDialog.show]
 *
 * @see AlertDialog.setButton
 */
public fun AlertDialog.setButton(
    @DialogInterfaceButton whichButton: Int,
    text: AString?,
    listener: OnClickListener?,
): Unit = setButton(
    whichButton,
    context.aString(text),
    listener,
)

/**
 * Sets a message to be sent when a button is pressed
 *
 * This method has no effect if called after [AlertDialog.show]
 *
 * @see AlertDialog.setButton
 */
public fun AlertDialog.setButton(
    @DialogInterfaceButton whichButton: Int,
    text: AString?,
    msg: Message?,
): Unit = setButton(
    whichButton,
    context.aString(text),
    msg,
)

/**
 * Sets the message to display
 *
 * @see AlertDialog.setMessage
 */
public fun AlertDialog.setMessage(
    message: AString?,
): Unit = setMessage(
    context.aString(message),
)

/**
 * Sets the title to display
 *
 * @see AlertDialog.setTitle
 */
public fun AlertDialog.setTitle(
    title: AString?,
): Unit = setTitle(
    context.aString(title),
)
