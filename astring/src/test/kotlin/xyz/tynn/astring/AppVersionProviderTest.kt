//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import android.content.pm.PackageInfo
import io.mockk.every
import io.mockk.mockk
import kotlin.test.*

internal class AppVersionProviderTest {

    @Test
    fun `invoke should return the package name`() {
        val info = PackageInfo().apply {
            versionName = "foo"
        }
        val context = mockk<Context> {
            every { packageName } returns "bar"
            every { packageManager.getPackageInfo("bar", 0) } returns info
        }

        assertEquals(
            "foo",
            Provider.AppVersionProvider.invoke(context),
        )
    }

    @Test
    @Suppress("KotlinConstantConditions")
    fun `equals should be true for same type`() {
        assertTrue {
            Provider.AppVersionProvider == Provider.AppVersionProvider
        }
    }

    @Test
    @Suppress("EqualsBetweenInconvertibleTypes", "KotlinConstantConditions")
    fun `equals should be false for non AppVersionProvider`() {
        assertFalse {
            Provider.AppVersionProvider.equals("foo")
        }
        assertFalse {
            Provider.AppVersionProvider == mockk<AString>()
        }
        assertFalse {
            Provider.AppVersionProvider == Provider.AppIdProvider
        }
        assertFalse {
            Provider.AppVersionProvider == mockk<Provider>()
        }
    }

    @Test
    fun `hashCode should not return 0`() {
        assertNotEquals(
            0,
            Provider.AppVersionProvider.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(Context(appVersion))",
            Provider.AppVersionProvider.toString(),
        )
    }
}
