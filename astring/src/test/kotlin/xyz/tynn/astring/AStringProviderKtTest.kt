//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import xyz.tynn.astring.Provider.AppId
import xyz.tynn.astring.Provider.AppVersion
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

@InefficientAStringApi
internal class AStringProviderKtTest {

    @Test
    fun `equals should be true for matching provider`() {
        assertTrue {
            Delegate.wrap(AppId) == Delegate.wrap(AppId)
        }
        assertTrue {
            Delegate.wrap(AppVersion) == Delegate.wrap(AppVersion)
        }
        assertTrue {
            Delegate.wrap(Provider()) == Delegate.wrap(Provider())
        }
    }

    @Test
    fun `equals should be false for non matching provider`() {
        assertFalse {
            Delegate.wrap(mockk<AString.Provider>()) == Delegate.wrap(mockk<AString.Provider>())
        }
    }

    @Test
    fun `equals should be false for non provider Delegate`() {
        assertFalse {
            Delegate.wrap(mockk<AString.Provider>()).equals("foo")
        }
        assertFalse {
            Delegate.wrap(mockk<AString.Provider>()) == mockk<AString>()
        }
        assertFalse {
            Delegate.wrap(mockk<AString.Provider>()).equals(mockk<Wrapper>())
        }
        assertFalse {
            Delegate.wrap(mockk<AString.Provider>()).equals(mockk<Provider>())
        }
        assertFalse {
            Delegate.wrap(mockk<AString.Provider>()).equals(mockk<Resource>())
        }
        assertFalse {
            Delegate.wrap(mockk<AString.Provider>()) ==
                    Delegate.wrap(mockk(), mockk<AString.Transformer>())
        }
    }

    @Test
    fun `hashCode should return delegate to provider`() {
        assertEquals(
            4775,
            Delegate.wrap(
                mockk<AString.Provider> { every { this@mockk.hashCode() } returns 123 },
            ).hashCode(),
        )
    }

    @Test
    fun `toString should delegate to provider`() {
        val provider = mockk<AString.Provider> {
            every { this@mockk.toString() } returns "to-string"
        }
        assertEquals(
            "AString(to-string)",
            AString(provider).toString(),
        )
    }

    @Test
    @Suppress("RedundantSamConstructor")
    fun `interface should not be efficient`() {
        assertNotEquals(
            AString(AString.Provider { "" }),
            AString(AString.Provider { "" }),
        )
    }

    @Test
    fun `interface val should be efficient`() {
        val function = AString.Provider { "" }
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

    private class Provider : AString.Provider {
        override fun invoke(context: Context) = context.toString()
        override fun equals(other: Any?) = other is Provider
        override fun hashCode() = 0
    }
}
