//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core

import android.widget.TextSwitcher
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringTextSwitcherKtTest {

    val aString = mockk<AString>(relaxed = true)
    val view = mockk<TextSwitcher>(relaxed = true)

    @Test
    fun `setCurrentText should delegate to view`() {
        view.setCurrentText(aString)

        verify { view.setCurrentText(aString(view.context)) }
    }

    @Test
    fun `setText should delegate to view`() {
        view.setText(aString)

        verify { view.setText(aString(view.context)) }
    }
}
