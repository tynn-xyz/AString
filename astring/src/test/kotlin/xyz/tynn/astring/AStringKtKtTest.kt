//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlin.test.Test

internal class AStringKtKtTest {

    private val aString = mockk<AString>(relaxed = true)
    private val aContext = mockk<Context>()

    private val Context.string by aString

    @Test
    fun `aString with context delegates to this`() {
        aContext.aString(aString)

        verify { aString(aContext) }
    }

    @Test
    fun `aString with fragment delegates to context`() {
        mockk<Fragment> {
            every { requireContext() } returns aContext
        }.aString(aString)

        verify { aString(aContext) }
    }

    @Test
    fun `aString with view delegates to context`() {
        mockk<View> {
            every { context } returns aContext
        }.aString(aString)

        verify { aString(aContext) }
    }

    @Test
    fun `getValue with context delegates to receiver`() {
        aContext.string

        verify { aString(aContext) }
    }

    @Test
    fun `getValue with context delegates to this`() {
        val aContext = object : Activity() {
            val string by aString
        }.apply { string }

        verify { aString(aContext) }
    }

    @Test
    fun `getValue with fragment delegates to context`() {
        spyk(object : Fragment() {
            val string by aString
        }) {
            every { context } returns aContext
        }.string

        verify { aString(aContext) }
    }

    @Test
    fun `getValue with view delegates to context`() {
        spyk(object : View(aContext) {
            val string by aString
        }) {
            every { context } returns aContext
        }.string

        verify { aString(aContext) }
    }
}
