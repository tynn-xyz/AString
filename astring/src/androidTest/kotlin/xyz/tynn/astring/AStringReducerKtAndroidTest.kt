//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import xyz.tynn.astring.test.AStringAssert.assertParcelableAStringEquality
import xyz.tynn.astring.test.AStringAssert.assertParcelableAStringInvocation

@InefficientAStringApi
internal class AStringReducerKtAndroidTest {

    private val aString1 = AString.Null
    private val aString2 = "+".asAString()
    private val aStrings = listOf(aString1, aString2)
    private val aStringIterable = sequenceOf(aString1, aString2).asIterable()

    @Test
    fun firstNonBlank_should_be_parcelable() {
        assertParcelableAStringEquality(firstNonBlank(aString1, aString2))
        assertParcelableAStringInvocation(firstNonBlank(aString1, aString2))
        assertParcelableAStringEquality(aStrings.firstNonBlank())
        assertParcelableAStringInvocation(aStrings.firstNonBlank())
        assertParcelableAStringEquality(aStringIterable.firstNonBlank())
        assertParcelableAStringInvocation(aStringIterable.firstNonBlank())
    }

    @Test
    fun firstNonEmpty_should_be_parcelable() {
        assertParcelableAStringEquality(firstNonEmpty(aString1, aString2))
        assertParcelableAStringInvocation(firstNonEmpty(aString1, aString2))
        assertParcelableAStringEquality(aStrings.firstNonEmpty())
        assertParcelableAStringInvocation(aStrings.firstNonEmpty())
        assertParcelableAStringEquality(aStringIterable.firstNonEmpty())
        assertParcelableAStringInvocation(aStringIterable.firstNonEmpty())
    }

    @Test
    fun firstNonNull_should_be_parcelable() {
        assertParcelableAStringEquality(firstNonNull(aString1, aString2))
        assertParcelableAStringInvocation(firstNonNull(aString1, aString2))
        assertParcelableAStringEquality(aStrings.firstNonNull())
        assertParcelableAStringInvocation(aStrings.firstNonNull())
        assertParcelableAStringEquality(aStringIterable.firstNonNull())
        assertParcelableAStringInvocation(aStringIterable.firstNonNull())
    }

    @Test
    fun join_should_be_parcelable() {
        assertParcelableAStringEquality(join(aString1, aString2, separator = "-"))
        assertParcelableAStringInvocation(join(aString1, aString2, separator = "-"))
        assertParcelableAStringEquality(aStrings.join(separator = "-"))
        assertParcelableAStringInvocation(aStrings.join(separator = "-"))
        assertParcelableAStringEquality(aStringIterable.join(separator = "-"))
        assertParcelableAStringInvocation(aStringIterable.join(separator = "-"))
    }

    @Test
    fun joinNonBlank_should_be_parcelable() {
        assertParcelableAStringEquality(joinNonBlank(aString1, aString2, separator = "-"))
        assertParcelableAStringInvocation(joinNonBlank(aString1, aString2, separator = "-"))
        assertParcelableAStringEquality(aStrings.joinNonBlank(separator = "-"))
        assertParcelableAStringInvocation(aStrings.joinNonBlank(separator = "-"))
        assertParcelableAStringEquality(aStringIterable.joinNonBlank(separator = "-"))
        assertParcelableAStringInvocation(aStringIterable.joinNonBlank(separator = "-"))
    }

    @Test
    fun joinNonEmpty_should_be_parcelable() {
        assertParcelableAStringEquality(joinNonEmpty(aString1, aString2, separator = "-"))
        assertParcelableAStringInvocation(joinNonEmpty(aString1, aString2, separator = "-"))
        assertParcelableAStringEquality(aStrings.joinNonEmpty(separator = "-"))
        assertParcelableAStringInvocation(aStrings.joinNonEmpty(separator = "-"))
        assertParcelableAStringEquality(aStringIterable.joinNonEmpty(separator = "-"))
        assertParcelableAStringInvocation(aStringIterable.joinNonEmpty(separator = "-"))
    }

    @Test
    fun joinNonNull_should_be_parcelable() {
        assertParcelableAStringEquality(joinNonNull(aString1, aString2, separator = "-"))
        assertParcelableAStringInvocation(joinNonNull(aString1, aString2, separator = "-"))
        assertParcelableAStringEquality(aStrings.joinNonNull(separator = "-"))
        assertParcelableAStringInvocation(aStrings.joinNonNull(separator = "-"))
        assertParcelableAStringEquality(aStringIterable.joinNonNull(separator = "-"))
        assertParcelableAStringInvocation(aStringIterable.joinNonNull(separator = "-"))
    }

    @Test
    @Suppress("RedundantSamConstructor")
    fun interface_should_not_be_efficient() {
        assertNotEquals(
            reduce(aString1, aString2, reducer = AString.Reducer { "" }),
            reduce(aString1, aString2, reducer = AString.Reducer { "" }),
        )
        assertNotEquals(
            reduce(aString1, aString2, reducer = AString.Reducer { "" }),
            aStrings.reduce(reducer = AString.Reducer { "" }),
        )
        assertNotEquals(
            reduce(aString1, aString2, reducer = AString.Reducer { "" }),
            aStringIterable.reduce(reducer = AString.Reducer { "" }),
        )
    }

    @Test
    fun interface_val_should_be_efficient() {
        val function = AString.Reducer { "" }
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            reduce(aString1, aString2, reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            aStrings.reduce(reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            aStringIterable.reduce(reducer = function),
        )
    }

    @Test
    fun instance_should_be_efficient() {
        assertEquals(
            reduce(aString1, aString2, reducer = Reducer()),
            reduce(aString1, aString2, reducer = Reducer()),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = Reducer()),
            aStrings.reduce(reducer = Reducer()),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = Reducer()),
            aStringIterable.reduce(reducer = Reducer()),
        )
    }

    @Test
    fun instance_val_should_be_efficient() {
        val function = Reducer()
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            reduce(aString1, aString2, reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            aStrings.reduce(reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            aStringIterable.reduce(reducer = function),
        )
    }

    @Test
    fun function_reference_should_be_efficient() {
        assertEquals(
            reduce(aString1, aString2, reducer = ::function),
            reduce(aString1, aString2, reducer = ::function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = ::function),
            aStrings.reduce(reducer = ::function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = ::function),
            aStringIterable.reduce(reducer = ::function),
        )
    }

    @Test
    fun function_reference_val_should_be_efficient() {
        val function = ::function
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            reduce(aString1, aString2, reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            aStrings.reduce(reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            aStringIterable.reduce(reducer = function),
        )
    }

    @Test
    fun lambda_should_not_be_efficient() {
        assertNotEquals(
            reduce(aString1, aString2) { it.toString() },
            reduce(aString1, aString2) { it.toString() },
        )
        assertNotEquals(
            reduce(aString1, aString2) { it.toString() },
            aStrings.reduce { it.toString() },
        )
        assertNotEquals(
            reduce(aString1, aString2) { it.toString() },
            aStringIterable.reduce { it.toString() },
        )
    }

    @Test
    fun lambda_val_should_be_efficient() {
        val function = { _: Iterable<CharSequence?> -> "" }
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            reduce(aString1, aString2, reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            aStrings.reduce(reducer = function),
        )
        assertEquals(
            reduce(aString1, aString2, reducer = function),
            aStringIterable.reduce(reducer = function),
        )
    }

    private fun function(values: Iterable<CharSequence?>) = values.first()

    private class Reducer : AString.Reducer {
        override fun invoke(values: Iterable<CharSequence?>) = ""
        override fun equals(other: Any?) = other is Reducer
        override fun hashCode() = 0
    }
}
