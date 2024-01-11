//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.*
import io.mockk.*
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringViewKtTest {

    private val aString = mockk<AString>(relaxed = true)
    private val view = mockk<View>(relaxed = true)

    @Test
    fun `setAccessibilityPaneTitle should delegate to ViewCompat`() {
        withViewCompat {
            every { setAccessibilityPaneTitle(any(), any()) } just runs

            view.setAccessibilityPaneTitle(aString)

            verify { setAccessibilityPaneTitle(view, aString(view.context)) }
        }
    }

    @Test
    fun `setAccessibilityPaneTitle should delegate null to ViewCompat`() {
        withViewCompat {
            every { setAccessibilityPaneTitle(any(), any()) } just runs

            view.setAccessibilityPaneTitle(null as AString?)

            verify { setAccessibilityPaneTitle(view, null) }
        }
    }

    @Test
    fun `setContentDescription should delegate to view`() {
        view.setContentDescription(aString)

        verify { view.contentDescription = aString(view.context) }
    }

    @Test
    fun `setContentDescription should delegate null to view`() {
        view.setContentDescription(null as AString?)

        verify { view.contentDescription = null }
    }

    @Test
    fun `setStateDescription should delegate to ViewCompat`() {
        withViewCompat {
            every { setStateDescription(any(), any()) } just runs

            view.setStateDescription(aString)

            verify { setStateDescription(view, aString(view.context)) }
        }
    }

    @Test
    fun `setStateDescription should delegate null to ViewCompat`() {
        withViewCompat {
            every { setStateDescription(any(), any()) } just runs

            view.setStateDescription(null as AString?)

            verify { setStateDescription(view, null) }
        }
    }

    @Test
    fun `setTooltipText should delegate to ViewCompat`() {
        withViewCompat {
            every { setTooltipText(any(), any()) } just runs

            view.setTooltipText(aString)

            verify { setTooltipText(view, aString(view.context)) }
        }
    }

    @Test
    fun `setTooltipText should delegate null to ViewCompat`() {
        withViewCompat {
            every { setTooltipText(any(), any()) } just runs

            view.setTooltipText(null as AString?)

            verify { setTooltipText(view, null) }
        }
    }

    private inline fun withViewCompat(
        block: () -> Unit
    ) = mockkStatic(ViewCompat::class) {
        block()
    }
}
