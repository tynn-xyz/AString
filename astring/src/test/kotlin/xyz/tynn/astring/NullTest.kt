//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

internal class NullTest {

    @Test
    fun `invoke should return null`() {
        assertNull(
            Wrapper.NULL.invoke(mockk()),
        )
    }

    @Test
    fun `identity should be true for same type`() {
        assertTrue {
            Wrapper.NULL === Wrapper.wrap(null)
        }
    }

    @Test
    fun `equals should be true for same type`() {
        assertTrue {
            Wrapper.NULL == Wrapper.NULL
        }
    }

    @Test
    fun `equals should be false for non NullValueProvider`() {
        assertFalse {
            Wrapper.NULL.equals("foo")
        }
        assertFalse {
            Wrapper.NULL == mockk<AString>()
        }
        assertFalse {
            Wrapper.NULL == Wrapper.wrap("")
        }
        assertFalse {
            Wrapper.NULL.equals(mockk<Resource>())
        }
        assertFalse {
            Wrapper.NULL.equals(mockk<Provider>())
        }
    }

    @Test
    fun `hashCode should return 0`() {
        assertEquals(
            0,
            Wrapper.NULL.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(CharSequence(null))",
            Wrapper.NULL.toString(),
        )
    }
}
