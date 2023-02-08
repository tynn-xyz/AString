//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat

import androidx.appcompat.widget.SwitchCompat
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringSwitchCompatKtTest {

    val aString = mockk<AString>(relaxed = true)
    val view = mockk<SwitchCompat>(relaxed = true)

    @Test
    fun `setTextOff should delegate to view`() {
        view.setTextOff(aString)

        verify { view.textOff = aString(view.context) }
    }

    @Test
    fun `setTextOn should delegate to view`() {
        view.setTextOn(aString)

        verify { view.textOn = aString(view.context) }
    }
}
