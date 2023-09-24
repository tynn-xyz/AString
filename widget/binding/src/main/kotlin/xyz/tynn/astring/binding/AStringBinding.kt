//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringBinding")

package xyz.tynn.astring.binding

import android.content.Context
import androidx.databinding.InverseMethod
import xyz.tynn.astring.AString
import java.util.*
import java.util.Objects.requireNonNull

/**
 * AString converter methods to support two-way data binding
 */

@InverseMethod("wrap")
public fun Context.load(
    value: AString?,
): String? = value?.invoke(
    requireNonNull(this, "context"),
)?.toString()

@Suppress("unused")
public fun Context.wrap(
    value: String?,
): AString = AString(value)
