//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:Suppress("FunctionName")
@file:JvmName("AStringFactory")

package xyz.tynn.astring

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

@JvmField
val nullAsAString: AString = NullValueWrapper.I

/**
 * Creates an `AString` from a `CharSequence?`
 *
 * Returns [nullAsAString] for null
 */
@JvmName("createFromCharSequence")
fun CharSequence?.asAString() = if (this == null)
    NullValueWrapper.I
else CharSequenceWrapper(this)

/**
 * Creates an `AString` from a plurals string resource
 *
 * Returns [nullAsAString] for 0
 */
@JvmName("createFromQuantityStringResource")
fun QuantityStringResource(
    @PluralsRes resId: Int,
    quantity: Int,
) = if (resId == 0)
    NullValueWrapper.I
else QuantityStringResourceDelegate(
    resId,
    quantity,
    null,
)

/**
 * Creates an `AString` from a plurals string resource with format arguments
 *
 * Returns [nullAsAString] for 0
 */
@JvmName("createFromQuantityStringResource")
fun QuantityStringResource(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg formatArgs: Any?,
) = if (resId == 0)
    NullValueWrapper.I
else QuantityStringResourceDelegate(
    resId,
    quantity,
    formatArgs,
)

/**
 * Creates an `AString` from a plurals text resource
 *
 * Returns [nullAsAString] for 0
 */
@JvmName("createFromQuantityTextResource")
fun QuantityTextResource(
    @PluralsRes resId: Int,
    quantity: Int,
) = if (resId == 0)
    NullValueWrapper.I
else QuantityTextResourceDelegate(
    resId,
    quantity,
)

/**
 * Creates an `AString` from a string resource
 *
 * Returns [nullAsAString] for 0
 */
@JvmName("createFromStringResource")
fun StringResource(
    @StringRes resId: Int,
) = if (resId == 0)
    NullValueWrapper.I
else StringResourceDelegate(
    resId,
    null,
)

/**
 * Creates an `AString` from a string resource with format arguments
 *
 * Returns [nullAsAString] for 0
 */
@JvmName("createFromStringResource")
fun StringResource(
    @StringRes resId: Int,
    vararg formatArgs: Any?,
) = if (resId == 0)
    NullValueWrapper.I
else StringResourceDelegate(
    resId,
    formatArgs,
)

/**
 * Creates an `AString` from a text resource
 *
 * Returns [nullAsAString] for 0
 */
@JvmName("createFromTextResource")
fun TextResource(
    @StringRes resId: Int,
) = if (resId == 0)
    NullValueWrapper.I
else TextResourceDelegate(
    resId,
)
