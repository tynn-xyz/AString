//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import kotlin.test.*

internal class AppIdProviderTest {

    @Test
    fun `invoke should return the package name`() {
        val context = mockk<Context> {
            every { packageName } returns "foo"
        }

        assertEquals(
            "foo",
            ValueProvider.AppIdProvider.invoke(context),
        )
    }

    @Test
    fun `equals should be true for same type`() {
        assertTrue {
            ValueProvider.AppIdProvider == ValueProvider.AppIdProvider
        }
    }

    @Test
    @Suppress("EqualsBetweenInconvertibleTypes")
    fun `equals should be false for non AppIdProvider`() {
        assertFalse {
            ValueProvider.AppIdProvider.equals("foo")
        }
        assertFalse {
            ValueProvider.AppIdProvider == mockk<AString>()
        }
        assertFalse {
            ValueProvider.AppIdProvider == ValueProvider.AppVersionProvider
        }
        assertFalse {
            ValueProvider.AppIdProvider == mockk<ValueProvider>()
        }
    }

    @Test
    fun `hashCode should return 0`() {
        assertNotEquals(
            0,
            ValueProvider.AppIdProvider.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(Context(appId))",
            ValueProvider.AppIdProvider.toString(),
        )
    }
}
