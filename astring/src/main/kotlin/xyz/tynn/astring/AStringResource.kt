//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:[JvmName("AStringFactory") JvmMultifileClass Suppress("FunctionName")]

package xyz.tynn.astring

import android.content.res.Resources.ID_NULL
import android.os.Parcel
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

/**
 * Creates an `AString` from a plurals string resource
 *
 * Returns [AString.Null] for [ID_NULL]
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
 * Returns [AString.Null] for [ID_NULL]
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
 * Returns [AString.Null] for [ID_NULL]
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
 * Returns [AString.Null] for [ID_NULL]
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
 * Returns [AString.Null] for [ID_NULL]
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
 * Returns [AString.Null] for [ID_NULL]
 */
@JvmName("createFromTextResource")
public fun TextResource(
    @StringRes resId: Int,
): AString = Resource.wrap(
    resId,
    null,
)
