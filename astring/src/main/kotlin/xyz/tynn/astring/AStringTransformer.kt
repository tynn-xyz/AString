//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:[JvmName("AStringFactory") JvmMultifileClass Suppress("FunctionName")]

package xyz.tynn.astring

import android.content.res.Configuration
import xyz.tynn.astring.Predicate.NonBlank
import xyz.tynn.astring.Predicate.NonEmpty
import xyz.tynn.astring.Predicate.NonNull
import xyz.tynn.astring.Transformer.Trim
import java.util.Locale

/**
 * Maps the [AString] `CharSequence` to the result of [transformer]
 */
@[InefficientAStringApi JvmName("mapAString")]
public fun AString?.map(
    transformer: AString.Transformer,
): AString = Delegate.wrap(
    transformer,
    this,
)

/**
 * Formats the [AString] `CharSequence` with [formatArgs]
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
 * Formats the [AString] `CharSequence` with [formatArgs]
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
 * Maps a null `CharSequence` to [defaultValue]
 */
@[JvmName("mapNullToDefault") OptIn(InefficientAStringApi::class)]
public fun AString?.ifNull(
    defaultValue: CharSequence,
): AString = Delegate.wrap(
    NonNull,
    this,
    AString(defaultValue),
)

/**
 * Maps a null `CharSequence` to [defaultValue]
 */
@[JvmName("mapNullToDefault") OptIn(InefficientAStringApi::class)]
public fun AString?.ifNull(
    defaultValue: AString,
): AString = Delegate.wrap(
    NonNull,
    this,
    defaultValue,
)

/**
 * Maps a blank [AString] `CharSequence` to null
 *
 * Returns [AString.Null] if this [AString] is `null` or [AString.Null]
 */
@[JvmName("mapBlankToNull") OptIn(InefficientAStringApi::class)]
public fun AString?.nullIfBlank(): AString = Delegate.wrap(
    NonBlank,
    this,
)

/**
 * Maps an empty [AString] `CharSequence` to null
 *
 * Returns [AString.Null] if this [AString] is `null` or [AString.Null]
 */
@[JvmName("mapEmptyToNull") OptIn(InefficientAStringApi::class)]
public fun AString?.nullIfEmpty(): AString = Delegate.wrap(
    NonEmpty,
    this,
)

/**
 * Maps the [AString] to always return a [String] on invocation
 *
 * Returns [AString.Null] if this [AString] is `null` or [AString.Null]
 */
@JvmName("mapCharSequenceToString")
public fun AString?.mapToString(): AString = Format.wrap(
    this,
    null,
    null,
)

/**
 * Trims the [AString] `CharSequence`
 *
 * Returns [AString.Null] if this [AString] is `null` or [AString.Null]
 */
@[JvmName("trimCharSequence") OptIn(InefficientAStringApi::class)]
public fun AString?.trim(): AString = Delegate.wrap(
    Trim,
    this,
)
