//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core

import android.widget.Toast
import io.mockk.every

fun mockkToastMakeText(
    toast: Toast,
) = every {
    Toast.makeText(any(), any<CharSequence>(), any())
} returns toast
