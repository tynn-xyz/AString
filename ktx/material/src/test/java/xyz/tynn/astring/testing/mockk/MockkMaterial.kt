//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.testing.mockk

import android.os.Looper.getMainLooper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import io.mockk.every
import io.mockk.mockk
import xyz.tynn.astring.AString

public fun mockkGetMainLooper(): Any = every {
    getMainLooper()
} returns mockk()

public fun mockkSnackbarMake(
    snackbar: Snackbar,
): Any = every {
    make(any(), any<CharSequence>(), any())
} returns snackbar

public fun mockkAStringInvoke(
    string: AString,
    value: CharSequence?,
): Any = every {
    string(any())
} returns value
