//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.material

import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import io.mockk.every
import io.mockk.mockk
import xyz.tynn.astring.AString

fun mockkGetMainLooper() = every {
    Looper.getMainLooper()
} returns mockk()

fun mockkSnackbarMake(
    snackbar: Snackbar,
) = every {
    Snackbar.make(any(), any<CharSequence>(), any())
} returns snackbar

fun mockkAStringInvoke(
    string: AString,
    value: CharSequence?,
) = every {
    string(any())
} returns value
