//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringAlertDialog")

package xyz.tynn.astring.core

import android.app.AlertDialog
import android.content.DialogInterface.*
import android.os.Message
import androidx.annotation.IntDef
import xyz.tynn.astring.AString
import kotlin.annotation.AnnotationRetention.SOURCE

@Retention(SOURCE)
@IntDef(BUTTON_POSITIVE, BUTTON_NEGATIVE, BUTTON_NEUTRAL)
private annotation class DialogInterfaceButton

/**
 * Sets a listener to be invoked when the button is pressed
 *
 * This method has no effect if called after [AlertDialog.show]
 *
 * @see AlertDialog.setButton
 */
fun AlertDialog.setButton(
    @DialogInterfaceButton whichButton: Int,
    text: AString,
    listener: OnClickListener?,
) = setButton(
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
fun AlertDialog.setButton(
    @DialogInterfaceButton whichButton: Int,
    text: AString,
    msg: Message?,
) = setButton(
    whichButton,
    text(context),
    msg,
)

/**
 * Sets the message to display
 *
 * @see AlertDialog.setMessage
 */
fun AlertDialog.setMessage(
    message: AString,
) = setMessage(
    message(context),
)

/**
 * Sets the title to display
 *
 * @see AlertDialog.setTitle
 */
fun AlertDialog.setTitle(
    title: AString,
) = setTitle(
    title(context),
)
