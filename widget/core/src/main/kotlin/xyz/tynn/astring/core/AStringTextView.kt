//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringTextView")

package xyz.tynn.astring.core

import android.graphics.drawable.Drawable
import android.widget.TextView
import xyz.tynn.astring.AString

/**
 * Appends the text to the display buffer
 * @see TextView.append
 */
public fun TextView.append(
    text: AString,
): Unit = append(
    text(context),
)

/**
 * Appends the text to the display buffer
 * @see TextView.append
 */
public fun TextView.append(
    text: AString,
    start: Int,
    end: Int,
): Unit = append(
    text(context),
    start,
    end,
)

/**
 * Sets the error message
 * @see TextView.setError
 */
public fun TextView.setError(
    error: AString,
): Unit = setError(
    error(context),
)

/**
 * Sets the error message
 * @see TextView.setError
 */
public fun TextView.setError(
    error: AString,
    icon: Drawable?,
): Unit = setError(
    error(context),
    icon,
)

/**
 * Sets the text to be displayed when the text of the TextView is empty
 * @see TextView.setHint
 */
public fun TextView.setHint(
    hint: AString,
): Unit = setHint(
    hint(context),
)

/**
 * Sets the text to be displayed
 * @see TextView.setText
 */
public fun TextView.setText(
    text: AString,
): Unit = setText(
    text(context),
)

/**
 * Sets the text to be displayed
 * @see TextView.setText
 */
public fun TextView.setText(
    text: AString,
    type: TextView.BufferType,
): Unit = setText(
    text(context),
    type,
)
