//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import android.content.res.Resources.ID_NULL
import io.mockk.mockk
import java.util.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

@InefficientAStringApi
internal class AStringFactoryKtTest {

    @Test
    fun `AString should return provided string`() {
        val context = mockk<Context>()
        val string = mockk<CharSequence>()
        assertEquals(
            string,
            context.aString(
                AString { string },
            ),
        )
    }

    @Test
    fun `map should transform the original value`() {
        val context = mockk<Context>()
        assertEquals(
            "value-value",
            context.aString(
                AString { "value" }.map {
                    "$it-$it"
                },
            ),
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
        val aString = mockk<AString>()
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
        val aString = mockk<AString>()
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
    fun `StringResource should return Format Resource without format args`() {
        assertEquals(
            Format.wrap(
                Resource.wrap(1, null),
                null,
                arrayOf(),
            ),
            StringResource(1),
        )
    }

    @Test
    fun `StringResource should return Format Resource with format args`() {
        assertEquals(
            Format.wrap(
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
    fun `QuantityStringResource should return Format Resource quantity without format args`() {
        assertEquals(
            Format.wrap(
                Resource.wrap(1, 2),
                null,
                arrayOf(),
            ),
            QuantityStringResource(1, 2),
        )
    }

    @Test
    fun `QuantityStringResource should return Format Resource quantity with format args`() {
        assertEquals(
            Format.wrap(
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
    fun `format should return Format`() {
        assertEquals(
            Format.wrap(TextResource(1), null, arrayOf(1, "2")),
            TextResource(1).format(1, "2"),
        )
        assertEquals(
            Format.wrap(TextResource(1), Locale.GERMAN, arrayOf(1, "2")),
            TextResource(1).format(Locale.GERMAN, 1, "2"),
        )
        assertEquals(
            Format.wrap(TextResource(1), null, arrayOf(1, "2")),
            TextResource(1).format(locale = null, 1, "2"),
        )
    }

    @Test
    fun `format should return Format with format args for Format`() {
        assertEquals(
            Format.wrap(TextResource(1), null, arrayOf(1, "2")),
            TextResource(1).mapToString().format(1, "2"),
        )
        assertEquals(
            Format.wrap(TextResource(1), Locale.GERMAN, arrayOf(1, "2")),
            TextResource(1).mapToString().format(Locale.GERMAN, 1, "2"),
        )
        assertEquals(
            Format.wrap(TextResource(1), null, arrayOf(1, "2")),
            TextResource(1).mapToString().format(locale = null, 1, "2"),
        )
    }

    @Test
    fun `format should return Format ignoring empty format args`() {
        assertEquals(
            Format.wrap(TextResource(1), null, null),
            TextResource(1).format(),
        )
        assertEquals(
            Format.wrap(TextResource(1), null, null),
            TextResource(1).format(Locale.GERMAN),
        )
        assertEquals(
            Format.wrap(TextResource(1), null, null),
            TextResource(1).format(locale = null),
        )
    }

    @Test
    fun `mapToString should return unformatted Format`() {
        assertEquals(
            Format.wrap(TextResource(1), null, null),
            TextResource(1).mapToString(),
        )
    }

    @Test
    fun `mapToString should return identity for Format`() {
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
    fun `mapToString should return mapped Wrapper`() {
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
    fun `nullIfBlank should return Null for null`() {
        assertSame(
            AString.Null,
            null.nullIfBlank(),
        )
        assertSame(
            AString.Null,
            null.asAString().nullIfBlank(),
        )
    }

    @Test
    fun `nullIfBlank should return Null for empty Wrapper`() {
        assertSame(
            AString.Null,
            " ".asAString().nullIfBlank(),
        )
    }

    @Test
    fun `nullIfBlank should return identity for Wrapper`() {
        val aString = "wrapper".asAString()
        assertSame(
            aString,
            aString.nullIfBlank(),
        )
    }

    @Test
    fun `nullIfBlank should return Delegate`() {
        val aString = mockk<AString>()
        assertEquals(
            Delegate.wrap(
                aString,
                Predicate.NonBlank,
            ),
            aString.nullIfBlank(),
        )
    }

    @Test
    fun `nullIfEmpty should return Null for null`() {
        assertSame(
            AString.Null,
            null.nullIfEmpty(),
        )
        assertSame(
            AString.Null,
            null.asAString().nullIfEmpty(),
        )
    }

    @Test
    fun `nullIfEmpty should return Null for empty Wrapper`() {
        assertSame(
            AString.Null,
            "".asAString().nullIfEmpty(),
        )
    }

    @Test
    fun `nullIfEmpty should return identity for Wrapper`() {
        val aString = "wrapper".asAString()
        assertSame(
            aString,
            aString.nullIfEmpty(),
        )
    }

    @Test
    fun `nullIfEmpty should return Delegate`() {
        val aString = mockk<AString>()
        assertEquals(
            Delegate.wrap(
                aString,
                Predicate.NonEmpty,
            ),
            aString.nullIfEmpty(),
        )
    }

    @Test
    fun `emptyIfNull should return empty Wrapper for null`() {
        assertEquals(
            "".asAString(),
            null.emptyIfNull(),
        )
    }

    @Test
    fun `emptyIfNull should return empty Wrapper for Null`() {
        assertEquals(
            "".asAString(),
            null.asAString().emptyIfNull(),
        )
    }

    @Test
    fun `emptyIfNull should return identity for Wrapper`() {
        val aString = "wrapper".asAString()
        assertSame(
            aString,
            aString.emptyIfNull(),
        )
    }

    @Test
    fun `emptyIfNull should return Delegate`() {
        val aString = mockk<AString>()
        assertEquals(
            Delegate.wrap(
                aString,
                Predicate.NonNull,
            ),
            aString.emptyIfNull(),
        )
    }

    @Test
    fun `trim should return Null for null`() {
        assertEquals(
            AString.Null,
            null.trim(),
        )
        assertEquals(
            AString.Null,
            null.asAString().trim(),
        )
    }

    @Test
    fun `trim should return trimmed Wrapper`() {
        assertEquals(
            "wrapper".asAString(),
            " wrapper ".asAString().trim(),
        )
    }

    @Test
    fun `trim should return Delegate`() {
        val aString = mockk<AString>()
        assertEquals(
            Delegate.wrap(
                aString,
                Transformer.Trim,
            ),
            aString.trim(),
        )
    }

    @Test
    fun `appIdAString should be an identity`() {
        assertSame(AppId, AppId)
    }

    @Test
    fun `appVersionAString should be an identity`() {
        assertSame(AppVersion, AppVersion)
    }
}
