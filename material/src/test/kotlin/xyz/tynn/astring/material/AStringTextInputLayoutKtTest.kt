//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.material

import com.google.android.material.textfield.TextInputLayout
import io.mockk.mockk
import io.mockk.verify
import xyz.tynn.astring.AString
import kotlin.test.Test

internal class AStringTextInputLayoutKtTest {

    val aString = mockk<AString>(relaxed = true)
    val view = mockk<TextInputLayout>(relaxed = true)

    @Test
    fun `setEndIconContentDescription should delegate to view`() {
        view.setEndIconContentDescription(aString)

        verify { view.endIconContentDescription = aString(view.context) }
    }

    @Test
    fun `setErrorContentDescription should delegate to view`() {
        view.setErrorContentDescription(aString)

        verify { view.errorContentDescription = aString(view.context) }
    }

    @Test
    fun `setError should delegate to view`() {
        view.setError(aString)

        verify { view.error = aString(view.context) }
    }

    @Test
    fun `setHelperText should delegate to view`() {
        view.setHelperText(aString)

        verify { view.helperText = aString(view.context) }
    }

    @Test
    fun `setHint should delegate to view`() {
        view.setHint(aString)

        verify { view.hint = aString(view.context) }
    }

    @Test
    fun `setPlaceholderText should delegate to view`() {
        view.setPlaceholderText(aString)

        verify { view.placeholderText = aString(view.context) }
    }

    @Test
    fun `setPrefixText should delegate to view`() {
        view.setPrefixText(aString)

        verify { view.prefixText = aString(view.context) }
    }

    @Test
    fun `setStartIconContentDescription should delegate to view`() {
        view.setStartIconContentDescription(aString)

        verify { view.startIconContentDescription = aString(view.context) }
    }

    @Test
    fun `setSuffixText should delegate to view`() {
        view.setSuffixText(aString)

        verify { view.suffixText = aString(view.context) }
    }
}
