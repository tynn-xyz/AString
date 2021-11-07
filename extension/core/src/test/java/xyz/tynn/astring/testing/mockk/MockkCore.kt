//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.testing.mockk

import android.widget.Toast
import android.widget.Toast.makeText
import io.mockk.every

public fun mockkToastMakeText(
    toast: Toast,
): Any = every {
    makeText(any(), any<CharSequence>(), any())
} returns toast
