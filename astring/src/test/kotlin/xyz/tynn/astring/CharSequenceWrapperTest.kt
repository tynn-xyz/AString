//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class CharSequenceWrapperTest {

    @Test
    fun `invoke should return the string`() {
        assertEquals(
            "foo",
            CharSequenceWrapper("foo").invoke(null),
        )
    }

    @Test
    fun `equals should be true for matching strings`() {
        assertTrue {
            CharSequenceWrapper("foo") == CharSequenceWrapper("foo")
        }
    }

    @Test
    fun `equals should be false for non matching strings`() {
        assertFalse {
            CharSequenceWrapper("foo") == CharSequenceWrapper("bar")
        }
    }

    @Test
    fun `equals should be false for non CharSequenceWrapper`() {
        assertFalse {
            CharSequenceWrapper("foo").equals("foo")
        }
        assertFalse {
            CharSequenceWrapper("foo") == mockk<AString>()
        }
        assertFalse {
            CharSequenceWrapper("foo").equals(mockk<CharSequenceWrapper>())
        }
        assertFalse {
            CharSequenceWrapper("foo").equals(mockk<ContextValueProvider>())
        }
        assertFalse {
            CharSequenceWrapper("foo").equals(mockk<ResourceDelegate>())
        }
    }

    @Test
    fun `hashCode should return delegate to string`() {
        assertEquals(
            "foo".hashCode(),
            CharSequenceWrapper("foo").hashCode(),
        )
        assertEquals(
            "bar".hashCode(),
            CharSequenceWrapper("bar").hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(CharSequence(foo))",
            CharSequenceWrapper("foo").toString(),
        )
    }
}
