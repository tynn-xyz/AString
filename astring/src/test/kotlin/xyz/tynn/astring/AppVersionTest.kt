//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import android.content.pm.PackageInfo
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class AppVersionTest {

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
            AppVersion.invoke(context),
        )
    }

    @Test
    @Suppress("KotlinConstantConditions")
    fun `equals should be true for same type`() {
        assertTrue {
            AppVersion == AppVersion
        }
    }

    @Test
    fun `equals should be false for non AppVersionProvider`() {
        assertFalse {
            AppVersion.equals("foo")
        }
        assertFalse {
            AppVersion == mockk<AString>()
        }
        assertFalse {
            AppVersion == AppId
        }
    }

    @Test
    fun `hashCode should not return 0`() {
        assertNotEquals(
            0,
            AppVersion.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(Context(AppVersion))",
            AppVersion.toString(),
        )
    }
}
