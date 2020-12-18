//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToolbar")

package xyz.tynn.astring.core

import android.os.Build.VERSION_CODES.LOLLIPOP
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import xyz.tynn.astring.AString

/**
 * Sets a description of the toolbar's logo
 * @see Toolbar.setLogoDescription
 */
@RequiresApi(LOLLIPOP)
@Suppress("UsePropertyAccessSyntax")
fun Toolbar.setLogoDescription(
    description: AString,
) = setLogoDescription(
    description(context),
)

/**
 * Sets a subtitle
 * @see Toolbar.setSubtitle
 */
@RequiresApi(LOLLIPOP)
fun Toolbar.setSubtitle(
    subtitle: AString,
) = setSubtitle(
    subtitle(context),
)

/**
 * Sets a title
 * @see Toolbar.setTitle
 */
@RequiresApi(LOLLIPOP)
fun Toolbar.setTitle(
    title: AString,
) = setTitle(
    title(context),
)
