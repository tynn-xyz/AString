//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:[JvmName("AStringFactory") JvmMultifileClass Suppress("FunctionName")]

package xyz.tynn.astring

import android.content.res.Configuration
import java.util.Locale

/**
 * Wraps the [AString] to format the string with [formatArgs]
 * using the first locale of [Configuration.getLocales]
 *
 * **Note** that any [AString] within [formatArgs] will be invoked
 *
 * Returns [AString.Null] if this [AString] is `null` or [AString.Null]
 *
 * @see String.format
 */
@JvmName("formatWithAString")
public fun AString?.format(
    vararg formatArgs: Any?,
): AString = Format.wrap(
    this,
    null,
    formatArgs,
)

/**
 * Wraps the [AString] to format the string with [formatArgs]
 * using the provided [locale] if not null or the first locale of
 * [Configuration.getLocales]
 *
 * **Note** that any [AString] within [formatArgs] will be invoked
 *
 * Returns [AString.Null] if this [AString] is `null` or [AString.Null]
 *
 * @see String.format
 */
@JvmName("formatWithAString")
public fun AString?.format(
    locale: Locale?,
    vararg formatArgs: Any?,
): AString = Format.wrap(
    this,
    locale,
    formatArgs,
)

/**
 * Wraps the [AString] to always return a [String] on invocation
 *
 * Returns [AString.Null] if this [AString] is `null` or [AString.Null]
 *
 * @see CharSequence.toString
 */
public fun AString?.mapToString(): AString = Format.wrap(
    this,
    null,
    null,
)
