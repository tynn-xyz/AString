//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@InefficientAStringApi
internal class AStringProviderKtTest {

    private val context = mockk<Context>()

    @Test
    fun `AString should return provided string`() {
        val string = mockk<CharSequence>()
        assertEquals(
            string,
            context.aString(
                AString { string },
            ),
        )
    }

    @Test
    @Suppress("RedundantSamConstructor")
    fun `interface should not be efficient`() {
        assertNotEquals(
            AString(AStringProvider { "" }),
            AString(AStringProvider { "" }),
        )
    }

    @Test
    fun `interface val should be efficient`() {
        val function = AStringProvider { "" }
        assertEquals(
            AString(function),
            AString(function),
        )
    }

    @Test
    fun `instance should be efficient`() {
        assertEquals(
            AString(Provider()),
            AString(Provider()),
        )
    }

    @Test
    fun `instance val should be efficient`() {
        val function = Provider()
        assertEquals(
            AString(function),
            AString(function),
        )
    }

    @Test
    fun `function reference should be efficient`() {
        assertEquals(
            AString(::function),
            AString(::function),
        )
    }

    @Test
    fun `function reference val should be efficient`() {
        val function = ::function
        assertEquals(
            AString(function),
            AString(function),
        )
    }

    @Test
    fun `lambda should not be efficient`() {
        assertNotEquals(
            AString { it.toString() },
            AString { it.toString() },
        )
    }

    @Test
    fun `lambda val should be efficient`() {
        val function = { _: Context -> "" }
        assertEquals(
            AString(function),
            AString(function),
        )
    }

    private fun function(context: Context) = context.toString()

    private class Provider : AStringProvider {
        override fun invoke(context: Context) = context.toString()
        override fun equals(other: Any?) = other is Provider
        override fun hashCode() = 0
    }
}
