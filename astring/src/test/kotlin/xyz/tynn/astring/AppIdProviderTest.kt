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
            Provider.AppIdProvider.invoke(context),
        )
    }

    @Test
    @Suppress("KotlinConstantConditions")
    fun `equals should be true for same type`() {
        assertTrue {
            Provider.AppIdProvider == Provider.AppIdProvider
        }
    }

    @Test
    @Suppress("EqualsBetweenInconvertibleTypes", "KotlinConstantConditions")
    fun `equals should be false for non AppIdProvider`() {
        assertFalse {
            Provider.AppIdProvider.equals("foo")
        }
        assertFalse {
            Provider.AppIdProvider == mockk<AString>()
        }
        assertFalse {
            Provider.AppIdProvider == Provider.AppVersionProvider
        }
        assertFalse {
            Provider.AppIdProvider == mockk<Provider>()
        }
    }

    @Test
    fun `hashCode should return 0`() {
        assertNotEquals(
            0,
            Provider.AppIdProvider.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(Context(appId))",
            Provider.AppIdProvider.toString(),
        )
    }
}
