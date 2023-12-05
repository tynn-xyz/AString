//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.res.Resources.ID_NULL
import io.mockk.mockk
import java.util.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

internal class AStringFactoryKtTest {

    @Test
    fun `Null should be the NULL singleton`() {
        assertSame(
            Wrapper.wrap(null),
            AString.Null,
        )
        assertSame(
            AString.Null,
            AString.Null,
        )
    }

    @Test
    fun `asAString should return NULL for null`() {
        assertSame(
            AString.Null,
            null.asAString(),
        )
    }

    @Test
    fun `AString should return NULL for null`() {
        assertSame(
            AString.Null,
            AString(null),
        )
        assertSame(
            AString.Null,
            AString(nothing = null),
        )
    }

    @Test
    fun `asAString should return NULL for null AString`() {
        val aString: AString? = null
        assertSame(
            AString.Null,
            aString.asAString(),
        )
    }

    @Test
    fun `asAString should be an identity for AString`() {
        val aString: AString = mockk()
        assertSame(
            aString,
            aString.asAString(),
        )
    }

    @Test
    fun `AString should return NULL for null AString`() {
        val aString: AString? = null
        assertSame(
            AString.Null,
            AString(aString),
        )
        assertSame(
            AString.Null,
            AString(aString = null),
        )
    }

    @Test
    fun `AString should be an identity for AString`() {
        val aString: AString = mockk()
        assertSame(
            aString,
            AString(aString),
        )
    }

    @Test
    fun `asAString should return Null for null CharSequence`() {
        val charSequence: CharSequence? = null
        assertEquals(
            AString.Null,
            charSequence.asAString(),
        )
    }

    @Test
    fun `asAString should return Wrapper for CharSequence`() {
        assertEquals(
            Wrapper.wrap("foo"),
            "foo".asAString(),
        )
    }

    @Test
    fun `AString should return NULL for null CharSequence`() {
        val charSequence: CharSequence? = null
        assertEquals(
            AString.Null,
            AString(charSequence),
        )
        assertEquals(
            AString.Null,
            AString(value = null),
        )
    }

    @Test
    fun `AString should return Wrapper for CharSequence`() {
        assertEquals(
            Wrapper.wrap("foo"),
            AString("foo"),
        )
    }

    @Test
    fun `StringResource should return NULL for ID_NULL`() {
        assertSame(
            AString.Null,
            StringResource(ID_NULL),
        )
        assertSame(
            AString.Null,
            StringResource(ID_NULL, 1),
        )
    }

    @Test
    fun `StringResource should return ToString Resource without format args`() {
        assertEquals(
            ToString.wrap(
                Resource.wrap(1, null),
                null,
                arrayOf(),
            ),
            StringResource(1),
        )
    }

    @Test
    fun `StringResource should return ToString Resource with format args`() {
        assertEquals(
            ToString.wrap(
                Resource.wrap(1, null),
                null,
                arrayOf(2, "3"),
            ),
            StringResource(1, 2, "3"),
        )
    }

    @Test
    fun `TextResource should return NULL for ID_NULL`() {
        assertSame(
            AString.Null,
            TextResource(ID_NULL),
        )
    }

    @Test
    fun `TextResource should return Resource`() {
        assertEquals(
            Resource.wrap(1, null),
            TextResource(1),
        )
    }

    @Test
    fun `QuantityStringResource should return NULL for ID_NULL`() {
        assertSame(
            AString.Null,
            QuantityStringResource(ID_NULL, 1),
        )
        assertSame(
            AString.Null,
            QuantityStringResource(ID_NULL, 1, 2),
        )
    }

    @Test
    fun `QuantityStringResource should return ToString Resource quantity without format args`() {
        assertEquals(
            ToString.wrap(
                Resource.wrap(1, 2),
                null,
                arrayOf(),
            ),
            QuantityStringResource(1, 2),
        )
    }

    @Test
    fun `QuantityStringResource should return ToString Resource quantity with format args`() {
        assertEquals(
            ToString.wrap(
                Resource.wrap(1, 2),
                null,
                arrayOf(3, "4"),
            ),
            QuantityStringResource(1, 2, 3, "4"),
        )
    }

    @Test
    fun `QuantityTextResource should return NULL for ID_NULL`() {
        assertSame(
            AString.Null,
            QuantityTextResource(ID_NULL, 1),
        )
    }

    @Test
    fun `QuantityTextResource should return Resource quantity`() {
        assertEquals(
            Resource.wrap(1, 2),
            QuantityTextResource(1, 2),
        )
    }

    @Test
    fun `format should return ToString`() {
        assertEquals(
            ToString.wrap(TextResource(1), null, arrayOf(1, "2")),
            TextResource(1).format(1, "2"),
        )
        assertEquals(
            ToString.wrap(TextResource(1), Locale.GERMAN, arrayOf(1, "2")),
            TextResource(1).format(Locale.GERMAN, 1, "2"),
        )
        assertEquals(
            ToString.wrap(TextResource(1), null, arrayOf(1, "2")),
            TextResource(1).format(locale = null, 1, "2"),
        )
    }

    @Test
    fun `format should return ToString with format args for ToString`() {
        assertEquals(
            ToString.wrap(TextResource(1), null, arrayOf(1, "2")),
            TextResource(1).mapToString().format(1, "2"),
        )
        assertEquals(
            ToString.wrap(TextResource(1), Locale.GERMAN, arrayOf(1, "2")),
            TextResource(1).mapToString().format(Locale.GERMAN, 1, "2"),
        )
        assertEquals(
            ToString.wrap(TextResource(1), null, arrayOf(1, "2")),
            TextResource(1).mapToString().format(locale = null, 1, "2"),
        )
    }

    @Test
    fun `format should return ToString ignoring empty format args`() {
        assertEquals(
            ToString.wrap(TextResource(1), null, null),
            TextResource(1).format(),
        )
        assertEquals(
            ToString.wrap(TextResource(1), null, null),
            TextResource(1).format(Locale.GERMAN),
        )
        assertEquals(
            ToString.wrap(TextResource(1), null, null),
            TextResource(1).format(locale = null),
        )
    }

    @Test
    fun `mapToString should return unformatted ToString`() {
        assertEquals(
            ToString.wrap(TextResource(1), null, null),
            TextResource(1).mapToString(),
        )
    }

    @Test
    fun `mapToString should return identity for ToString`() {
        val format = "format".asAString().format(1)
        assertSame(
            format,
            format.mapToString(),
        )
        val toString = TextResource(1).mapToString()
        assertSame(
            toString,
            toString.mapToString(),
        )
    }

    @Test
    fun `mapToString should return NULL for null`() {
        assertSame(
            AString.Null,
            null.mapToString(),
        )
        assertSame(
            AString.Null,
            null.asAString().mapToString(),
        )
    }

    @Test
    fun `mapToString should return new Wrapper`() {
        assertEquals(
            "format".asAString(),
            StringBuilder("format").asAString().mapToString(),
        )
    }

    @Test
    fun `mapToString should return identity for Wrapper of String`() {
        val wrapper = "format".asAString()
        assertSame(
            wrapper,
            wrapper.mapToString(),
        )
    }

    @Test
    fun `mapToString should return identity for Provider`() {
        Provider.entries.forEach {
            assertSame(it, it.mapToString())
        }
    }

    @Test
    fun `appIdAString should be an identity`() {
        assertSame(
            AppId,
            AppId,
        )
    }

    @Test
    fun `appVersionAString should be an identity`() {
        assertSame(
            AppVersion,
            AppVersion,
        )
    }
}
