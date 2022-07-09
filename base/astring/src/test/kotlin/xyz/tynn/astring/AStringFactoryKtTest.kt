//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.res.Resources.ID_NULL
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

internal class AStringFactoryKtTest {

    @Test
    fun `nullAsAString should be the NullValueProvider singleton`() {
        assertSame(
            ValueProvider.NullValueProvider,
            nullAsAString,
        )
    }

    @Test
    fun `asAString should return NullValueProvider for null`() {
        assertSame(
            ValueProvider.NullValueProvider,
            null.asAString(),
        )
    }

    @Test
    fun `asAString should return CharSequenceWrapper for CharSequence`() {
        assertEquals(
            CharSequenceWrapper("foo"),
            "foo".asAString(),
        )
    }

    @Test
    fun `StringResource should return NullValueProvider for ID_NULL`() {
        assertSame(
            ValueProvider.NullValueProvider,
            StringResource(ID_NULL),
        )
        assertSame(
            ValueProvider.NullValueProvider,
            StringResource(ID_NULL, 1),
        )
    }

    @Test
    fun `StringResource should return StringResourceDelegate without format args`() {
        assertEquals(
            StringResourceDelegate(1, arrayOf()),
            StringResource(1),
        )
    }

    @Test
    fun `StringResource should return StringResourceDelegate with format args`() {
        assertEquals(
            StringResourceDelegate(1, arrayOf(2, "3")),
            StringResource(1, 2, "3"),
        )
    }

    @Test
    fun `TextResource should return NullValueProvider for ID_NULL`() {
        assertSame(
            ValueProvider.NullValueProvider,
            TextResource(ID_NULL),
        )
    }

    @Test
    fun `TextResource should return TextResourceDelegate`() {
        assertEquals(
            TextResourceDelegate(1),
            TextResource(1),
        )
    }

    @Test
    fun `QuantityStringResource should return NullValueProvider for ID_NULL`() {
        assertSame(
            ValueProvider.NullValueProvider,
            QuantityStringResource(ID_NULL, 1),
        )
        assertSame(
            ValueProvider.NullValueProvider,
            QuantityStringResource(ID_NULL, 1, 2),
        )
    }

    @Test
    fun `QuantityStringResource should return QuantityStringResourceDelegate without format args`() {
        assertEquals(
            QuantityStringResourceDelegate(1, 2, arrayOf()),
            QuantityStringResource(1, 2),
        )
    }

    @Test
    fun `QuantityStringResource should return QuantityStringResourceDelegate with format args`() {
        assertEquals(
            QuantityStringResourceDelegate(1, 2, arrayOf(3, "4")),
            QuantityStringResource(1, 2, 3, "4"),
        )
    }

    @Test
    fun `QuantityTextResource should return NullValueProvider for ID_NULL`() {
        assertSame(
            ValueProvider.NullValueProvider,
            QuantityTextResource(ID_NULL, 1),
        )
    }

    @Test
    fun `QuantityTextResource should return QuantityTextResourceDelegate`() {
        assertEquals(
            QuantityTextResourceDelegate(1, 2),
            QuantityTextResource(1, 2),
        )
    }

    @Test
    fun `appIdAString should be an identity`() {
        assertSame(
            appIdAString,
            appIdAString,
        )
    }

    @Test
    fun `appVersionAString should be an identity`() {
        assertSame(
            appVersionAString,
            appVersionAString,
        )
    }
}
