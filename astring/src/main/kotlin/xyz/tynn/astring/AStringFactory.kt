//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringFactory")
@file:Suppress("FunctionName")

package xyz.tynn.astring

import android.content.Context
import android.content.pm.PackageInfo
import android.content.res.Configuration
import android.content.res.Resources.ID_NULL
import android.os.Parcel
import android.text.TextUtils
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import xyz.tynn.astring.Provider.AppIdProvider
import xyz.tynn.astring.Provider.AppVersionProvider
import xyz.tynn.astring.Wrapper.NULL
import java.util.Locale

/**
 * An `AString` always providing `null`
 */
@JvmField
public val nullAsAString: AString = NULL

/**
 * Returns [nullAsAString]
 *
 * This ensures that `null.asAString()` doesn't produce an overload ambiguity
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE", "UnusedReceiverParameter")]
public inline fun Nothing?.asAString(): AString = nullAsAString

/**
 * Returns [nullAsAString]
 *
 * This ensures that `AString(null)` doesn't produce an overload ambiguity
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE", "UNUSED_PARAMETER")]
public inline fun AString(
    nothing: Nothing?,
): AString = nullAsAString

/**
 * Returns [nullAsAString] when this [AString] is null
 *
 * This is a shorthand for
 * ```
 * this ?: nullAsAString
 * ```
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE")]
public inline fun AString?.asAString(): AString = AString(
    aString = this,
)

/**
 * Returns [nullAsAString] when [aString] is null
 *
 * This is a shorthand for
 * ```
 * aString ?: nullAsAString
 * ```
 */
@JvmName("wrapNullAsAString")
public fun AString(
    aString: AString?,
): AString = aString ?: nullAsAString

/**
 * Creates an `AString` from a `CharSequence?`
 *
 * Returns [nullAsAString] for null
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
 * Returns [nullAsAString] for null
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

/**
 * Creates an `AString` from a plurals string resource
 *
 * Returns [nullAsAString] for [ID_NULL]
 */
@JvmName("createFromQuantityStringResource")
public fun QuantityStringResource(
    @PluralsRes resId: Int,
    quantity: Int,
): AString = Resource.wrap(
    resId,
    quantity,
    null,
)

/**
 * Creates an `AString` from a plurals string resource with format arguments
 *
 * Returns [nullAsAString] for [ID_NULL]
 *
 * **Note** that [Parcel.writeValue] is used to parcel [formatArgs]
 * which might throw a [RuntimeException] for un-parcelable values
 */
@JvmName("createFromQuantityStringResource")
public fun QuantityStringResource(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg formatArgs: Any?,
): AString = Resource.wrap(
    resId,
    quantity,
    formatArgs,
)

/**
 * Creates an `AString` from a plurals text resource
 *
 * Returns [nullAsAString] for [ID_NULL]
 */
@JvmName("createFromQuantityTextResource")
public fun QuantityTextResource(
    @PluralsRes resId: Int,
    quantity: Int,
): AString = Resource.wrap(
    resId,
    quantity,
)

/**
 * Creates an `AString` from a string resource
 *
 * Returns [nullAsAString] for [ID_NULL]
 */
@JvmName("createFromStringResource")
public fun StringResource(
    @StringRes resId: Int,
): AString = Resource.wrap(
    resId,
    null,
    null,
)

/**
 * Creates an `AString` from a string resource with format arguments
 *
 * Returns [nullAsAString] for [ID_NULL]
 *
 * **Note** that [Parcel.writeValue] is used to parcel [formatArgs]
 * which might throw a [RuntimeException] for un-parcelable values
 */
@JvmName("createFromStringResource")
public fun StringResource(
    @StringRes resId: Int,
    vararg formatArgs: Any?,
): AString = Resource.wrap(
    resId,
    null,
    formatArgs,
)

/**
 * Creates an `AString` from a text resource
 *
 * Returns [nullAsAString] for [ID_NULL]
 */
@JvmName("createFromTextResource")
public fun TextResource(
    @StringRes resId: Int,
): AString = Resource.wrap(
    resId,
    null,
)

/**
 * Wraps the [AString] to format the string with [formatArgs]
 * using the first locale of [Configuration.getLocales]
 *
 * Returns [nullAsAString] if this [AString] is `null` or [nullAsAString]
 *
 * @see String.format
 */
@JvmName("formatWithAString")
public fun AString?.format(
    vararg formatArgs: Any?,
): AString = ToString.wrap(
    this,
    null,
    formatArgs,
)

/**
 * Wraps the [AString] to format the string with [formatArgs]
 * using the provided [locale] if not null or the first locale of
 * [Configuration.getLocales]
 *
 * Returns [nullAsAString] if this [AString] is `null` or [nullAsAString]
 *
 * @see String.format
 */
@JvmName("formatWithAString")
public fun AString?.format(
    locale: Locale?,
    vararg formatArgs: Any?,
): AString = ToString.wrap(
    this,
    locale,
    formatArgs,
)

/**
 * Wraps the [AString] to always return a [String] on invocation
 *
 * Returns [nullAsAString] if this [AString] is `null` or [nullAsAString]
 *
 * @see CharSequence.toString
 */
public fun AString?.wrapToString(): AString = ToString.wrap(
    this,
    null,
    null,
)

/**
 * An `AString` always providing the application id
 *
 * @see Context.getPackageName
 */
@JvmField
public val appIdAString: AString = AppIdProvider

/**
 * An `AString` always providing the application version
 *
 * @see PackageInfo.versionName
 */
@JvmField
public val appVersionAString: AString = AppVersionProvider
