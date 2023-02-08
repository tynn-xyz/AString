//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core.test

import android.widget.Toast
import android.widget.Toast.makeText
import io.mockk.every

internal fun mockkToastMakeText(
    toast: Toast,
) = every {
    makeText(any(), any<CharSequence>(), any())
} returns toast
