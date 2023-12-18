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

internal class AppIdTest {

    @Test
    fun `invoke should return the package name`() {
        val context = mockk<Context> {
            every { packageName } returns "foo"
        }

        assertEquals(
            "foo",
            AppId.invoke(context),
        )
    }

    @Test
    @Suppress("KotlinConstantConditions")
    fun `equals should be true for same type`() {
        assertTrue {
            AppId == AppId
        }
    }

    @Test
    fun `equals should be false for non AppIdProvider`() {
        assertFalse {
            AppId.equals("foo")
        }
        assertFalse {
            AppId == mockk<AString>()
        }
        assertFalse {
            AppId == AppVersion
        }
    }

    @Test
    fun `hashCode should return 0`() {
        assertNotEquals(
            0,
            AppId.hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(Context(AppId))",
            AppId.toString(),
        )
    }
}
