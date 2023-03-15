//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringTextInputLayout")

package xyz.tynn.astring.material

import com.google.android.material.textfield.TextInputLayout
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets a content description for the end icon
 * @see TextInputLayout.setEndIconContentDescription
 */
public fun TextInputLayout.setEndIconContentDescription(
    endIconContentDescription: AString?,
): Unit = setEndIconContentDescription(
    aString(endIconContentDescription),
)

/**
 * Sets a content description for the error message
 * @see TextInputLayout.setErrorContentDescription
 */
public fun TextInputLayout.setErrorContentDescription(
    errorContentDescription: AString?,
): Unit = setErrorContentDescription(
    aString(errorContentDescription),
)

/**
 * Sets an error message
 * @see TextInputLayout.setError
 */
public fun TextInputLayout.setError(
    error: AString?,
): Unit = setError(
    aString(error),
)

/**
 * Sets a helper message
 * @see TextInputLayout.setHelperText
 */
public fun TextInputLayout.setHelperText(
    helperText: AString?,
): Unit = setHelperText(
    aString(helperText),
)

/**
 * Sets a hint
 * @see TextInputLayout.setHint
 */
public fun TextInputLayout.setHint(
    hint: AString?,
): Unit = setHint(
    aString(hint),
)

/**
 * Sets a placeholder text
 * @see TextInputLayout.setPlaceholderText
 */
public fun TextInputLayout.setPlaceholderText(
    placeholderText: AString?,
): Unit = setPlaceholderText(
    aString(placeholderText),
)

/**
 * Sets a prefix text
 * @see TextInputLayout.setPrefixText
 */
public fun TextInputLayout.setPrefixText(
    prefixText: AString?,
): Unit = setPrefixText(
    aString(prefixText),
)

/**
 * Sets a content description for the start icon
 * @see TextInputLayout.setStartIconContentDescription
 */
public fun TextInputLayout.setStartIconContentDescription(
    startIconContentDescription: AString?,
): Unit = setStartIconContentDescription(
    aString(startIconContentDescription),
)

/**
 * Sets the suffix text
 * @see TextInputLayout.setSuffixText
 */
public fun TextInputLayout.setSuffixText(
    suffixText: AString?,
): Unit = setSuffixText(
    aString(suffixText),
)
