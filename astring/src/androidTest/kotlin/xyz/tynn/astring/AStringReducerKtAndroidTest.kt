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

    private val aStrings = listOf(AString.Null, AppId)
    private val aStringIterable = sequenceOf(AString.Null, AppId).asIterable()

    @Test
    fun firstNonBlank_should_be_parcelable() {
        assertParcelableAStringEquality(firstNonBlank(AString.Null, AppId))
        assertParcelableAStringInvocation(firstNonBlank(AString.Null, AppId))
        assertParcelableAStringEquality(aStrings.firstNonBlank())
        assertParcelableAStringInvocation(aStrings.firstNonBlank())
        assertParcelableAStringEquality(aStringIterable.firstNonBlank())
        assertParcelableAStringInvocation(aStringIterable.firstNonBlank())
    }

    @Test
    fun firstNonEmpty_should_be_parcelable() {
        assertParcelableAStringEquality(firstNonEmpty(AString.Null, AppId))
        assertParcelableAStringInvocation(firstNonEmpty(AString.Null, AppId))
        assertParcelableAStringEquality(aStrings.firstNonEmpty())
        assertParcelableAStringInvocation(aStrings.firstNonEmpty())
        assertParcelableAStringEquality(aStringIterable.firstNonEmpty())
        assertParcelableAStringInvocation(aStringIterable.firstNonEmpty())
    }

    @Test
    fun firstNonNull_should_be_parcelable() {
        assertParcelableAStringEquality(firstNonNull(AString.Null, AppId))
        assertParcelableAStringInvocation(firstNonNull(AString.Null, AppId))
        assertParcelableAStringEquality(aStrings.firstNonNull())
        assertParcelableAStringInvocation(aStrings.firstNonNull())
        assertParcelableAStringEquality(aStringIterable.firstNonNull())
        assertParcelableAStringInvocation(aStringIterable.firstNonNull())
    }

    @Test
    fun join_should_be_parcelable() {
        assertParcelableAStringEquality(join(AString.Null, AppId, separator = "-"))
        assertParcelableAStringInvocation(join(AString.Null, AppId, separator = "-"))
        assertParcelableAStringEquality(aStrings.join(separator = "-"))
        assertParcelableAStringInvocation(aStrings.join(separator = "-"))
        assertParcelableAStringEquality(aStringIterable.join(separator = "-"))
        assertParcelableAStringInvocation(aStringIterable.join(separator = "-"))
    }

    @Test
    fun joinNonBlank_should_be_parcelable() {
        assertParcelableAStringEquality(joinNonBlank(AString.Null, AppId, separator = "-"))
        assertParcelableAStringInvocation(joinNonBlank(AString.Null, AppId, separator = "-"))
        assertParcelableAStringEquality(aStrings.joinNonBlank(separator = "-"))
        assertParcelableAStringInvocation(aStrings.joinNonBlank(separator = "-"))
        assertParcelableAStringEquality(aStringIterable.joinNonBlank(separator = "-"))
        assertParcelableAStringInvocation(aStringIterable.joinNonBlank(separator = "-"))
    }

    @Test
    fun joinNonEmpty_should_be_parcelable() {
        assertParcelableAStringEquality(joinNonEmpty(AString.Null, AppId, separator = "-"))
        assertParcelableAStringInvocation(joinNonEmpty(AString.Null, AppId, separator = "-"))
        assertParcelableAStringEquality(aStrings.joinNonEmpty(separator = "-"))
        assertParcelableAStringInvocation(aStrings.joinNonEmpty(separator = "-"))
        assertParcelableAStringEquality(aStringIterable.joinNonEmpty(separator = "-"))
        assertParcelableAStringInvocation(aStringIterable.joinNonEmpty(separator = "-"))
    }

    @Test
    fun joinNonNull_should_be_parcelable() {
        assertParcelableAStringEquality(joinNonNull(AString.Null, AppId, separator = "-"))
        assertParcelableAStringInvocation(joinNonNull(AString.Null, AppId, separator = "-"))
        assertParcelableAStringEquality(aStrings.joinNonNull(separator = "-"))
        assertParcelableAStringInvocation(aStrings.joinNonNull(separator = "-"))
        assertParcelableAStringEquality(aStringIterable.joinNonNull(separator = "-"))
        assertParcelableAStringInvocation(aStringIterable.joinNonNull(separator = "-"))
    }

    @Test
    @Suppress("RedundantSamConstructor")
    fun interface_should_not_be_efficient() {
        assertNotEquals(
            reduce(AString.Null, AppId, reducer = AString.Reducer { "" }),
            reduce(AString.Null, AppId, reducer = AString.Reducer { "" }),
        )
        assertNotEquals(
            reduce(AString.Null, AppId, reducer = AString.Reducer { "" }),
            aStrings.reduce(reducer = AString.Reducer { "" }),
        )
        assertNotEquals(
            reduce(AString.Null, AppId, reducer = AString.Reducer { "" }),
            aStringIterable.reduce(reducer = AString.Reducer { "" }),
        )
    }

    @Test
    fun interface_val_should_be_efficient() {
        val function = AString.Reducer { "" }
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            reduce(AString.Null, AppId, reducer = function),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            aStrings.reduce(reducer = function),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            aStringIterable.reduce(reducer = function),
        )
    }

    @Test
    fun instance_should_be_efficient() {
        assertEquals(
            reduce(AString.Null, AppId, reducer = Reducer()),
            reduce(AString.Null, AppId, reducer = Reducer()),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = Reducer()),
            aStrings.reduce(reducer = Reducer()),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = Reducer()),
            aStringIterable.reduce(reducer = Reducer()),
        )
    }

    @Test
    fun instance_val_should_be_efficient() {
        val function = Reducer()
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            reduce(AString.Null, AppId, reducer = function),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            aStrings.reduce(reducer = function),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            aStringIterable.reduce(reducer = function),
        )
    }

    @Test
    fun function_reference_should_be_efficient() {
        assertEquals(
            reduce(AString.Null, AppId, reducer = ::function),
            reduce(AString.Null, AppId, reducer = ::function),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = ::function),
            aStrings.reduce(reducer = ::function),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = ::function),
            aStringIterable.reduce(reducer = ::function),
        )
    }

    @Test
    fun function_reference_val_should_be_efficient() {
        val function = ::function
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            reduce(AString.Null, AppId, reducer = function),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            aStrings.reduce(reducer = function),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            aStringIterable.reduce(reducer = function),
        )
    }

    @Test
    fun lambda_should_not_be_efficient() {
        assertNotEquals(
            reduce(AString.Null, AppId) { it.toString() },
            reduce(AString.Null, AppId) { it.toString() },
        )
        assertNotEquals(
            reduce(AString.Null, AppId) { it.toString() },
            aStrings.reduce { it.toString() },
        )
        assertNotEquals(
            reduce(AString.Null, AppId) { it.toString() },
            aStringIterable.reduce { it.toString() },
        )
    }

    @Test
    fun lambda_val_should_be_efficient() {
        val function = { _: Iterable<CharSequence?> -> "" }
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            reduce(AString.Null, AppId, reducer = function),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
            aStrings.reduce(reducer = function),
        )
        assertEquals(
            reduce(AString.Null, AppId, reducer = function),
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
