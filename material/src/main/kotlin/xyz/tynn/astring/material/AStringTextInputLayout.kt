//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringTextInputLayout")

package xyz.tynn.astring.material

import com.google.android.material.textfield.TextInputLayout
import xyz.tynn.astring.AString

/**
 * Sets a content description for the end icon
 * @see TextInputLayout.setEndIconContentDescription
 */
fun TextInputLayout.setEndIconContentDescription(
    endIconContentDescription: AString,
) = setEndIconContentDescription(
    endIconContentDescription(context),
)

/**
 * Sets a content description for the error message
 * @see TextInputLayout.setErrorContentDescription
 */
fun TextInputLayout.setErrorContentDescription(
    errorContentDescription: AString,
) = setErrorContentDescription(
    errorContentDescription(context),
)

/**
 * Sets an error message
 * @see TextInputLayout.setError
 */
fun TextInputLayout.setError(
    error: AString,
) = setError(
    error(context),
)

/**
 * Sets a helper message
 * @see TextInputLayout.setHelperText
 */
fun TextInputLayout.setHelperText(
    helperText: AString,
) = setHelperText(
    helperText(context),
)

/**
 * Sets a hint
 * @see TextInputLayout.setHint
 */
fun TextInputLayout.setHint(
    hint: AString,
) = setHint(
    hint(context),
)

/**
 * Sets a placeholder text
 * @see TextInputLayout.setPlaceholderText
 */
fun TextInputLayout.setPlaceholderText(
    placeholderText: AString,
) = setPlaceholderText(
    placeholderText(context),
)

/**
 * Sets a prefix text
 * @see TextInputLayout.setPrefixText
 */
fun TextInputLayout.setPrefixText(
    prefixText: AString,
) = setPrefixText(
    prefixText(context),
)

/**
 * Sets a content description for the start icon
 * @see TextInputLayout.setStartIconContentDescription
 */
fun TextInputLayout.setStartIconContentDescription(
    startIconContentDescription: AString,
) = setStartIconContentDescription(
    startIconContentDescription(context),
)

/**
 * Sets the suffix text
 * @see TextInputLayout.setSuffixText
 */
fun TextInputLayout.setSuffixText(
    suffixText: AString,
) = setSuffixText(
    suffixText(context),
)
