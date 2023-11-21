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

internal class StringResourceDelegateTest {

    @Test
    fun `invoke should return string without format args`() {
        val context = mockk<Context> {
            every { resources.getText(1) } returns StringBuilder("foo")
        }

        assertEquals(
            "foo",
            ResourceDelegate.string(1, arrayOf()).invoke(context),
        )
    }

    @Test
    fun `invoke should return string with format args`() {
        val context = mockk<Context> {
            every { resources.getText(1) } returns "foo%d%s"
            every { resources.configuration } returns mockk {
                @Suppress("DEPRECATION")
                locale = Locale.getDefault()
            }
        }

        assertEquals(
            "foo23",
            ResourceDelegate.string(1, arrayOf(2, "3")).invoke(context),
        )
    }

    @Test
    fun `equals should be true for matching string resources`() {
        assertTrue {
            ResourceDelegate.string(1, null) ==
                    ResourceDelegate.string(1, arrayOf())
        }
        assertTrue {
            ResourceDelegate.string(1, arrayOf()) ==
                    ResourceDelegate.string(1, arrayOf())
        }
        assertTrue {
            ResourceDelegate.string(1, arrayOf(2, "3")) ==
                    ResourceDelegate.string(1, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non matching string resources`() {
        assertFalse {
            ResourceDelegate.string(1, arrayOf()) ==
                    ResourceDelegate.string(2, arrayOf())
        }
        assertFalse {
            ResourceDelegate.string(1, arrayOf(2, "3")) ==
                    ResourceDelegate.string(2, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non matching format args`() {
        assertFalse {
            ResourceDelegate.string(1, arrayOf(2, 3)) ==
                    ResourceDelegate.string(1, arrayOf("2", "3"))
        }
        assertFalse {
            ResourceDelegate.string(1, arrayOf()) ==
                    ResourceDelegate.string(1, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non StringResourceDelegate`() {
        assertFalse {
            ResourceDelegate.string(1, arrayOf()).equals("foo")
        }
        assertFalse {
            ResourceDelegate.string(1, arrayOf()) == mockk<AString>()
        }
        assertFalse {
            ResourceDelegate.string(1, arrayOf()).equals(mockk<CharSequenceWrapper>())
        }
        assertFalse {
            ResourceDelegate.string(1, arrayOf()).equals(mockk<ContextValueProvider>())
        }
        assertFalse {
            ResourceDelegate.string(1, arrayOf()).equals(mockk<ResourceDelegate>())
        }
    }

    @Test
    fun `hashCode should return delegate to string resource`() {
        assertEquals(
            -939606592,
            ResourceDelegate.string(1, null).hashCode(),
        )
        assertEquals(
            -939606592,
            ResourceDelegate.string(1, arrayOf()).hashCode(),
        )
        assertEquals(
            -939605518,
            ResourceDelegate.string(1, arrayOf(2, "3")).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(StringResource(1))",
            ResourceDelegate.string(1, arrayOf()).toString(),
        )
        assertEquals(
            "AString(StringResource(1,2,3))",
            ResourceDelegate.string(1, arrayOf(2, "3")).toString(),
        )
    }
}
