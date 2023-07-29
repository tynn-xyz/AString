//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import java.util.Objects.requireNonNull
import kotlin.reflect.KProperty

/**
 * Invokes the [aString] with [Context]
 */
@JvmName("invokeWithContext")
public fun Context.aString(
    aString: AString?,
): CharSequence? = aString?.invoke(
    requireNonNull(this, "context"),
)

/**
 * Invokes the [aString] with [Fragment.requireContext]
 *
 * @throws IllegalStateException if not currently associated with a context
 */
@JvmName("invokeWithFragment")
public fun Fragment.aString(
    aString: AString?,
): CharSequence? = aString?.invoke(
    requireContext(),
)

/**
 * Invokes the [aString] with [View.getContext]
 */
@JvmName("invokeWithView")
public fun View.aString(
    aString: AString?,
): CharSequence? = aString?.invoke(
    context,
)

/**
 * Delegates a `CharSequence?` property within a [Context] to the `AString`
 */
@JvmSynthetic
public operator fun AString?.getValue(
    thisRef: Context,
    property: KProperty<*>,
): CharSequence? = thisRef.aString(
    this,
)

/**
 * Delegates a `CharSequence?` property within a [Fragment] to the `AString`
 */
@JvmSynthetic
public operator fun AString?.getValue(
    thisRef: Fragment,
    property: KProperty<*>,
): CharSequence? = thisRef.aString(
    this,
)

/**
 * Delegates a `CharSequence?` property within a [View] to the `AString`
 */
@JvmSynthetic
public operator fun AString?.getValue(
    thisRef: View,
    property: KProperty<*>,
): CharSequence? = thisRef.aString(
    this,
)
