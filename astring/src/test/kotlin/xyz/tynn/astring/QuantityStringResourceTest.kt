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

internal class QuantityStringResourceTest {

    @Test
    fun `invoke should return string without format args`() {
        val context = mockk<Context> {
            every { resources.getQuantityText(1, 2) } returns "foo"
        }

        assertEquals(
            "foo",
            Resource.wrap(1, 2, arrayOf()).invoke(context),
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
            Resource.wrap(1, 2, arrayOf(3, "4")).invoke(context),
        )
    }

    @Test
    fun `equals should be true for matching string resources and quantities`() {
        assertTrue {
            Resource.wrap(1, 2, null) ==
                    Resource.wrap(1, 2, arrayOf())
        }
        assertTrue {
            Resource.wrap(1, 2, arrayOf()) ==
                    Resource.wrap(1, 2, arrayOf())
        }
        assertTrue {
            Resource.wrap(1, 2, arrayOf(2, "3")) ==
                    Resource.wrap(1, 2, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non matching string resources or quantities`() {
        assertFalse {
            Resource.wrap(1, 2, arrayOf()) ==
                    Resource.wrap(1, 1, arrayOf())
        }
        assertFalse {
            Resource.wrap(1, 2, arrayOf(3, "4")) ==
                    Resource.wrap(1, 1, arrayOf(3, "4"))
        }
        assertFalse {
            Resource.wrap(1, 2, arrayOf()) ==
                    Resource.wrap(2, 2, arrayOf())
        }
        assertFalse {
            Resource.wrap(1, 2, arrayOf(3, "4")) ==
                    Resource.wrap(2, 2, arrayOf(3, "4"))
        }
    }

    @Test
    fun `equals should be false for non matching format args`() {
        assertFalse {
            Resource.wrap(1, 2, arrayOf(3, 4)) ==
                    Resource.wrap(1, 2, arrayOf("3", "4"))
        }
        assertFalse {
            Resource.wrap(1, 2, arrayOf()) ==
                    Resource.wrap(1, 2, arrayOf(3, "4"))
        }
    }

    @Test
    fun `equals should be false for non QuantityStringResourceDelegate`() {
        assertFalse {
            Resource.wrap(1, 2, arrayOf()).equals("foo")
        }
        assertFalse {
            Resource.wrap(1, 2, arrayOf()) == mockk<AString>()
        }
        assertFalse {
            Resource.wrap(1, 2, arrayOf())
                .equals(mockk<Wrapper>())
        }
        assertFalse {
            Resource.wrap(1, 2, arrayOf())
                .equals(mockk<Provider>())
        }
        assertFalse {
            Resource.wrap(1, 2, arrayOf())
                .equals(mockk<Resource>())
        }
    }

    @Test
    fun `hashCode should return delegate to string resource and quantity`() {
        assertEquals(
            985025,
            Resource.wrap(1, 2, null).hashCode(),
        )
        assertEquals(
            985025,
            Resource.wrap(1, 2, arrayOf()).hashCode(),
        )
        assertEquals(
            986131,
            Resource.wrap(1, 2, arrayOf(3, "4")).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(String(AString(QuantityTextResource(1,2))))",
            Resource.wrap(1, 2, arrayOf()).toString(),
        )
        assertEquals(
            "AString(Format(AString(QuantityTextResource(1,2)),3,4))",
            Resource.wrap(1, 2, arrayOf(3, "4")).toString(),
        )
    }
}
