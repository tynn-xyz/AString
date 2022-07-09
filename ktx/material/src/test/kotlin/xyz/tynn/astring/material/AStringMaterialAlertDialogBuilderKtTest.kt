//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.material

import android.content.DialogInterface.OnClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import xyz.tynn.astring.appcompat.*
import kotlin.test.Test
import kotlin.test.assertEquals

internal class AStringMaterialAlertDialogBuilderKtTest {

    val aString = mockk<AString>(relaxed = true)
    val builder = mockk<MaterialAlertDialogBuilder>(relaxed = true)
    val listener = mockk<OnClickListener>()

    @Test
    fun `setNegativeButton should delegate to builder`() {
        assertEquals(
            builder,
            builder.setNegativeButton(aString, listener),
        )

        verify { builder.setNegativeButton(aString(builder.context), listener) }
    }

    @Test
    fun `setNeutralButton should delegate to builder`() {
        assertEquals(
            builder,
            builder.setNeutralButton(aString, listener),
        )

        verify { builder.setNeutralButton(aString(builder.context), listener) }
    }

    @Test
    fun `setPositiveButton should delegate to builder`() {
        assertEquals(
            builder,
            builder.setPositiveButton(aString, listener),
        )

        verify { builder.setPositiveButton(aString(builder.context), listener) }
    }

    @Test
    fun `setMessage should delegate to builder`() {
        assertEquals(
            builder,
            builder.setMessage(aString),
        )

        verify { builder.setMessage(aString(builder.context)) }
    }

    @Test
    fun `setTitle should delegate to builder`() {
        assertEquals(
            builder,
            builder.setTitle(aString),
        )

        verify { builder.setTitle(aString(builder.context)) }
    }
}
