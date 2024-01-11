//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core

import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_NEUTRAL
import android.content.DialogInterface.BUTTON_POSITIVE
import androidx.annotation.IntDef
import kotlin.annotation.AnnotationRetention.SOURCE

@Retention(SOURCE)
@IntDef(BUTTON_POSITIVE, BUTTON_NEGATIVE, BUTTON_NEUTRAL)
public annotation class DialogInterfaceButton
