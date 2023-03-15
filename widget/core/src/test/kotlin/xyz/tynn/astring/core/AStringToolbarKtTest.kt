//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core

import android.widget.Toolbar
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringToolbarKtTest {

    private val aString = mockk<AString>(relaxed = true)
    private val view = mockk<Toolbar>(relaxed = true)

    @Test
    fun `setLogoDescription should delegate to view`() {
        view.setLogoDescription(aString)

        verify { view.logoDescription = aString(view.context) }
    }

    @Test
    fun `setLogoDescription should delegate null to view`() {
        view.setLogoDescription(null as AString?)

        verify { view.logoDescription = null }
    }

    @Test
    fun `setSubtitle should delegate to view`() {
        view.setSubtitle(aString)

        verify { view.subtitle = aString(view.context) }
    }

    @Test
    fun `setSubtitle should delegate null to view`() {
        view.setSubtitle(null as AString?)

        verify { view.subtitle = null }
    }

    @Test
    fun `setTitle should delegate to view`() {
        view.setTitle(aString)

        verify { view.title = aString(view.context) }
    }

    @Test
    fun `setTitle should delegate null to view`() {
        view.setTitle(null as AString?)

        verify { view.title = null }
    }
}
