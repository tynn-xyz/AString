//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToolbar")

package xyz.tynn.astring.appcompat

import androidx.appcompat.widget.Toolbar
import xyz.tynn.astring.AString

fun Toolbar.setLogoDescription(
    description: AString,
) = setLogoDescription(
    description(context),
)

fun Toolbar.setSubtitle(
    subtitle: AString,
) = setSubtitle(
    subtitle(context),
)

fun Toolbar.setTitle(
    title: AString,
) = setTitle(
    title(context),
)
