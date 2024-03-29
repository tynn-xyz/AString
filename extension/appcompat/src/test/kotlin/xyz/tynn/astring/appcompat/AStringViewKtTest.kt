//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat

import android.view.View
import androidx.appcompat.widget.TooltipCompat
import androidx.appcompat.widget.TooltipCompat.setTooltipText
import io.mockk.*
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringViewKtTest {

    private val aString = mockk<AString>(relaxed = true)
    private val view = mockk<View>(relaxed = true)

    @Test
    fun `setTooltipText should delegate to ViewCompat`() {
        withTooltipCompat {
            every { setTooltipText(any(), any()) } just runs

            view.setTooltipText(aString)

            verify { setTooltipText(view, aString(view.context)) }
        }
    }

    @Test
    fun `setTooltipText should delegate null to ViewCompat`() {
        withTooltipCompat {
            every { setTooltipText(any(), any()) } just runs

            view.setTooltipText(null as AString?)

            verify { setTooltipText(view, null) }
        }
    }

    private inline fun withTooltipCompat(
        block: () -> Unit
    ) = mockkStatic(TooltipCompat::class) {
        block()
    }
}
