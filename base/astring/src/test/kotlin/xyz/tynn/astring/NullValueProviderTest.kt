//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import io.mockk.mockk
import kotlin.test.*

internal class NullValueProviderTest {

    @Test
    fun `invoke should return null`() {
        assertNull(
            CharSequenceWrapper(null).invoke(mockk()),
        )
    }

    @Test
    fun `equals should be true for same type`() {
        assertTrue {
            CharSequenceWrapper(null) == CharSequenceWrapper(null)
        }
    }

    @Test
    @Suppress("EqualsBetweenInconvertibleTypes")
    fun `equals should be false for non NullValueProvider`() {
        assertFalse {
            CharSequenceWrapper(null).equals("foo")
        }
        assertFalse {
            CharSequenceWrapper(null) == mockk<AString>()
        }
        assertFalse {
            CharSequenceWrapper(null) == CharSequenceWrapper("")
        }
        assertFalse {
            CharSequenceWrapper(null).equals(mockk<ResourceDelegate>())
        }
        assertFalse {
            CharSequenceWrapper(null).equals(mockk<ContextValueProvider>())
        }
    }

    @Test
    fun `hashCode should return 0`() {
        assertEquals(
            0,
            CharSequenceWrapper(null).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(CharSequence(null))",
            CharSequenceWrapper(null).toString(),
        )
    }
}
