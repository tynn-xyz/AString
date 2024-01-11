//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core

import android.app.Activity
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringActivityKtTest {

    private val aString = mockk<AString>(relaxed = true)
    private val activity = mockk<Activity>(relaxed = true)

    @Test
    fun `setTitle should delegate to activity`() {
        activity.setTitle(aString)

        verify { activity.title = aString(activity) }
    }

    @Test
    fun `setTitle should delegate null to activity`() {
        activity.setTitle(null as AString?)

        verify { activity.title = null }
    }
}
