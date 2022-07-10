//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import io.mockk.mockk
import kotlin.test.*

internal class NullValueProviderTest {

    @Test
    fun `invoke should return null`() {
        assertNull(
            ValueProvider.NullValueProvider.invoke(mockk()),
        )
    }

    @Test
    fun `equals should be true for same type`() {
        assertTrue {
            ValueProvider.NullValueProvider == ValueProvider.NullValueProvider
        }
    }

    @Test
    @Suppress("EqualsBetweenInconvertibleTypes")
    fun `equals should be false for non NullValueProvider`() {
        assertFalse {
            ValueProvider.NullValueProvider.equals("foo")
        }
        assertFalse {
            ValueProvider.NullValueProvider == mockk<AString>()
        }
        assertFalse {
            ValueProvider.NullValueProvider.equals(mockk<CharSequenceWrapper>())
        }
        assertFalse {
            ValueProvider.NullValueProvider.equals(mockk<ResourceDelegate>())
        }
        assertFalse {
            ValueProvider.NullValueProvider.equals(mockk<ValueProvider>())
        }
    }

    @Test
    fun `hashCode should not return 0`() {
        assertNotEquals(
            0,
            ValueProvider.NullValueProvider.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(CharSequence(null))",
            ValueProvider.NullValueProvider.toString(),
        )
    }
}
