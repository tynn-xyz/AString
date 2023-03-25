//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToolbar")

package xyz.tynn.astring.appcompat

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets a description of the toolbar's logo
 * @see Toolbar.setLogoDescription
 */
@Suppress("UsePropertyAccessSyntax")
public fun Toolbar.setLogoDescription(
    description: AString?,
): Unit = setLogoDescription(
    aString(description),
)

/**
 * Sets a subtitle
 * @see Toolbar.setSubtitle
 */
@BindingAdapter("android:subtitle")
public fun Toolbar.setSubtitle(
    subtitle: AString?,
): Unit = setSubtitle(
    aString(subtitle),
)

/**
 * Sets a title
 * @see Toolbar.setTitle
 */
@BindingAdapter("android:title")
public fun Toolbar.setTitle(
    title: AString?,
): Unit = setTitle(
    aString(title),
)
