//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat

import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AlertDialog
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import kotlin.test.Test
import kotlin.test.assertSame

internal class AStringAlertDialogBuilderKtTest {

    private val aString = mockk<AString>(relaxed = true)
    private val builder = mockk<AlertDialog.Builder>(relaxed = true)
    private val listener = mockk<OnClickListener>()

    @Test
    fun `setNegativeButton should delegate to builder`() {
        assertSame(
            builder,
            builder.setNegativeButton(aString, null),
        )

        verify { builder.setNegativeButton(aString(builder.context), null) }
    }

    @Test
    fun `setNegativeButton should delegate null to builder`() {
        assertSame(
            builder,
            builder.setNegativeButton(null as AString?, listener),
        )

        verify { builder.setNegativeButton(null, listener) }
    }

    @Test
    fun `setNeutralButton should delegate to builder`() {
        assertSame(
            builder,
            builder.setNeutralButton(aString, null),
        )

        verify { builder.setNeutralButton(aString(builder.context), null) }
    }

    @Test
    fun `setNeutralButton should delegate null to builder`() {
        assertSame(
            builder,
            builder.setNeutralButton(null as AString?, listener),
        )

        verify { builder.setNeutralButton(null, listener) }
    }

    @Test
    fun `setPositiveButton should delegate to builder`() {
        assertSame(
            builder,
            builder.setPositiveButton(aString, null),
        )

        verify { builder.setPositiveButton(aString(builder.context), null) }
    }

    @Test
    fun `setPositiveButton should delegate null to builder`() {
        assertSame(
            builder,
            builder.setPositiveButton(null as AString?, listener),
        )

        verify { builder.setPositiveButton(null as AString?, listener) }
    }

    @Test
    fun `setMessage should delegate to builder`() {
        assertSame(
            builder,
            builder.setMessage(aString),
        )

        verify { builder.setMessage(aString(builder.context)) }
    }

    @Test
    fun `setMessage should delegate null to builder`() {
        assertSame(
            builder,
            builder.setMessage(null as AString?),
        )

        verify { builder.setMessage(null) }
    }

    @Test
    fun `setTitle should delegate to builder`() {
        assertSame(
            builder,
            builder.setTitle(aString),
        )

        verify { builder.setTitle(aString(builder.context)) }
    }

    @Test
    fun `setTitle should delegate null to builder`() {
        assertSame(
            builder,
            builder.setTitle(null as AString?),
        )

        verify { builder.setTitle(null) }
    }
}
