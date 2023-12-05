//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class AppIdProviderTest {

    @Test
    fun `invoke should return the package name`() {
        val context = mockk<Context> {
            every { packageName } returns "foo"
        }

        assertEquals(
            "foo",
            Provider.AppId.invoke(context),
        )
    }

    @Test
    @Suppress("KotlinConstantConditions")
    fun `equals should be true for same type`() {
        assertTrue {
            Provider.AppId == Provider.AppId
        }
    }

    @Test
    @Suppress("EqualsBetweenInconvertibleTypes", "KotlinConstantConditions")
    fun `equals should be false for non AppIdProvider`() {
        assertFalse {
            Provider.AppId.equals("foo")
        }
        assertFalse {
            Provider.AppId == mockk<AString>()
        }
        assertFalse {
            Provider.AppId == Provider.AppVersion
        }
        assertFalse {
            Provider.AppId == mockk<Provider>()
        }
    }

    @Test
    fun `hashCode should return 0`() {
        assertNotEquals(
            0,
            Provider.AppId.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(Context(appId))",
            Provider.AppId.toString(),
        )
    }
}
