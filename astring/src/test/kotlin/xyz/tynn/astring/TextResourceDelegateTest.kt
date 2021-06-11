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

internal class TextResourceDelegateTest {

    @Test
    fun `invoke should return text`() {
        val context = mockk<Context> {
            every { getText(1) } returns "foo"
        }

        assertEquals(
            "foo",
            TextResourceDelegate(1).invoke(context),
        )
    }

    @Test
    fun `equals should be true for matching text resources`() {
        assertTrue {
            TextResourceDelegate(1) == TextResourceDelegate(1)
        }
    }

    @Test
    fun `equals should be false for non matching text resources`() {
        assertFalse {
            TextResourceDelegate(1) == TextResourceDelegate(2)
        }
    }

    @Test
    fun `equals should be false for non TextResourceDelegate`() {
        assertFalse {
            TextResourceDelegate(1).equals("foo")
        }
        assertFalse {
            TextResourceDelegate(1) == mockk<AString>()
        }
        assertFalse {
            TextResourceDelegate(1).equals(mockk<CharSequenceWrapper>())
        }
        assertFalse {
            TextResourceDelegate(1).equals(mockk<NullValueWrapper>())
        }
        assertFalse {
            TextResourceDelegate(1).equals(mockk<QuantityStringResourceDelegate>())
        }
        assertFalse {
            TextResourceDelegate(1).equals(mockk<QuantityTextResourceDelegate>())
        }
        assertFalse {
            TextResourceDelegate(1).equals(mockk<StringResourceDelegate>())
        }
    }

    @Test
    fun `hashCode should return delegate to text resource`() {
        assertEquals(
            1,
            TextResourceDelegate(1).hashCode(),
        )
        assertEquals(
            2,
            TextResourceDelegate(2).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(TextResource(1))",
            TextResourceDelegate(1).toString(),
        )
    }
}
