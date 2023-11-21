//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import java.util.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class QuantityStringResourceDelegateTest {

    @Test
    fun `invoke should return string without format args`() {
        val context = mockk<Context> {
            every { resources.getQuantityText(1, 2) } returns "foo"
        }

        assertEquals(
            "foo",
            ResourceDelegate.quantityString(1, 2, arrayOf()).invoke(context),
        )
    }

    @Test
    fun `invoke should return string with format args`() {
        val context = mockk<Context> {
            every { resources.getQuantityText(1, 2) } returns "foo%d%s"
            every { resources.configuration } returns mockk {
                @Suppress("DEPRECATION")
                locale = Locale.getDefault()
            }
        }

        assertEquals(
            "foo34",
            ResourceDelegate.quantityString(1, 2, arrayOf(3, "4")).invoke(context),
        )
    }

    @Test
    fun `equals should be true for matching string resources and quantities`() {
        assertTrue {
            ResourceDelegate.quantityString(1, 2, null) ==
                    ResourceDelegate.quantityString(1, 2, arrayOf())
        }
        assertTrue {
            ResourceDelegate.quantityString(1, 2, arrayOf()) ==
                    ResourceDelegate.quantityString(1, 2, arrayOf())
        }
        assertTrue {
            ResourceDelegate.quantityString(1, 2, arrayOf(2, "3")) ==
                    ResourceDelegate.quantityString(1, 2, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non matching string resources or quantities`() {
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf()) ==
                    ResourceDelegate.quantityString(1, 1, arrayOf())
        }
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf(3, "4")) ==
                    ResourceDelegate.quantityString(1, 1, arrayOf(3, "4"))
        }
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf()) ==
                    ResourceDelegate.quantityString(2, 2, arrayOf())
        }
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf(3, "4")) ==
                    ResourceDelegate.quantityString(2, 2, arrayOf(3, "4"))
        }
    }

    @Test
    fun `equals should be false for non matching format args`() {
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf(3, 4)) ==
                    ResourceDelegate.quantityString(1, 2, arrayOf("3", "4"))
        }
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf()) ==
                    ResourceDelegate.quantityString(1, 2, arrayOf(3, "4"))
        }
    }

    @Test
    fun `equals should be false for non QuantityStringResourceDelegate`() {
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf()).equals("foo")
        }
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf()) == mockk<AString>()
        }
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf()).equals(mockk<CharSequenceWrapper>())
        }
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf()).equals(mockk<ContextValueProvider>())
        }
        assertFalse {
            ResourceDelegate.quantityString(1, 2, arrayOf()).equals(mockk<ResourceDelegate>())
        }
    }

    @Test
    fun `hashCode should return delegate to string resource and quantity`() {
        assertEquals(
            1202335992,
            ResourceDelegate.quantityString(1, 2, null).hashCode(),
        )
        assertEquals(
            1202335992,
            ResourceDelegate.quantityString(1, 2, arrayOf()).hashCode(),
        )
        assertEquals(
            1202337098,
            ResourceDelegate.quantityString(1, 2, arrayOf(3, "4")).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(QuantityStringResource(1,2))",
            ResourceDelegate.quantityString(1, 2, arrayOf()).toString(),
        )
        assertEquals(
            "AString(QuantityStringResource(1,2,3,4))",
            ResourceDelegate.quantityString(1, 2, arrayOf(3, "4")).toString(),
        )
    }
}
