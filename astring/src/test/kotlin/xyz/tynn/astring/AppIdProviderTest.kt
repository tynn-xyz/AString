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
            ContextValueProvider.AppIdProvider.invoke(context),
        )
    }

    @Test
    fun `equals should be true for same type`() {
        assertTrue {
            ContextValueProvider.AppIdProvider == ContextValueProvider.AppIdProvider
        }
    }

    @Test
    @Suppress("EqualsBetweenInconvertibleTypes")
    fun `equals should be false for non AppIdProvider`() {
        assertFalse {
            ContextValueProvider.AppIdProvider.equals("foo")
        }
        assertFalse {
            ContextValueProvider.AppIdProvider == mockk<AString>()
        }
        assertFalse {
            ContextValueProvider.AppIdProvider == ContextValueProvider.AppVersionProvider
        }
        assertFalse {
            ContextValueProvider.AppIdProvider == mockk<ContextValueProvider>()
        }
    }

    @Test
    fun `hashCode should return 0`() {
        assertNotEquals(
            0,
            ContextValueProvider.AppIdProvider.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(Context(appId))",
            ContextValueProvider.AppIdProvider.toString(),
        )
    }
}
