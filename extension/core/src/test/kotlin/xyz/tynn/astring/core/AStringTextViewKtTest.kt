//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core

import android.graphics.drawable.Drawable
import android.widget.TextView
import android.widget.TextView.BufferType
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringTextViewKtTest {

    private val aString = mockk<AString>(relaxed = true)
    private val view = mockk<TextView>(relaxed = true)

    @Test
    fun `append should delegate to view`() {
        view.append(aString)

        verify { view.append(aString(view.context)) }
    }

    @Test
    fun `append should delegate null to view`() {
        view.append(null as AString?)

        verify { view.append(null) }
    }

    @Test
    fun `append with range should delegate to view`() {
        view.append(aString, 1, 2)

        verify { view.append(aString(view.context), 1, 2) }
    }

    @Test
    fun `append with range should delegate null to view`() {
        view.append(null as AString?, 1, 2)

        verify { view.append(null, 1, 2) }
    }

    @Test
    fun `setError should delegate to view`() {
        view.setError(aString)

        verify { view.error = aString(view.context) }
    }

    @Test
    fun `setError should delegate null to view`() {
        view.setError(null as AString?)

        verify { view.error = null }
    }

    @Test
    fun `setError with icon should delegate to view`() {
        val icon = mockk<Drawable>()

        view.setError(aString, icon)

        verify { view.setError(aString(view.context), icon) }
    }

    @Test
    fun `setError with icon should delegate null to view`() {
        val icon = mockk<Drawable>()

        view.setError(null as AString?, icon)

        verify { view.setError(null, icon) }
    }

    @Test
    fun `setHint should delegate to view`() {
        view.setHint(aString)

        verify { view.hint = aString(view.context) }
    }

    @Test
    fun `setHint should delegate null to view`() {
        view.setHint(null as AString?)

        verify { view.hint = null }
    }

    @Test
    fun `setText should delegate to view`() {
        view.setText(aString)

        verify { view.text = aString(view.context) }
    }

    @Test
    fun `setText should delegate null to view`() {
        view.setText(null as AString?)

        verify { view.text = null }
    }

    @Test
    fun `setText with type should delegate to view`() {
        val type = mockk<BufferType>()

        view.setText(aString, type)

        verify { view.setText(aString(view.context), type) }
    }

    @Test
    fun `setText with type should delegate null to view`() {
        val type = mockk<BufferType>()

        view.setText(null as AString?, type)

        verify { view.setText(null, type) }
    }
}
