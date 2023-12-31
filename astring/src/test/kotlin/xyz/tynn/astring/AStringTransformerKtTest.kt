//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@InefficientAStringApi
internal class AStringTransformerKtTest {

    private val context = mockk<Context>()
    private val aString = mockk<AString>()

    @Test
    fun `equals should be true for matching transformer and receiver`() {
        assertTrue {
            Delegate.wrap(Transformer(), AppId) == Delegate.wrap(Transformer(), AppId)
        }
        assertTrue {
            Delegate.wrap(Transformer(), AppVersion) == Delegate.wrap(Transformer(), AppVersion)
        }
    }

    @Test
    fun `equals should be false for non matching transformer`() {
        assertFalse {
            Delegate.wrap(mockk<AString.Transformer>(), AppId) ==
                    Delegate.wrap(mockk<AString.Transformer>(), AppId)
        }
    }

    @Test
    fun `equals should be false for non matching receiver`() {
        assertFalse {
            Delegate.wrap(Transformer(), mockk()) == Delegate.wrap(Transformer(), mockk())
        }
    }

    @Test
    fun `equals should be false for non transformer Delegate`() {
        assertFalse {
            Delegate.wrap(mockk<AString.Transformer>(), mockk()).equals("foo")
        }
        assertFalse {
            Delegate.wrap(mockk<AString.Transformer>(), mockk()) == mockk<AString>()
        }
        assertFalse {
            Delegate.wrap(mockk<AString.Transformer>(), mockk()).equals(mockk<Wrapper>())
        }
        assertFalse {
            Delegate.wrap(mockk<AString.Transformer>(), mockk()).equals(mockk<Transformer>())
        }
        assertFalse {
            Delegate.wrap(mockk<AString.Transformer>(), mockk()).equals(mockk<Resource>())
        }
        assertFalse {
            Delegate.wrap(mockk<AString.Transformer>(), mockk()) ==
                    Delegate.wrap(mockk<AString.Provider>())
        }
    }

    @Test
    fun `hashCode should return delegate to transformer and receiver`() {
        assertEquals(
            5150,
            Delegate.wrap(
                mockk<AString.Transformer> { every { this@mockk.hashCode() } returns 123 },
                mockk { every { this@mockk.hashCode() } returns 345 },
            ).hashCode(),
        )
    }

    @Test
    fun `toString should delegate to transformer and receiver`() {
        val transformer = mockk<AString.Transformer> {
            every { this@mockk.toString() } returns "to-string"
        }
        assertEquals(
            "AString(Map(to-string),$AppId)",
            AppId.map(transformer).toString(),
        )
    }

    @Test
    fun `nullIfBlank should map a blank string to null`() {
        assertNull(context.aString(AString(null).nullIfBlank()))
        assertNull(context.aString(AString("").nullIfBlank()))
        assertNull(context.aString(AString(" ").nullIfBlank()))
        assertEquals(
            "value",
            context.aString(
                AString("value")
                    .nullIfBlank(),
            )
        )
    }

    @Test
    fun `nullIfEmpty should map a blank string to null`() {
        assertNull(context.aString(AString(null).nullIfBlank()))
        assertNull(context.aString(AString("").nullIfBlank()))
        assertEquals(
            " ",
            context.aString(
                AString(" ")
                    .nullIfEmpty(),
            )
        )
        assertEquals(
            "value",
            context.aString(
                AString("value")
                    .nullIfEmpty(),
            )
        )
    }

    @Test
    fun `ifNull should return original if non null`() {
        assertEquals(
            "",
            context.aString(
                AString(
                    value = "",
                ).ifNull(
                    defaultValue = " ",
                ),
            ),
        )
        assertEquals(
            " ",
            context.aString(
                AString(
                    value = " ",
                ).ifNull(
                    defaultValue = "",
                ),
            ),
        )
        assertEquals(
            "value",
            context.aString(
                AString(
                    value = "value",
                ).ifNull(
                    defaultValue = "",
                ),
            ),
        )
        assertEquals(
            "",
            context.aString(
                AString(
                    value = "",
                ).ifNull(
                    defaultValue = AString(
                        value = " ",
                    ),
                ),
            ),
        )
        assertEquals(
            " ",
            context.aString(
                AString(
                    value = " ",
                ).ifNull(
                    defaultValue = AString(
                        value = "",
                    ),
                ),
            ),
        )
        assertEquals(
            "value",
            context.aString(
                AString(
                    value = "value",
                ).ifNull(
                    defaultValue = AString(
                        value = "",
                    ),
                ),
            ),
        )
    }

    @Test
    fun `ifNull should return defaultValue if null`() {
        assertEquals(
            "value",
            context.aString(
                AString(
                    value = null,
                ).ifNull(
                    defaultValue = "value",
                ),
            ),
        )
        assertEquals(
            "value",
            context.aString(
                AString(
                    value = null,
                ).ifNull(
                    defaultValue = AString(
                        value = "value",
                    )
                ),
            ),
        )
    }

    @Test
    @Suppress("RedundantSamConstructor")
    fun `interface should not be efficient`() {
        assertNotEquals(
            aString.map(AString.Transformer { "" }),
            aString.map(AString.Transformer { "" }),
        )
    }

    @Test
    fun `interface val should be efficient`() {
        val function = AString.Transformer { "" }
        assertEquals(
            aString.map(function),
            aString.map(function),
        )
    }

    @Test
    fun `instance should be efficient`() {
        assertEquals(
            aString.map(Transformer()),
            aString.map(Transformer()),
        )
    }

    @Test
    fun `instance val should be efficient`() {
        val function = Transformer()
        assertEquals(
            aString.map(function),
            aString.map(function),
        )
    }

    @Test
    fun `function reference should be efficient`() {
        assertEquals(
            aString.map(::function),
            aString.map(::function),
        )
    }

    @Test
    fun `function reference val should be efficient`() {
        val function = ::function
        assertEquals(
            aString.map(function),
            aString.map(function),
        )
    }

    @Test
    fun `lambda should not be efficient`() {
        assertNotEquals(
            aString.map { it },
            aString.map { it },
        )
    }

    @Test
    fun `lambda val should be efficient`() {
        val function = { it: CharSequence? -> it }
        assertEquals(
            aString.map(function),
            aString.map(function),
        )
    }

    private fun function(value: CharSequence?) = value

    private class Transformer : AString.Transformer {
        override fun invoke(value: CharSequence?) = value
        override fun equals(other: Any?) = other is Transformer
        override fun hashCode() = 0
    }
}
