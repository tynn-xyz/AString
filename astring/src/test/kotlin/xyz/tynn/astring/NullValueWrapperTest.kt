//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import io.mockk.mockk
import kotlin.test.*

internal class NullValueWrapperTest {

    @Test
    fun `invoke should return null`() {
        assertNull(
            NullValueWrapper.I.invoke(null),
        )
    }

    @Test
    fun `equals should be true for same type`() {
        assertTrue {
            NullValueWrapper.I == mockk<NullValueWrapper>()
        }
    }

    @Test
    fun `equals should be false for non NullValueWrapper`() {
        assertFalse {
            NullValueWrapper.I.equals("foo")
        }
        assertFalse {
            NullValueWrapper.I == mockk<AString>()
        }
        assertFalse {
            NullValueWrapper.I.equals(mockk<CharSequenceWrapper>())
        }
        assertFalse {
            NullValueWrapper.I.equals(mockk<QuantityStringResourceDelegate>())
        }
        assertFalse {
            NullValueWrapper.I.equals(mockk<QuantityTextResourceDelegate>())
        }
        assertFalse {
            NullValueWrapper.I.equals(mockk<StringResourceDelegate>())
        }
        assertFalse {
            NullValueWrapper.I.equals(mockk<TextResourceDelegate>())
        }
    }

    @Test
    fun `hashCode should return 0`() {
        assertEquals(
            0,
            NullValueWrapper.I.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(NullValue(null))",
            NullValueWrapper.I.toString(),
        )
    }
}
