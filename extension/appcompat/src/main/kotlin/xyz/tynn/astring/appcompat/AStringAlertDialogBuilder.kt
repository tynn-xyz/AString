//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringAlertDialogBuilder")

package xyz.tynn.astring.appcompat

import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AlertDialog.Builder
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets a listener to be invoked when the negative button is pressed
 *
 * @see Builder.setNegativeButton
 */
public fun <B : Builder> B.setNegativeButton(
    text: AString?,
    listener: OnClickListener?,
): B = apply {
    setNegativeButton(
        context.aString(text),
        listener,
    )
}

/**
 * Sets a listener to be invoked when the neutral button is pressed
 *
 * @see Builder.setNeutralButton
 */
public fun <B : Builder> B.setNeutralButton(
    text: AString?,
    listener: OnClickListener?,
): B = apply {
    setNeutralButton(
        context.aString(text),
        listener,
    )
}

/**
 * Sets a listener to be invoked when the positive button is pressed
 *
 * @see Builder.setPositiveButton
 */
public fun <B : Builder> B.setPositiveButton(
    text: AString?,
    listener: OnClickListener?,
): B = apply {
    setPositiveButton(
        context.aString(text),
        listener,
    )
}

/**
 * Sets the message
 *
 * @see Builder.setMessage
 */
public fun <B : Builder> B.setMessage(
    message: AString?,
): B = apply {
    setMessage(
        context.aString(message),
    )
}

/**
 * Sets the title
 *
 * @see Builder.setTitle
 */
public fun <B : Builder> B.setTitle(
    title: AString?,
): B = apply {
    setTitle(
        context.aString(title),
    )
}
