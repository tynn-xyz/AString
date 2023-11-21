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
            every { resources.getText(1) } returns "foo"
        }

        assertEquals(
            "foo",
            ResourceDelegate.text(1).invoke(context),
        )
    }

    @Test
    fun `equals should be true for matching text resources`() {
        assertTrue {
            ResourceDelegate.text(1) == ResourceDelegate.text(1)
        }
    }

    @Test
    fun `equals should be false for non matching text resources`() {
        assertFalse {
            ResourceDelegate.text(1) == ResourceDelegate.text(2)
        }
    }

    @Test
    fun `equals should be false for non TextResourceDelegate`() {
        assertFalse {
            ResourceDelegate.text(1).equals("foo")
        }
        assertFalse {
            ResourceDelegate.text(1) == mockk<AString>()
        }
        assertFalse {
            ResourceDelegate.text(1).equals(mockk<CharSequenceWrapper>())
        }
        assertFalse {
            ResourceDelegate.text(1).equals(mockk<ContextValueProvider>())
        }
        assertFalse {
            ResourceDelegate.text(1).equals(mockk<ResourceDelegate>())
        }
    }

    @Test
    fun `hashCode should return delegate to text resource`() {
        assertEquals(
            -939785338,
            ResourceDelegate.text(1).hashCode(),
        )
        assertEquals(
            -939784377,
            ResourceDelegate.text(2).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(TextResource(1))",
            ResourceDelegate.text(1).toString(),
        )
    }
}
