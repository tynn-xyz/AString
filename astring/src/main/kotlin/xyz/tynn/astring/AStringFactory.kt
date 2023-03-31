//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringFactory")
@file:Suppress("FunctionName")

package xyz.tynn.astring

import android.content.Context
import android.content.pm.PackageInfo
import android.content.res.Resources.ID_NULL
import android.os.Parcel
import android.text.TextUtils
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import xyz.tynn.astring.ContextValueProvider.AppIdProvider
import xyz.tynn.astring.ContextValueProvider.AppVersionProvider

/**
 * An `AString` always providing `null`
 */
@JvmField
public val nullAsAString: AString = CharSequenceWrapper.NULL

/**
 * Creates an `AString` from a `CharSequence?`
 *
 * Returns [nullAsAString] for null
 *
 * **Note** that [TextUtils.writeToParcel] is used to parcel
 * the [CharSequence] which might lose some custom styles
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE")]
public inline fun CharSequence?.asAString(): AString = AString(this)

/**
 * Creates an `AString` from a `CharSequence?`
 *
 * Returns [nullAsAString] for null
 *
 * **Note** that [TextUtils.writeToParcel] is used to parcel
 * the [CharSequence] which might lose some custom styles
 */
@JvmName("createFromCharSequence")
public fun AString(
    value: CharSequence?,
): AString = if (value == null)
    nullAsAString
else CharSequenceWrapper(value)

/**
 * Creates an `AString` from a plurals string resource
 *
 * Returns [nullAsAString] for [ID_NULL]
 */
@JvmName("createFromQuantityStringResource")
public fun QuantityStringResource(
    @PluralsRes resId: Int,
    quantity: Int,
): AString = if (resId == ID_NULL)
    nullAsAString
else ResourceDelegate.quantityString(
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
): AString = if (resId == ID_NULL)
    nullAsAString
else ResourceDelegate.quantityString(
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
): AString = if (resId == ID_NULL)
    nullAsAString
else ResourceDelegate.quantityText(
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
): AString = if (resId == ID_NULL)
    nullAsAString
else ResourceDelegate.string(
    resId,
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
): AString = if (resId == ID_NULL)
    nullAsAString
else ResourceDelegate.string(
    resId,
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
): AString = if (resId == ID_NULL)
    nullAsAString
else ResourceDelegate.text(
    resId,
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
