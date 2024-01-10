//  Copyright 2024 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.integration.compose

import app.cash.paparazzi.DeviceConfig.Companion.NEXUS_4
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

internal class ComposeWidgetTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = NEXUS_4,
        theme = "android:Theme.Material.Light.NoActionBar"
    )

    @Test
    fun screenshotComposeWidget() {
        paparazzi.snapshot {
            ComposeWidget()
        }
    }
}
