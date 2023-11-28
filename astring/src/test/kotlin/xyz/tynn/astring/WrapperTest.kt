//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class WrapperTest {

    @Test
    fun `invoke should return the string`() {
        assertEquals(
            "foo",
            Wrapper.wrap("foo").invoke(null),
        )
    }

    @Test
    fun `equals should be true for matching strings`() {
        assertTrue {
            Wrapper.wrap("foo") == Wrapper.wrap("foo")
        }
    }

    @Test
    fun `equals should be false for non matching strings`() {
        assertFalse {
            Wrapper.wrap("foo") == Wrapper.wrap("bar")
        }
    }

    @Test
    fun `equals should be false for non CharSequenceWrapper`() {
        assertFalse {
            Wrapper.wrap("foo").equals("foo")
        }
        assertFalse {
            Wrapper.wrap("foo") == mockk<AString>()
        }
        assertFalse {
            Wrapper.wrap("foo") == mockk<Wrapper>()
        }
        assertFalse {
            Wrapper.wrap("foo").equals(mockk<Provider>())
        }
        assertFalse {
            Wrapper.wrap("foo").equals(mockk<Resource>())
        }
    }

    @Test
    fun `hashCode should return delegate to string`() {
        assertEquals(
            "foo".hashCode(),
            Wrapper.wrap("foo").hashCode(),
        )
        assertEquals(
            "bar".hashCode(),
            Wrapper.wrap("bar").hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(CharSequence(foo))",
            Wrapper.wrap("foo").toString(),
        )
    }
}
