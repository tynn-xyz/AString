//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:JvmName("AStringToolbar")

package xyz.tynn.astring.core

import android.os.Build.VERSION_CODES.LOLLIPOP
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import xyz.tynn.astring.AString

@RequiresApi(LOLLIPOP)
fun Toolbar.setLogoDescription(
    description: AString,
) = setLogoDescription(
    description(context),
)

@RequiresApi(LOLLIPOP)
fun Toolbar.setSubtitle(
    subtitle: AString,
) = setSubtitle(
    subtitle(context),
)

@RequiresApi(LOLLIPOP)
fun Toolbar.setTitle(
    title: AString,
) = setTitle(
    title(context),
)
