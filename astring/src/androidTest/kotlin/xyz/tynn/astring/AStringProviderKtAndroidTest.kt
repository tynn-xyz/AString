//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import xyz.tynn.astring.test.AStringAssert.assertParcelableAStringEquality
import xyz.tynn.astring.test.AStringAssert.assertParcelableAStringInvocation

@InefficientAStringApi
internal class AStringProviderKtAndroidTest {

    @Test
    fun delegate_should_implement_parcelable_method() {
        assertParcelableAStringEquality(AString(Context::getPackageName))
        assertParcelableAStringInvocation(AString(Context::getPackageName))
    }

    @Test
    @Suppress("RedundantSamConstructor")
    fun interface_should_not_be_efficient() {
        assertNotEquals(
            AString(AStringProvider { "" }),
            AString(AStringProvider { "" }),
        )
    }

    @Test
    fun interface_val_should_be_efficient() {
        val function = AStringProvider { "" }
        assertEquals(
            AString(function),
            AString(function),
        )
    }

    @Test
    fun instance_should_be_efficient() {
        assertEquals(
            AString(Provider()),
            AString(Provider()),
        )
    }

    @Test
    fun instance_val_should_be_efficient() {
        val function = Provider()
        assertEquals(
            AString(function),
            AString(function),
        )
    }

    @Test
    fun function_reference_should_be_efficient() {
        assertEquals(
            AString(::function),
            AString(::function),
        )
    }

    @Test
    fun function_reference_val_should_be_efficient() {
        val function = ::function
        assertEquals(
            AString(function),
            AString(function),
        )
    }

    @Test
    fun lambda_should_not_be_efficient() {
        assertNotEquals(
            AString { it.toString() },
            AString { it.toString() },
        )
    }

    @Test
    fun lambda_val_should_be_efficient() {
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
