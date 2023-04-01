//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import xyz.tynn.astring.AString

/**
 * Loads an `AString` value as a [String]
 *
 * @see LocalConfiguration
 * @see LocalContext
 */
@[Composable ReadOnlyComposable]
public fun AString.asString(): String = this(
    context(),
)?.toString().orEmpty()

/**
 * Loads an [aString] value as a [String]
 *
 * **Note** that this function is a synonym for `aString.asString()`
 *
 * @see LocalConfiguration
 * @see LocalContext
 */
@Suppress("NOTHING_TO_INLINE")
@[Composable ReadOnlyComposable]
public inline fun aString(
    aString: AString,
): String = aString.asString()

/**
 * Loads an `AString` value as an [AnnotatedString]
 *
 * **Note** that only some build-in spans are supported
 *
 * @see AnnotatedString.spanStyles
 * @see AnnotatedString.paragraphStyles
 * @see AnnotatedString.getTtsAnnotations
 * @see LocalConfiguration
 * @see LocalContext
 */
@ExperimentalTextApi
@[Composable ReadOnlyComposable]
public fun AString.asAnnotatedString(): AnnotatedString = this(
    context(),
)?.let {
    spannedString(it)
} ?: AnnotatedString("")

@[Composable ReadOnlyComposable]
private fun context(): Context {
    LocalConfiguration.current
    return LocalContext.current
}
