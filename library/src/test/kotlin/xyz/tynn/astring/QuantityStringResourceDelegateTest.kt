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

internal class QuantityStringResourceDelegateTest {

    @Test
    fun `invoke should return string without format args`() {
        val context = mockk<Context> {
            every { resources.getQuantityString(1, 2) } returns "foo"
        }

        assertEquals(
            "foo",
            QuantityStringResourceDelegate(1, 2, arrayOf()).invoke(context),
        )
    }

    @Test
    fun `invoke should return string with format args`() {
        val context = mockk<Context> {
            every { resources.getQuantityString(1, 2, 3, "4") } returns "foo"
        }

        assertEquals(
            "foo",
            QuantityStringResourceDelegate(1, 2, arrayOf(3, "4")).invoke(context),
        )
    }

    @Test
    fun `equals should be true for matching string resources and quantities`() {
        assertTrue {
            QuantityStringResourceDelegate(1, 2, null) ==
                    QuantityStringResourceDelegate(1, 2, arrayOf())
        }
        assertTrue {
            QuantityStringResourceDelegate(1, 2, arrayOf()) ==
                    QuantityStringResourceDelegate(1, 2, arrayOf())
        }
        assertTrue {
            QuantityStringResourceDelegate(1, 2, arrayOf(2, "3")) ==
                    QuantityStringResourceDelegate(1, 2, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non matching string resources or quantities`() {
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf()) ==
                    QuantityStringResourceDelegate(1, 1, arrayOf())
        }
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf(3, "4")) ==
                    QuantityStringResourceDelegate(1, 1, arrayOf(3, "4"))
        }
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf()) ==
                    QuantityStringResourceDelegate(2, 2, arrayOf())
        }
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf(3, "4")) ==
                    QuantityStringResourceDelegate(2, 2, arrayOf(3, "4"))
        }
    }

    @Test
    fun `equals should be false for non matching format args`() {
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf(3, 4)) ==
                    QuantityStringResourceDelegate(1, 2, arrayOf("3", "4"))
        }
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf()) ==
                    QuantityStringResourceDelegate(1, 2, arrayOf(3, "4"))
        }
    }

    @Test
    fun `equals should be false for non QuantityStringResourceDelegate`() {
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf()).equals("foo")
        }
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf()) == mockk<AString>()
        }
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf()).equals(mockk<CharSequenceWrapper>())
        }
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf()).equals(mockk<NullValueWrapper>())
        }
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf()).equals(mockk<QuantityTextResourceDelegate>())
        }
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf()).equals(mockk<StringResourceDelegate>())
        }
        assertFalse {
            QuantityStringResourceDelegate(1, 2, arrayOf()).equals(mockk<TextResourceDelegate>())
        }
    }

    @Test
    fun `hashCode should return delegate to string resource and quantity`() {
        assertEquals(
            1023,
            QuantityStringResourceDelegate(1, 2, null).hashCode(),
        )
        assertEquals(
            1023,
            QuantityStringResourceDelegate(1, 2, arrayOf()).hashCode(),
        )
        assertEquals(
            1023 + arrayOf(3, "4").contentHashCode(),
            QuantityStringResourceDelegate(1, 2, arrayOf(3, "4")).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(QuantityStringResource(1,2))",
            QuantityStringResourceDelegate(1, 2, arrayOf()).toString(),
        )
        assertEquals(
            "AString(QuantityStringResource(1,2,3,4))",
            QuantityStringResourceDelegate(1, 2, arrayOf(3, "4")).toString(),
        )
    }
}
