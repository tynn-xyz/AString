//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.res.Resources.ID_NULL
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

internal class AStringFactoryKtTest {

    @Test
    fun `nullAsAString should be the NullValueWrapper singleton`() {
        assertSame(
            NullValueWrapper.I,
            nullAsAString,
        )
    }

    @Test
    fun `asAString should return NullValueWrapper for null`() {
        assertSame(
            NullValueWrapper.I,
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
    fun `StringResource should return NullValueWrapper for ID_NULL`() {
        assertSame(
            NullValueWrapper.I,
            StringResource(ID_NULL),
        )
        assertSame(
            NullValueWrapper.I,
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
    fun `TextResource should return NullValueWrapper for ID_NULL`() {
        assertSame(
            NullValueWrapper.I,
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
    fun `QuantityStringResource should return NullValueWrapper for ID_NULL`() {
        assertSame(
            NullValueWrapper.I,
            QuantityStringResource(ID_NULL, 1),
        )
        assertSame(
            NullValueWrapper.I,
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
    fun `QuantityTextResource should return NullValueWrapper for ID_NULL`() {
        assertSame(
            NullValueWrapper.I,
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
}
