//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.material.test

import android.os.Looper.getMainLooper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import io.mockk.every
import io.mockk.mockk
import xyz.tynn.astring.AString

internal fun mockkGetMainLooper() = every {
    getMainLooper()
} returns mockk()

internal fun mockkSnackbarMake(
    snackbar: Snackbar,
) = every {
    make(any(), any<CharSequence>(), any())
} returns snackbar

internal fun mockkAStringInvoke(
    string: AString,
    value: CharSequence?,
) = every {
    string(any())
} returns value
