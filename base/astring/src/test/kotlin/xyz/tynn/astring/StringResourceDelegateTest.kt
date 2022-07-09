//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class StringResourceDelegateTest {

    @Test
    fun `invoke should return string without format args`() {
        val context = mockk<Context> {
            every { getString(1) } returns "foo"
        }

        assertEquals(
            "foo",
            StringResourceDelegate(1, arrayOf()).invoke(context),
        )
    }

    @Test
    fun `invoke should return string with format args`() {
        val context = mockk<Context> {
            every { getString(1, 2, "3") } returns "foo"
        }

        assertEquals(
            "foo",
            StringResourceDelegate(1, arrayOf(2, "3")).invoke(context),
        )
    }

    @Test
    fun `equals should be true for matching string resources`() {
        assertTrue {
            StringResourceDelegate(1, null) ==
                    StringResourceDelegate(1, arrayOf())
        }
        assertTrue {
            StringResourceDelegate(1, arrayOf()) ==
                    StringResourceDelegate(1, arrayOf())
        }
        assertTrue {
            StringResourceDelegate(1, arrayOf(2, "3")) ==
                    StringResourceDelegate(1, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non matching string resources`() {
        assertFalse {
            StringResourceDelegate(1, arrayOf()) ==
                    StringResourceDelegate(2, arrayOf())
        }
        assertFalse {
            StringResourceDelegate(1, arrayOf(2, "3")) ==
                    StringResourceDelegate(2, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non matching format args`() {
        assertFalse {
            StringResourceDelegate(1, arrayOf(2, 3)) ==
                    StringResourceDelegate(1, arrayOf("2", "3"))
        }
        assertFalse {
            StringResourceDelegate(1, arrayOf()) ==
                    StringResourceDelegate(1, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non StringResourceDelegate`() {
        assertFalse {
            StringResourceDelegate(1, arrayOf()).equals("foo")
        }
        assertFalse {
            StringResourceDelegate(1, arrayOf()) == mockk<AString>()
        }
        assertFalse {
            StringResourceDelegate(1, arrayOf()).equals(mockk<CharSequenceWrapper>())
        }
        assertFalse {
            StringResourceDelegate(1, arrayOf()).equals(mockk<ValueProvider>())
        }
        assertFalse {
            StringResourceDelegate(1, arrayOf()).equals(mockk<QuantityStringResourceDelegate>())
        }
        assertFalse {
            StringResourceDelegate(1, arrayOf()).equals(mockk<QuantityTextResourceDelegate>())
        }
        assertFalse {
            StringResourceDelegate(1, arrayOf()).equals(mockk<TextResourceDelegate>())
        }
    }

    @Test
    fun `hashCode should return delegate to string resource`() {
        assertEquals(
            31,
            StringResourceDelegate(1, null).hashCode(),
        )
        assertEquals(
            31,
            StringResourceDelegate(1, arrayOf()).hashCode(),
        )
        assertEquals(
            31 + arrayOf(2, "3").contentHashCode(),
            StringResourceDelegate(1, arrayOf(2, "3")).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(StringResource(1))",
            StringResourceDelegate(1, arrayOf()).toString(),
        )
        assertEquals(
            "AString(StringResource(1,2,3))",
            StringResourceDelegate(1, arrayOf(2, "3")).toString(),
        )
    }
}
