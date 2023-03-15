//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat

import android.content.DialogInterface.BUTTON_NEUTRAL
import android.content.DialogInterface.OnClickListener
import android.os.Message
import androidx.appcompat.app.AlertDialog
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringAlertDialogKtTest {

    private val aString = mockk<AString>(relaxed = true)
    private val dialog = mockk<AlertDialog>(relaxed = true)

    @Test
    fun `setNegativeButton with listener should delegate to dialog`() {
        val listener: OnClickListener? = null

        dialog.setButton(BUTTON_NEUTRAL, aString, listener)

        verify { dialog.setButton(BUTTON_NEUTRAL, aString(dialog.context), listener) }
    }

    @Test
    fun `setNegativeButton with listener should delegate null to dialog`() {
        val listener = mockk<OnClickListener>()

        dialog.setButton(BUTTON_NEUTRAL, null as AString?, listener)

        verify { dialog.setButton(BUTTON_NEUTRAL, null, listener) }
    }

    @Test
    fun `setNegativeButton with message should delegate to dialog`() {
        val message: Message? = null

        dialog.setButton(BUTTON_NEUTRAL, aString, message)

        verify { dialog.setButton(BUTTON_NEUTRAL, aString(dialog.context), message) }
    }

    @Test
    fun `setNegativeButton with message should delegate null to dialog`() {
        val message = mockk<Message>()

        dialog.setButton(BUTTON_NEUTRAL, null as AString?, message)

        verify { dialog.setButton(BUTTON_NEUTRAL, null, message) }
    }

    @Test
    fun `setMessage should delegate to dialog`() {
        dialog.setMessage(aString)

        verify { dialog.setMessage(aString(dialog.context)) }
    }

    @Test
    fun `setMessage should delegate null to dialog`() {
        dialog.setMessage(null as AString?)

        verify { dialog.setMessage(null) }
    }

    @Test
    fun `setTitle should delegate to dialog`() {
        dialog.setTitle(aString)

        verify { dialog.setTitle(aString(dialog.context)) }
    }

    @Test
    fun `setTitle should delegate null to dialog`() {
        dialog.setTitle(null as AString?)

        verify { dialog.setTitle(null) }
    }
}
