//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringAlertDialogBuilder")

package xyz.tynn.astring.core

import android.app.AlertDialog.Builder
import android.content.DialogInterface.OnClickListener
import xyz.tynn.astring.AString

/**
 * Sets a listener to be invoked when the negative button is pressed
 *
 * @see Builder.setNegativeButton
 */
fun <B : Builder> B.setNegativeButton(
    text: AString,
    listener: OnClickListener?,
): B {
    setNegativeButton(
        text(context),
        listener,
    )
    return this
}

/**
 * Sets a listener to be invoked when the neutral button is pressed
 *
 * @see Builder.setNeutralButton
 */
fun <B : Builder> B.setNeutralButton(
    text: AString,
    listener: OnClickListener?,
): B {
    setNeutralButton(
        text(context),
        listener,
    )
    return this
}

/**
 * Sets a listener to be invoked when the positive button is pressed
 *
 * @see Builder.setPositiveButton
 */
fun <B : Builder> B.setPositiveButton(
    text: AString,
    listener: OnClickListener?,
): B {
    setPositiveButton(
        text(context),
        listener,
    )
    return this
}

/**
 * Sets the message
 *
 * @see Builder.setMessage
 */
fun <B : Builder> B.setMessage(
    message: AString,
): B {
    setMessage(
        message(context),
    )
    return this
}

/**
 * Sets the title
 *
 * @see Builder.setTitle
 */
fun <B : Builder> B.setTitle(
    title: AString,
): B {
    setTitle(
        title(context),
    )
    return this
}
