//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core

import android.app.Activity
import android.content.DialogInterface.OnClickListener
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringActivityKtTest {

    val aString = mockk<AString>(relaxed = true)
    val activity = mockk<Activity>(relaxed = true)
    val listener = mockk<OnClickListener>()

    @Test
    fun `setTitle should delegate to activity`() {
        activity.setTitle(aString)

        verify { activity.title = aString(activity) }
    }
}
