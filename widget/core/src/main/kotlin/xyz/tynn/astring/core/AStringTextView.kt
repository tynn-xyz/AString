//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringTextView")

package xyz.tynn.astring.core

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Appends the text to the display buffer
 * @see TextView.append
 */
public fun TextView.append(
    text: AString?,
): Unit = append(
    aString(text),
)

/**
 * Appends the text to the display buffer
 * @see TextView.append
 */
public fun TextView.append(
    text: AString?,
    start: Int,
    end: Int,
): Unit = append(
    aString(text),
    start,
    end,
)

/**
 * Sets the error message
 * @see TextView.setError
 */
public fun TextView.setError(
    error: AString?,
): Unit = setError(
    aString(error),
)

/**
 * Sets the error message
 * @see TextView.setError
 */
public fun TextView.setError(
    error: AString?,
    icon: Drawable?,
): Unit = setError(
    aString(error),
    icon,
)

/**
 * Sets the text to be displayed when the text of the TextView is empty
 * @see TextView.setHint
 */
@BindingAdapter("android:hint")
public fun TextView.setHint(
    hint: AString?,
): Unit = setHint(
    aString(hint),
)

/**
 * Sets the text to be displayed
 * @see TextView.setText
 */
@BindingAdapter("android:text")
public fun TextView.setText(
    text: AString?,
): Unit = setText(
    aString(text),
)

/**
 * Sets the text to be displayed
 * @see TextView.setText
 */
public fun TextView.setText(
    text: AString?,
    type: TextView.BufferType,
): Unit = setText(
    aString(text),
    type,
)
