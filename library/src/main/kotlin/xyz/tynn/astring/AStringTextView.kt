//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringTextView")

package xyz.tynn.astring

import android.graphics.drawable.Drawable
import android.widget.TextView

fun TextView.append(
    text: AString,
) = append(
    text(context),
)

fun TextView.append(
    text: AString,
    start: Int,
    end: Int,
) = append(
    text(context),
    start,
    end,
)

fun TextView.setError(
    error: AString,
) = setError(
    error(context),
)

fun TextView.setError(
    error: AString,
    icon: Drawable?,
) = setError(
    error(context),
    icon,
)

fun TextView.setHint(
    hint: AString,
) = setHint(
    hint(context),
)

fun TextView.setText(
    text: AString,
) = setText(
    text(context),
)

fun TextView.setText(
    text: AString,
    type: TextView.BufferType,
) = setText(
    text(context),
    type,
)
