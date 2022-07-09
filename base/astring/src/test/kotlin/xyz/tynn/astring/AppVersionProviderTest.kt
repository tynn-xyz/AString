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
            ValueProvider.AppVersionProvider.invoke(context),
        )
    }

    @Test
    fun `equals should be true for same type`() {
        assertTrue {
            ValueProvider.AppVersionProvider == ValueProvider.AppVersionProvider
        }
    }

    @Test
    @Suppress("EqualsBetweenInconvertibleTypes")
    fun `equals should be false for non AppVersionProvider`() {
        assertFalse {
            ValueProvider.AppVersionProvider.equals("foo")
        }
        assertFalse {
            ValueProvider.AppVersionProvider == mockk<AString>()
        }
        assertFalse {
            ValueProvider.AppVersionProvider == ValueProvider.AppIdProvider
        }
        assertFalse {
            ValueProvider.AppVersionProvider == mockk<ValueProvider>()
        }
    }

    @Test
    fun `hashCode should not return 0`() {
        assertNotEquals(
            0,
            ValueProvider.AppVersionProvider.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(Context(appVersion))",
            ValueProvider.AppVersionProvider.toString(),
        )
    }
}
