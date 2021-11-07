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
): CharSequence? = invoke(fragment.requireContext())

/**
 * Invokes the `AString` with [View.getContext]
 */
public operator fun AString.invoke(
    view: View,
): CharSequence? = invoke(view.context)

/**
 * Delegates a `CharSequence?` property within a [Context] to the `AString`
 */
@JvmSynthetic
public operator fun AString.getValue(
    thisRef: Context,
    property: KProperty<*>,
): CharSequence? = invoke(thisRef)

/**
 * Delegates a `CharSequence?` property within a [Fragment] to the `AString`
 */
@JvmSynthetic
public operator fun AString.getValue(
    thisRef: Fragment,
    property: KProperty<*>,
): CharSequence? = invoke(thisRef.requireContext())

/**
 * Delegates a `CharSequence?` property within a [View] to the `AString`
 */
@JvmSynthetic
public operator fun AString.getValue(
    thisRef: View,
    property: KProperty<*>,
): CharSequence? = invoke(thisRef.context)
