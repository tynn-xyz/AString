//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringAlertDialog")

package xyz.tynn.astring.appcompat

import android.content.DialogInterface.*
import android.os.Message
import androidx.appcompat.app.AlertDialog
import xyz.tynn.astring.AString
import xyz.tynn.astring.core.DialogInterfaceButton

/**
 * Sets a listener to be invoked when the button is pressed
 *
 * This method has no effect if called after [AlertDialog.show]
 *
 * @see AlertDialog.setButton
 */
public fun AlertDialog.setButton(
    @DialogInterfaceButton whichButton: Int,
    text: AString,
    listener: OnClickListener?,
): Unit = setButton(
    whichButton,
    text(context),
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
    text: AString,
    msg: Message?,
): Unit = setButton(
    whichButton,
    text(context),
    msg,
)

/**
 * Sets the message to display
 *
 * @see AlertDialog.setMessage
 */
public fun AlertDialog.setMessage(
    message: AString,
): Unit = setMessage(
    message(context),
)

/**
 * Sets the title to display
 *
 * @see AlertDialog.setTitle
 */
public fun AlertDialog.setTitle(
    title: AString,
): Unit = setTitle(
    title(context),
)
