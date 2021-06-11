//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat

import androidx.appcompat.widget.Toolbar
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringToolbarKtTest {

    val aString = mockk<AString>(relaxed = true)
    val view = mockk<Toolbar>(relaxed = true)

    @Test
    fun `setLogoDescription should delegate to view`() {
        view.setLogoDescription(aString)

        verify { view.logoDescription = aString(view.context) }
    }

    @Test
    fun `setSubtitle should delegate to view`() {
        view.setSubtitle(aString)

        verify { view.subtitle = aString(view.context) }
    }

    @Test
    fun `setTitle should delegate to view`() {
        view.setTitle(aString)

        verify { view.title = aString(view.context) }
    }
}
