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
public fun TextInputLayout.setEndIconContentDescription(
    endIconContentDescription: AString,
): Unit = setEndIconContentDescription(
    endIconContentDescription(context),
)

/**
 * Sets a content description for the error message
 * @see TextInputLayout.setErrorContentDescription
 */
public fun TextInputLayout.setErrorContentDescription(
    errorContentDescription: AString,
): Unit = setErrorContentDescription(
    errorContentDescription(context),
)

/**
 * Sets an error message
 * @see TextInputLayout.setError
 */
public fun TextInputLayout.setError(
    error: AString,
): Unit = setError(
    error(context),
)

/**
 * Sets a helper message
 * @see TextInputLayout.setHelperText
 */
public fun TextInputLayout.setHelperText(
    helperText: AString,
): Unit = setHelperText(
    helperText(context),
)

/**
 * Sets a hint
 * @see TextInputLayout.setHint
 */
public fun TextInputLayout.setHint(
    hint: AString,
): Unit = setHint(
    hint(context),
)

/**
 * Sets a placeholder text
 * @see TextInputLayout.setPlaceholderText
 */
public fun TextInputLayout.setPlaceholderText(
    placeholderText: AString,
): Unit = setPlaceholderText(
    placeholderText(context),
)

/**
 * Sets a prefix text
 * @see TextInputLayout.setPrefixText
 */
public fun TextInputLayout.setPrefixText(
    prefixText: AString,
): Unit = setPrefixText(
    prefixText(context),
)

/**
 * Sets a content description for the start icon
 * @see TextInputLayout.setStartIconContentDescription
 */
public fun TextInputLayout.setStartIconContentDescription(
    startIconContentDescription: AString,
): Unit = setStartIconContentDescription(
    startIconContentDescription(context),
)

/**
 * Sets the suffix text
 * @see TextInputLayout.setSuffixText
 */
public fun TextInputLayout.setSuffixText(
    suffixText: AString,
): Unit = setSuffixText(
    suffixText(context),
)
