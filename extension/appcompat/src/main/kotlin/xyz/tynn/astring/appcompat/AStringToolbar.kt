//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToolbar")

package xyz.tynn.astring.appcompat

import androidx.appcompat.widget.Toolbar
import xyz.tynn.astring.AString

/**
 * Sets a description of the toolbar's logo
 * @see Toolbar.setLogoDescription
 */
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
fun Toolbar.setSubtitle(
    subtitle: AString,
) = setSubtitle(
    subtitle(context),
)

/**
 * Sets a title
 * @see Toolbar.setTitle
 */
fun Toolbar.setTitle(
    title: AString,
) = setTitle(
    title(context),
)
