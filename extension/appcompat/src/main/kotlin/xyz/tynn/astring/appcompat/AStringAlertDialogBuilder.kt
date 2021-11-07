//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringAlertDialogBuilder")

package xyz.tynn.astring.appcompat

import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AlertDialog.Builder
import xyz.tynn.astring.AString

/**
 * Sets a listener to be invoked when the negative button is pressed
 *
 * @see Builder.setNegativeButton
 */
public fun <B : Builder> B.setNegativeButton(
    text: AString,
    listener: OnClickListener?,
): B = apply {
    setNegativeButton(
        text(context),
        listener,
    )
}

/**
 * Sets a listener to be invoked when the neutral button is pressed
 *
 * @see Builder.setNeutralButton
 */
public fun <B : Builder> B.setNeutralButton(
    text: AString,
    listener: OnClickListener?,
): B = apply {
    setNeutralButton(
        text(context),
        listener,
    )
}

/**
 * Sets a listener to be invoked when the positive button is pressed
 *
 * @see Builder.setPositiveButton
 */
public fun <B : Builder> B.setPositiveButton(
    text: AString,
    listener: OnClickListener?,
): B = apply {
    setPositiveButton(
        text(context),
        listener,
    )
}

/**
 * Sets the message
 *
 * @see Builder.setMessage
 */
public fun <B : Builder> B.setMessage(
    message: AString,
): B = apply {
    setMessage(
        message(context),
    )
}

/**
 * Sets the title
 *
 * @see Builder.setTitle
 */
public fun <B : Builder> B.setTitle(
    title: AString,
): B = apply {
    setTitle(
        title(context),
    )
}
