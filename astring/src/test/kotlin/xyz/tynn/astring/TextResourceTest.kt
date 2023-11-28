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

internal class TextResourceTest {

    @Test
    fun `invoke should return text`() {
        val context = mockk<Context> {
            every { getText(1) } returns "foo"
        }

        assertEquals(
            "foo",
            Resource.wrap(1, null).invoke(context),
        )
    }

    @Test
    fun `equals should be true for matching text resources`() {
        assertTrue {
            Resource.wrap(1, null) ==
                    Resource.wrap(1, null)
        }
    }

    @Test
    fun `equals should be false for non matching text resources`() {
        assertFalse {
            Resource.wrap(1, null) ==
                    Resource.wrap(2, null)
        }
    }

    @Test
    fun `equals should be false for non TextResourceDelegate`() {
        assertFalse {
            Resource.wrap(1, null).equals("foo")
        }
        assertFalse {
            Resource.wrap(1, null) == mockk<AString>()
        }
        assertFalse {
            Resource.wrap(1, null).equals(mockk<Wrapper>())
        }
        assertFalse {
            Resource.wrap(1, null).equals(mockk<Provider>())
        }
        assertFalse {
            Resource.wrap(1, null).equals(mockk<Resource>())
        }
    }

    @Test
    fun `hashCode should return delegate to text resource`() {
        assertEquals(
            992,
            Resource.wrap(1, null).hashCode(),
        )
        assertEquals(
            1023,
            Resource.wrap(2, null).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(TextResource(1))",
            Resource.wrap(1, null).toString(),
        )
    }
}
