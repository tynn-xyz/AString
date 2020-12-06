//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.graphics.drawable.Drawable
import android.widget.TextView
import android.widget.TextView.BufferType
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

internal class AStringTextViewKtTest {

    val aString = mockk<AString>(relaxed = true)
    val view = mockk<TextView>(relaxed = true)

    @Test
    fun `append should delegate to view`() {
        view.append(aString)

        verify { view.append(aString(view.context)) }
    }

    @Test
    fun `append with range should delegate to view`() {
        view.append(aString, 1, 2)

        verify { view.append(aString(view.context), 1, 2) }
    }

    @Test
    fun `setError should delegate to view`() {
        view.setError(aString)

        verify { view.error = aString(view.context) }
    }

    @Test
    fun `setError with icon should delegate to view`() {
        val icon = mockk<Drawable>()

        view.setError(aString, icon)

        verify { view.setError(aString(view.context), icon) }
    }

    @Test
    fun `setHint should delegate to view`() {
        view.setHint(aString)

        verify { view.hint = aString(view.context) }
    }

    @Test
    fun `setText should delegate to view`() {
        view.setText(aString)

        verify { view.text = aString(view.context) }
    }

    @Test
    fun `setText with type should delegate to view`() {
        val type = mockk<BufferType>()

        view.setText(aString, type)

        verify { view.setText(aString(view.context), type) }
    }
}
