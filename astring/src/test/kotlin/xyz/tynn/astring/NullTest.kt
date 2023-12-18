//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

internal class NullTest {

    @Test
    fun `Null should be the NULL singleton`() {
        assertSame(
            Wrapper.wrap(null),
            AString.Null,
        )
        assertSame(
            AString.Null,
            AString.Null,
        )
    }

    @Test
    fun `invoke should return null`() {
        assertNull(
            AString.Null.invoke(mockk<Context>()),
        )
    }

    @Test
    @Suppress("KotlinConstantConditions")
    fun `identity should be true for same type`() {
        assertTrue {
            AString.Null === AString.Null
        }
    }

    @Test
    @Suppress("KotlinConstantConditions")
    fun `equals should be true for same type`() {
        assertTrue {
            AString.Null == AString.Null
        }
    }

    @Test
    fun `equals should be false for non NullValueProvider`() {
        assertFalse {
            AString.Null.equals("foo")
        }
        assertFalse {
            AString.Null == mockk<AString>()
        }
        assertFalse {
            AString.Null == Wrapper.wrap("")
        }
        assertFalse {
            AString.Null == mockk<Delegate>()
        }
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(CharSequence(null))",
            AString.Null.toString(),
        )
    }
}
