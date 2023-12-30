//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:[JvmName("AStringFactory") JvmMultifileClass Suppress("FunctionName")]

package xyz.tynn.astring

import android.text.TextUtils

/**
 * Returns [AString.Null]
 *
 * This ensures that `null.asAString()` doesn't produce an overload ambiguity
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE", "UnusedReceiverParameter")]
public inline fun Nothing?.asAString(): AString = AString.Null

/**
 * Returns [AString.Null]
 *
 * This ensures that `AString(null)` doesn't produce an overload ambiguity
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE", "UNUSED_PARAMETER")]
public inline fun AString(
    nothing: Nothing?,
): AString = AString.Null

/**
 * Returns [AString.Null] when this [AString] is null
 *
 * This is a shorthand for
 * ```
 * this ?: AString.NULL
 * ```
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE")]
public inline fun AString?.asAString(): AString = AString(
    aString = this,
)

/**
 * Returns [AString.Null] when [aString] is null
 *
 * This is a shorthand for
 * ```
 * aString ?: AString.NULL
 * ```
 */
@JvmName("ensureNonNull")
public fun AString(
    aString: AString?,
): AString = aString ?: AString.Null

/**
 * Creates an `AString` from a `CharSequence?`
 *
 * Returns [AString.Null] for null
 *
 * **Note** that [TextUtils.writeToParcel] is used to parcel
 * the [CharSequence] which might lose some custom spans
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE")]
public inline fun CharSequence?.asAString(): AString = AString(
    value = this
)

/**
 * Creates an `AString` from a `CharSequence?`
 *
 * Returns [AString.Null] for null
 *
 * **Note** that [TextUtils.writeToParcel] is used to parcel
 * the [CharSequence] which might lose some custom spans
 */
@JvmName("createFromCharSequence")
public fun AString(
    value: CharSequence?,
): AString = Wrapper.wrap(
    value,
)
