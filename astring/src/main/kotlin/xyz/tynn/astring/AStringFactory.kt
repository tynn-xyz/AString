//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:Suppress("FunctionName")
@file:JvmName("AStringFactory")

package xyz.tynn.astring

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

@JvmField
public val nullAsAString: AString = NullValueWrapper.I

/**
 * Creates an `AString` from a `CharSequence?`
 *
 * Returns [nullAsAString] for null
 */
@JvmName("createFromCharSequence")
public fun CharSequence?.asAString(): AString = if (this == null)
    nullAsAString
else CharSequenceWrapper(this)

/**
 * Creates an `AString` from a plurals string resource
 *
 * Returns [nullAsAString] for 0
 */
@JvmName("createFromQuantityStringResource")
public fun QuantityStringResource(
    @PluralsRes resId: Int,
    quantity: Int,
): AString = if (resId == 0)
    nullAsAString
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
public fun QuantityStringResource(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg formatArgs: Any?,
): AString = if (resId == 0)
    nullAsAString
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
public fun QuantityTextResource(
    @PluralsRes resId: Int,
    quantity: Int,
): AString = if (resId == 0)
    nullAsAString
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
public fun StringResource(
    @StringRes resId: Int,
): AString = if (resId == 0)
    nullAsAString
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
public fun StringResource(
    @StringRes resId: Int,
    vararg formatArgs: Any?,
): AString = if (resId == 0)
    nullAsAString
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
public fun TextResource(
    @StringRes resId: Int,
): AString = if (resId == 0)
    nullAsAString
else TextResourceDelegate(
    resId,
)
