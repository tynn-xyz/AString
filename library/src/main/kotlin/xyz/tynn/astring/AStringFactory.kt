//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:Suppress("FunctionName")
@file:JvmName("AStringFactory")

package xyz.tynn.astring

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

@JvmName("createFromCharSequence")
fun CharSequence?.asAString() = if (this == null)
    NullValueWrapper.I
else CharSequenceWrapper(this)

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

@JvmName("createFromStringResource")
fun StringResource(
    @StringRes resId: Int,
) = if (resId == 0)
    NullValueWrapper.I
else StringResourceDelegate(
    resId,
    null,
)

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

@JvmName("createFromTextResource")
fun TextResource(
    @StringRes resId: Int,
) = if (resId == 0)
    NullValueWrapper.I
else TextResourceDelegate(
    resId,
)
