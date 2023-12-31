//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:[JvmName("AStringFactory") JvmMultifileClass Suppress("FunctionName")]

package xyz.tynn.astring

import xyz.tynn.astring.Predicate.AnyValue
import xyz.tynn.astring.Predicate.NonBlank
import xyz.tynn.astring.Predicate.NonEmpty
import xyz.tynn.astring.Predicate.NonNull

/**
 * Reduces an [Array] of [AString] to a single [CharSequence]
 */
@InefficientAStringApi
public fun reduce(
    vararg aStrings: AString?,
    reducer: AString.Reducer,
): AString = Delegate.wrap(
    reducer,
    *aStrings,
)

/**
 * Reduces an [Iterable] of [AString] to a single [CharSequence]
 */
@InefficientAStringApi
public fun Iterable<AString?>.reduce(
    reducer: AString.Reducer,
): AString = Delegate.wrap(
    reducer,
    this,
)

/**
 * Reduces all [aStrings] by returning the first non-blank [CharSequence] value
 */
@OptIn(InefficientAStringApi::class)
public fun firstNonBlank(
    vararg aStrings: AString?,
): AString = Delegate.wrap(
    NonBlank,
    *aStrings,
)

/**
 * Reduces all items by returning the first non-blank [CharSequence] value
 */
@OptIn(InefficientAStringApi::class)
public fun Iterable<AString?>.firstNonBlank(): AString = Delegate.wrap(
    NonBlank,
    this,
)

/**
 * Reduces all [aStrings] by returning the first non-empty [CharSequence] value
 */
@OptIn(InefficientAStringApi::class)
public fun firstNonEmpty(
    vararg aStrings: AString?,
): AString = Delegate.wrap(
    NonEmpty,
    *aStrings,
)

/**
 * Reduces all items by returning the first non-empty [CharSequence] value
 */
@OptIn(InefficientAStringApi::class)
public fun Iterable<AString?>.firstNonEmpty(): AString = Delegate.wrap(
    NonEmpty,
    this,
)

/**
 * Reduces all [aStrings] by returning the first non-null [CharSequence] value
 */
@OptIn(InefficientAStringApi::class)
public fun firstNonNull(
    vararg aStrings: AString?,
): AString = Delegate.wrap(
    NonNull,
    *aStrings,
)

/**
 * Reduces all items by returning the first non-null [CharSequence] value
 */
@OptIn(InefficientAStringApi::class)
public fun Iterable<AString?>.firstNonNull(): AString = Delegate.wrap(
    NonNull,
    this,
)

/**
 * Reduces all [aStrings] by joining all nullable [CharSequence] values
 */
@OptIn(InefficientAStringApi::class)
public fun join(
    vararg aStrings: AString?,
    separator: String,
): AString = Delegate.wrap(
    Joiner(
        separator,
        AnyValue,
    ),
    *aStrings,
)

/**
 * Reduces all items by joining all nullable [CharSequence] values
 */
@OptIn(InefficientAStringApi::class)
public fun Iterable<AString?>.join(
    separator: String,
): AString = Delegate.wrap(
    Joiner(
        separator,
        AnyValue,
    ),
    this,
)

/**
 * Reduces all [aStrings] by joining all non-null [CharSequence] values
 */
@OptIn(InefficientAStringApi::class)
public fun joinNonBlank(
    vararg aStrings: AString?,
    separator: String,
): AString = Delegate.wrap(
    Joiner(
        separator,
        NonBlank,
    ),
    *aStrings,
)

/**
 * Reduces all items by joining all non-null [CharSequence] values
 */
@OptIn(InefficientAStringApi::class)
public fun Iterable<AString?>.joinNonBlank(
    separator: String,
): AString = Delegate.wrap(
    Joiner(
        separator,
        NonBlank,
    ),
    this,
)

/**
 * Reduces all [aStrings] by joining all non-null [CharSequence] values
 */
@OptIn(InefficientAStringApi::class)
public fun joinNonEmpty(
    vararg aStrings: AString?,
    separator: String,
): AString = Delegate.wrap(
    Joiner(
        separator,
        NonEmpty,
    ),
    *aStrings,
)

/**
 * Reduces all items by joining all non-null [CharSequence] values
 */
@OptIn(InefficientAStringApi::class)
public fun Iterable<AString?>.joinNonEmpty(
    separator: String,
): AString = Delegate.wrap(
    Joiner(
        separator,
        NonEmpty,
    ),
    this,
)

/**
 * Reduces all [aStrings] by joining all non-null [CharSequence] values
 */
@OptIn(InefficientAStringApi::class)
public fun joinNonNull(
    vararg aStrings: AString?,
    separator: String,
): AString = Delegate.wrap(
    Joiner(
        separator,
        NonNull,
    ),
    *aStrings,
)

/**
 * Reduces all items by joining all non-null [CharSequence] values
 */
@OptIn(InefficientAStringApi::class)
public fun Iterable<AString?>.joinNonNull(
    separator: String,
): AString = Delegate.wrap(
    Joiner(
        separator,
        NonNull,
    ),
    this,
)
