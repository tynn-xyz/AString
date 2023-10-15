//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core

import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.annotation.IntDef
import kotlin.annotation.AnnotationRetention.SOURCE

@Retention(SOURCE)
@IntDef(LENGTH_LONG, LENGTH_SHORT)
public annotation class ToastDuration
