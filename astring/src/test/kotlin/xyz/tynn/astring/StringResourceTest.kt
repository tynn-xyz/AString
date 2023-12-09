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

internal class StringResourceTest {

    @Test
    fun `invoke should return string without format args`() {
        val context = mockk<Context> {
            every { getText(1) } returns StringBuilder("foo")
        }

        assertEquals(
            "foo",
            Resource.wrap(1, null, arrayOf()).invoke(context),
        )
    }

    @Test
    fun `invoke should return string with format args`() {
        val context = mockk<Context> {
            every { getText(1) } returns "foo%d%s"
            every { resources.configuration } returns mockk {
                @Suppress("DEPRECATION")
                locale = Locale.getDefault()
            }
        }

        assertEquals(
            "foo23",
            Resource.wrap(1, null, arrayOf(2, "3")).invoke(context),
        )
    }

    @Test
    fun `equals should be true for matching string resources`() {
        assertTrue {
            Resource.wrap(1, null, null) ==
                    Resource.wrap(1, null, arrayOf())
        }
        assertTrue {
            Resource.wrap(1, null, arrayOf()) ==
                    Resource.wrap(1, null, arrayOf())
        }
        assertTrue {
            Resource.wrap(1, null, arrayOf(2, "3")) ==
                    Resource.wrap(1, null, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non matching string resources`() {
        assertFalse {
            Resource.wrap(1, null, arrayOf()) ==
                    Resource.wrap(2, null, arrayOf())
        }
        assertFalse {
            Resource.wrap(1, null, arrayOf(2, "3")) ==
                    Resource.wrap(2, null, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non matching format args`() {
        assertFalse {
            Resource.wrap(1, null, arrayOf(2, 3)) ==
                    Resource.wrap(1, null, arrayOf("2", "3"))
        }
        assertFalse {
            Resource.wrap(1, null, arrayOf()) ==
                    Resource.wrap(1, null, arrayOf(2, "3"))
        }
    }

    @Test
    fun `equals should be false for non StringResourceDelegate`() {
        assertFalse {
            Resource.wrap(1, null, arrayOf()).equals("foo")
        }
        assertFalse {
            Resource.wrap(1, null, arrayOf()) == mockk<AString>()
        }
        assertFalse {
            Resource.wrap(1, null, arrayOf())
                .equals(mockk<Wrapper>())
        }
        assertFalse {
            Resource.wrap(1, null, arrayOf())
                .equals(mockk<Provider>())
        }
        assertFalse {
            Resource.wrap(1, null, arrayOf())
                .equals(mockk<Resource>())
        }
    }

    @Test
    fun `hashCode should return delegate to string resource`() {
        assertEquals(
            30506945,
            Resource.wrap(1, null, null).hashCode(),
        )
        assertEquals(
            30506945,
            Resource.wrap(1, null, arrayOf()).hashCode(),
        )
        assertEquals(
            30508019,
            Resource.wrap(1, null, arrayOf(2, "3")).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(String(AString(TextResource(1))))",
            Resource.wrap(1, null, arrayOf()).toString(),
        )
        assertEquals(
            "AString(Format(AString(TextResource(1)),2,3))",
            Resource.wrap(1, null, arrayOf(2, "3")).toString(),
        )
    }
}
