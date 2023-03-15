//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToolbar")

package xyz.tynn.astring.core

import android.os.Build.VERSION_CODES.LOLLIPOP
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import xyz.tynn.astring.AString
import xyz.tynn.astring.aString

/**
 * Sets a description of the toolbar's logo
 * @see Toolbar.setLogoDescription
 */
@RequiresApi(LOLLIPOP)
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
@RequiresApi(LOLLIPOP)
public fun Toolbar.setSubtitle(
    subtitle: AString?,
): Unit = setSubtitle(
    aString(subtitle),
)

/**
 * Sets a title
 * @see Toolbar.setTitle
 */
@RequiresApi(LOLLIPOP)
public fun Toolbar.setTitle(
    title: AString?,
): Unit = setTitle(
    aString(title),
)
