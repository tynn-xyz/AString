//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import android.text.SpannableStringBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

@InefficientAStringApi
internal class AStringReducerKtTest {

    private val context = mockk<Context>()
    private val aString1 = mockk<AString>()
    private val aString2 = mockk<AString>()

    @Test
    fun `equals should be true for matching reducer and receiver`() {
        assertTrue {
            Delegate.wrap(Reducer(), AppId) == Delegate.wrap(Reducer(), AppId)
        }
        assertTrue {
            Delegate.wrap(Reducer(), AppVersion) == Delegate.wrap(Reducer(), AppVersion)
        }
        assertTrue {
            Delegate.wrap(Predicate.AnyValue, AppId) == Delegate.wrap(Predicate.AnyValue, AppId)
        }
        assertTrue {
            Delegate.wrap(Predicate.NonBlank, AppId) == Delegate.wrap(Predicate.NonBlank, AppId)
        }
        assertTrue {
            Delegate.wrap(Predicate.NonEmpty, AppId) == Delegate.wrap(Predicate.NonEmpty, AppId)
        }
        assertTrue {
            Delegate.wrap(Predicate.NonNull, AppId) == Delegate.wrap(Predicate.NonNull, AppId)
        }
    }

    @Test
    fun `equals should be false for non matching reducer`() {
        assertFalse {
            Delegate.wrap(mockk<AString.Reducer>(), AppId) ==
                    Delegate.wrap(mockk<AString.Reducer>(), AppId)
        }
    }

    @Test
    fun `equals should be false for non matching receiver`() {
        assertFalse {
            Delegate.wrap(Reducer(), mockk<AString>()) == Delegate.wrap(Reducer(), mockk<AString>())
        }
    }

    @Test
    fun `equals should be false for non reducer Delegate`() {
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
            Delegate.wrap(mockk<AString.Transformer>(), mockk()).equals(mockk<Reducer>())
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
    fun `hashCode should return delegate to reducer and receiver`() {
        assertEquals(
            17108,
            Delegate.wrap(
                mockk { every { this@mockk.hashCode() } returns 123 },
                mockk { every { this@mockk.hashCode() } returns 345 },
                mockk { every { this@mockk.hashCode() } returns 678 },
            ).hashCode(),
        )
    }

    @Test
    fun `toString should delegate to reducer and receiver`() {
        val reducer = mockk<AString.Reducer> {
            every { this@mockk.toString() } returns "to-string"
        }
        assertEquals(
            "AString(Reduce(to-string),$AppId)",
            reduce(AppId, reducer = reducer).toString(),
        )
    }

    @Test
    fun `firstNonBlank should provide first non blank value`() {
        assertEquals(
            "value",
            context.aString(
                firstNonBlank(
                    AString(null),
                    AString(""),
                    AString(" "),
                    AString("\n \t"),
                    AString("value"),
                    AString("invalid"),
                ),
            ),
        )
        assertEquals(
            "value",
            context.aString(
                listOf(
                    AString(null),
                    AString(""),
                    AString(" "),
                    AString("\n \t"),
                    AString("value"),
                    AString("invalid"),
                ).firstNonBlank(),
            ),
        )
        assertEquals(
            "value",
            context.aString(
                sequenceOf(
                    AString(null),
                    AString(""),
                    AString(" "),
                    AString("\n \t"),
                    AString("value"),
                    AString("invalid"),
                ).asIterable().firstNonBlank(),
            ),
        )
    }

    @Test
    fun `firstNonEmpty should provide first non empty value`() {
        assertEquals(
            " ",
            context.aString(
                firstNonEmpty(
                    AString(null),
                    AString(""),
                    AString(" "),
                    AString("invalid"),
                ),
            ),
        )
        assertEquals(
            " ",
            context.aString(
                listOf(
                    AString(null),
                    AString(""),
                    AString(" "),
                    AString("invalid"),
                ).firstNonEmpty(),
            ),
        )
        assertEquals(
            " ",
            context.aString(
                sequenceOf(
                    AString(null),
                    AString(""),
                    AString(" "),
                    AString("invalid"),
                ).asIterable().firstNonEmpty(),
            ),
        )
    }

    @Test
    fun `firstNonNull should provide first non null value`() {
        assertEquals(
            "",
            context.aString(
                firstNonNull(
                    AString(null),
                    AString(""),
                    AString("invalid"),
                ),
            ),
        )
        assertEquals(
            "",
            context.aString(
                listOf(
                    AString(null),
                    AString(""),
                    AString("invalid"),
                ).firstNonNull(),
            ),
        )
        assertEquals(
            "",
            context.aString(
                sequenceOf(
                    AString(null),
                    AString(""),
                    AString("invalid"),
                ).asIterable().firstNonNull(),
            ),
        )
    }

    @Test
    fun `join should join all values with separator`() {
        withSpannableStringBuilder {
            assertEquals(
                "null--null- -value",
                context.aString(
                    join(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                        separator = "-",
                    ),
                ),
            )
        }
        withSpannableStringBuilder {
            assertEquals(
                "null--null- -value",
                context.aString(
                    listOf(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                    ).join(
                        separator = "-",
                    ),
                ),
            )
        }
        withSpannableStringBuilder {
            assertEquals(
                "null--null- -value",
                context.aString(
                    sequenceOf(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                    ).asIterable().join(
                        separator = "-",
                    ),
                ),
            )
        }
    }

    @Test
    fun `joinNonBlank should join all non blank values with separator`() {
        withSpannableStringBuilder {
            assertEquals(
                "null-value",
                context.aString(
                    joinNonBlank(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                        separator = "-",
                    ),
                ),
            )
        }
        withSpannableStringBuilder {
            assertEquals(
                "null-value",
                context.aString(
                    listOf(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                    ).joinNonBlank(
                        separator = "-",
                    ),
                ),
            )
        }
        withSpannableStringBuilder {
            assertEquals(
                "null-value",
                context.aString(
                    sequenceOf(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                    ).asIterable().joinNonBlank(
                        separator = "-",
                    ),
                ),
            )
        }
    }

    @Test
    fun `joinNonEmpty should join all non empty values with separator`() {
        withSpannableStringBuilder {
            assertEquals(
                "null- -value",
                context.aString(
                    joinNonEmpty(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                        separator = "-",
                    ),
                ),
            )
        }
        withSpannableStringBuilder {
            assertEquals(
                "null- -value",
                context.aString(
                    listOf(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                    ).joinNonEmpty(
                        separator = "-",
                    ),
                ),
            )
        }
        withSpannableStringBuilder {
            assertEquals(
                "null- -value",
                context.aString(
                    sequenceOf(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                    ).asIterable().joinNonEmpty(
                        separator = "-",
                    ),
                ),
            )
        }
    }

    @Test
    fun `joinNonNull should join all non null values with separator`() {
        withSpannableStringBuilder {
            assertEquals(
                "-null- -value",
                context.aString(
                    joinNonNull(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                        separator = "-",
                    ),
                ),
            )
        }
        withSpannableStringBuilder {
            assertEquals(
                "-null- -value",
                context.aString(
                    listOf(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                    ).joinNonNull(
                        separator = "-",
                    ),
                ),
            )
        }
        withSpannableStringBuilder {
            assertEquals(
                "-null- -value",
                context.aString(
                    sequenceOf(
                        AString(null),
                        AString(""),
                        AString("null"),
                        AString(" "),
                        AString("value"),
                    ).asIterable().joinNonNull(
                        separator = "-",
                    ),
                ),
            )
        }
    }

    @Test
    @Suppress("RedundantSamConstructor")
    fun `interface should not be efficient`() {
        assertNotEquals(
            reduce(aString1, aString2, reducer = AString.Reducer { "" }),
            reduce(aString1, aString2, reducer = AString.Reducer { "" }),
        )
        assertNotEquals(
            reduce(aString1, aString2, reducer = AString.Reducer { "" }),
            listOf(aString1, aString2).reduce(reducer = AString.Reducer { "" }),
        )
        assertNotEquals(
            reduce(aString1, aString2, reducer = AString.Reducer { "" }),
            sequenceOf(aString1, aString2).asIterable().reduce(reducer = AString.Reducer { "" }),
        )
    }

    @Test
    fun `interface val should be efficient`() {
        val function = AString.Reducer { "" }
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            reduce(aString1, aString2, reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            listOf(aString1, aString2).reduce(reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            sequenceOf(aString1, aString2).asIterable().reduce(reducer = function),
        )
    }

    @Test
    fun `instance should be efficient`() {
        assertEquals(
            reduce(aString1, aString2, reducer = Reducer()),
            reduce(aString1, aString2, reducer = Reducer()),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = Reducer()),
            listOf(aString1, aString2).reduce(reducer = Reducer()),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = Reducer()),
            sequenceOf(aString1, aString2).asIterable().reduce(reducer = Reducer()),
        )
    }

    @Test
    fun `instance val should be efficient`() {
        val function = Reducer()
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            reduce(aString1, aString2, reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            listOf(aString1, aString2).reduce(reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            sequenceOf(aString1, aString2).asIterable().reduce(reducer = function),
        )
    }

    @Test
    fun `function reference should be efficient`() {
        assertEquals(
            reduce(aString1, aString2, reducer = ::function),
            reduce(aString1, aString2, reducer = ::function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = ::function),
            listOf(aString1, aString2).reduce(reducer = ::function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = ::function),
            sequenceOf(aString1, aString2).asIterable().reduce(reducer = ::function),
        )
    }

    @Test
    fun `function reference val should be efficient`() {
        val function = ::function
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            reduce(aString1, aString2, reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            listOf(aString1, aString2).reduce(reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            sequenceOf(aString1, aString2).asIterable().reduce(reducer = function),
        )
    }

    @Test
    fun `lambda should not be efficient`() {
        assertNotEquals(
            reduce(aString1, aString2) { it.toString() },
            reduce(aString1, aString2) { it.toString() },
        )
        assertNotEquals(
            reduce(aString1, aString2) { it.toString() },
            listOf(aString1, aString2).reduce { it.toString() },
        )
        assertNotEquals(
            reduce(aString1, aString2) { it.toString() },
            sequenceOf(aString1, aString2).asIterable().reduce { it.toString() },
        )
    }

    @Test
    fun `lambda val should be efficient`() {
        val function = { _: Iterable<CharSequence?> -> "" }
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            reduce(aString1, aString2, reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            listOf(aString1, aString2).reduce(reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            sequenceOf(aString1, aString2).asIterable().reduce(reducer = function),
        )
    }

    private fun function(values: Iterable<CharSequence?>) = values.first()

    private class Reducer : AString.Reducer {
        override fun invoke(values: Iterable<CharSequence?>) = ""
        override fun equals(other: Any?) = other is Reducer
        override fun hashCode() = 0
    }

    private fun withSpannableStringBuilder(block: () -> Unit) {
        mockkConstructor(SpannableStringBuilder::class) {
            val sb = StringBuilder()
            every {
                anyConstructed<SpannableStringBuilder>().toString()
            } answers {
                sb.toString()
            }
            every {
                anyConstructed<SpannableStringBuilder>().append(any<CharSequence>())
            } answers {
                sb.append(firstArg<CharSequence?>())
                self as SpannableStringBuilder
            }
            block()
        }
    }
}
