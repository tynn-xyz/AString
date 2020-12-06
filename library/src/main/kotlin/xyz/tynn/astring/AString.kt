//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

operator fun AString.invoke(
    fragment: Fragment,
) = invoke(fragment.requireContext())

operator fun AString.invoke(
    view: View,
) = invoke(view.context)

@JvmSynthetic
operator fun AString.getValue(
    thisRef: Context,
    property: KProperty<*>,
) = invoke(thisRef)

@JvmSynthetic
operator fun AString.getValue(
    thisRef: Fragment,
    property: KProperty<*>,
) = invoke(thisRef.requireContext())

@JvmSynthetic
operator fun AString.getValue(
    thisRef: View,
    property: KProperty<*>,
) = invoke(thisRef.context)
