//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import io.mockk.mockk
import kotlin.test.*

internal class NullValueWrapperTest {

    @Test
    fun `I should be non null`() {
        assertNotNull(
            NullValueWrapper.I,
        )
    }

    @Test
    fun `invoke should return null`() {
        assertNull(
            NullValueWrapper().invoke(null),
        )
    }

    @Test
    fun `equals should be true for same type`() {
        assertTrue {
            NullValueWrapper() == NullValueWrapper()
        }
    }

    @Test
    fun `equals should be false for non CharSequenceWrapper`() {
        assertFalse {
            NullValueWrapper().equals("foo")
        }
        assertFalse {
            NullValueWrapper() == mockk<AString>()
        }
        assertFalse {
            NullValueWrapper().equals(mockk<CharSequenceWrapper>())
        }
        assertFalse {
            NullValueWrapper().equals(mockk<QuantityStringResourceDelegate>())
        }
        assertFalse {
            NullValueWrapper().equals(mockk<QuantityTextResourceDelegate>())
        }
        assertFalse {
            NullValueWrapper().equals(mockk<StringResourceDelegate>())
        }
        assertFalse {
            NullValueWrapper().equals(mockk<TextResourceDelegate>())
        }
    }

    @Test
    fun `hashCode should return 0`() {
        assertEquals(
            0,
            NullValueWrapper().hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(NullValue(null))",
            NullValueWrapper().toString(),
        )
    }
}
