//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import xyz.tynn.astring.test.AStringAssert.assertParcelableAStringEquality
import xyz.tynn.astring.test.AStringAssert.assertParcelableAStringInvocation

@InefficientAStringApi
internal class AStringTransformerKtAndroidTest {

    private val aString = AppId

    @Test
    fun transformer_should_implement_parcelable() {
        assertParcelableAStringEquality(aString.map(CharSequence?::toString))
        assertParcelableAStringInvocation(aString.map(CharSequence?::toString))
    }

    @Test
    @Suppress("RedundantSamConstructor")
    fun interface_should_not_be_efficient() {
        assertNotEquals(
            aString.map(AString.Transformer { "" }),
            aString.map(AString.Transformer { "" }),
        )
    }

    @Test
    fun interface_val_should_be_efficient() {
        val function = AString.Transformer { "" }
        assertEquals(
            aString.map(function),
            aString.map(function),
        )
    }

    @Test
    fun instance_should_be_efficient() {
        assertEquals(
            aString.map(Transformer()),
            aString.map(Transformer()),
        )
    }

    @Test
    fun instance_val_should_be_efficient() {
        val function = Transformer()
        assertEquals(
            aString.map(function),
            aString.map(function),
        )
    }

    @Test
    fun function_reference_should_be_efficient() {
        assertEquals(
            aString.map(::function),
            aString.map(::function),
        )
    }

    @Test
    fun function_reference_val_should_be_efficient() {
        val function = ::function
        assertEquals(
            aString.map(function),
            aString.map(function),
        )
    }

    @Test
    fun lambda_should_not_be_efficient() {
        assertNotEquals(
            aString.map { it },
            aString.map { it },
        )
    }

    @Test
    fun lambda_val_should_be_efficient() {
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
