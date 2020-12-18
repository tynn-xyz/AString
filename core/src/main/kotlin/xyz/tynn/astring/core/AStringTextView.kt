//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringTextView")

package xyz.tynn.astring.core

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import xyz.tynn.astring.AString

/**
 * Appends the text to the display buffer
 * @see TextView.append
 */
fun TextView.append(
    text: AString,
) = append(
    text(context),
)

/**
 * Appends the text to the display buffer
 * @see TextView.append
 */
fun TextView.append(
    text: AString,
    start: Int,
    end: Int,
) = append(
    text(context),
    start,
    end,
)

/**
 * Sets the error message
 * @see TextView.setError
 */
fun TextView.setError(
    error: AString,
) = setError(
    error(context),
)

/**
 * Sets the error message
 * @see TextView.setError
 */
fun TextView.setError(
    error: AString,
    icon: Drawable?,
) = setError(
    error(context),
    icon,
)

/**
 * Sets the text to be displayed when the text of the TextView is empty
 * @see TextView.setHint
 */
fun TextView.setHint(
    hint: AString,
) = setHint(
    hint(context),
)

/**
 * Sets the text to be displayed
 * @see TextView.setText
 */
fun TextView.setText(
    text: AString,
) = setText(
    text(context),
)

/**
 * Sets the text to be displayed
 * @see TextView.setText
 */
fun TextView.setText(
    text: AString,
    type: TextView.BufferType,
) = setText(
    text(context),
    type,
)
