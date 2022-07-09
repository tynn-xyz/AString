//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringFactory")
@file:Suppress("FunctionName")

package xyz.tynn.astring

import android.content.res.Resources.ID_NULL
import android.os.Parcel
import android.text.TextUtils
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import xyz.tynn.astring.ValueProvider.*

@JvmField
public val nullAsAString: AString = NullValueProvider

/**
 * Creates an `AString` from a `CharSequence?`
 *
 * Returns [nullAsAString] for null
 *
 * **Note** that [TextUtils.writeToParcel] is used to parcel
 * the [CharSequence] which might lose some custom styles
 */
@JvmName("createFromCharSequence")
public fun CharSequence?.asAString(): AString = if (this == null)
    nullAsAString
else CharSequenceWrapper(this)

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
else QuantityStringResourceDelegate(
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
else QuantityStringResourceDelegate(
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
else QuantityTextResourceDelegate(
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
else StringResourceDelegate(
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
else StringResourceDelegate(
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
else TextResourceDelegate(
    resId,
)

@JvmField
public val appIdAString: AString = AppIdProvider

@JvmField
public val appVersionAString: AString = AppVersionProvider
