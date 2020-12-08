//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringTextInputLayout")

package xyz.tynn.astring.material

import com.google.android.material.textfield.TextInputLayout
import xyz.tynn.astring.AString

fun TextInputLayout.setEndIconContentDescription(
    endIconContentDescription: AString,
) = setEndIconContentDescription(
    endIconContentDescription(context),
)

fun TextInputLayout.setErrorContentDescription(
    errorContentDescription: AString,
) = setErrorContentDescription(
    errorContentDescription(context),
)

fun TextInputLayout.setError(
    error: AString,
) = setError(
    error(context),
)

fun TextInputLayout.setHelperText(
    helperText: AString,
) = setHelperText(
    helperText(context),
)

fun TextInputLayout.setHint(
    hint: AString,
) = setHint(
    hint(context),
)

fun TextInputLayout.setPlaceholderText(
    placeholderText: AString,
) = setPlaceholderText(
    placeholderText(context),
)

fun TextInputLayout.setPrefixText(
    prefixText: AString,
) = setPrefixText(
    prefixText(context),
)

fun TextInputLayout.setStartIconContentDescription(
    startIconContentDescription: AString,
) = setStartIconContentDescription(
    startIconContentDescription(context),
)

fun TextInputLayout.setSuffixText(
    suffixText: AString,
) = setSuffixText(
    suffixText(context),
)
