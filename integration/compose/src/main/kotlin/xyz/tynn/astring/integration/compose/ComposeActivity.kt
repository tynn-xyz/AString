//  Copyright 2024 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.integration.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

class ComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeWidget() }
    }
}
