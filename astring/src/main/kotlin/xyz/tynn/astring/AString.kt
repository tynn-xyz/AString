//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

/**
 * Invokes the `AString` with [Fragment.requireContext]
 */
public operator fun AString.invoke(
    fragment: Fragment,
): CharSequence? = invoke(
    fragment.requireContext(),
)

/**
 * Invokes the `AString` with [View.getContext]
 */
public operator fun AString.invoke(
    view: View,
): CharSequence? = invoke(
    view.context,
)

/**
 * Delegates a `CharSequence?` property within a [Context] to the `AString`
 */
@JvmSynthetic
public operator fun AString.getValue(
    thisRef: Context,
    property: KProperty<*>,
): CharSequence? = invoke(
    thisRef,
)

/**
 * Delegates a `CharSequence?` property within a [Fragment] to the `AString`
 */
@JvmSynthetic
public operator fun AString.getValue(
    thisRef: Fragment,
    property: KProperty<*>,
): CharSequence? = invoke(
    thisRef.requireContext(),
)

/**
 * Delegates a `CharSequence?` property within a [View] to the `AString`
 */
@JvmSynthetic
public operator fun AString.getValue(
    thisRef: View,
    property: KProperty<*>,
): CharSequence? = invoke(
    thisRef.context,
)

/**
 * Invokes the [aString] with [Context]
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE")]
public inline fun Context.aString(
    aString: AString,
): CharSequence? = aString.invoke(
    this,
)

/**
 * Invokes the [aString] with [Fragment.requireContext]
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE")]
public inline fun Fragment.aString(
    aString: AString,
): CharSequence? = aString.invoke(
    requireContext(),
)

/**
 * Invokes the [aString] with [View.getContext]
 */
@[JvmSynthetic Suppress("NOTHING_TO_INLINE")]
public inline fun View.aString(
    aString: AString,
): CharSequence? = aString.invoke(
    context,
)
