//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import xyz.tynn.astring.Transformer.ToString
import xyz.tynn.astring.Transformer.Trim
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertSame

internal class TransformerTest {

    @Test
    fun `ToString should be named`() {
        assertEquals(
            "String",
            ToString.toString(),
        )
    }

    @Test
    fun `ToString should return null to null`() {
        assertNull(ToString(null))
    }

    @Test
    fun `ToString should return identity for String`() {
        val string = "123".repeat(2)
        assertSame(
            string,
            ToString(string),
        )
    }

    @Test
    fun `ToString should return toString of value`() {
        assertEquals(
            "123",
            ToString(StringBuilder("123")),
        )
    }

    @Test
    fun `Trim should be named`() {
        assertEquals(
            "Trim",
            Trim.toString(),
        )
    }

    @Test
    fun `Trim should return null for null`() {
        assertNull(Trim(null))
    }

    @Test
    fun `Trim should return empty for blank`() {
        assertEquals(
            "",
            Trim(" "),
        )
        assertEquals(
            "",
            Trim(StringBuilder(" ")),
        )
        assertEquals(
            "",
            Trim("\r"),
        )
        assertEquals(
            "",
            Trim(StringBuilder("\r")),
        )
        assertEquals(
            "",
            Trim("\n\t"),
        )
        assertEquals(
            "",
            Trim(StringBuilder("\n\t")),
        )
    }

    @Test
    fun `Trim should return identity for untrimmed`() {
        val string = "123"
        assertSame(string, Trim(string))
        val emptyString = ""
        assertSame(emptyString, Trim(emptyString))
        val chars = StringBuilder(string)
        assertSame(chars, Trim(chars))
        val emptyChars = StringBuilder(emptyString)
        assertSame(emptyChars, Trim(emptyChars))
    }

    @Test
    fun `Trim should trim CharSequence`() {
        assertEquals(
            "3\n4",
            Trim("\n\t 3\n4 \r\n  "),
        )
        assertEquals(
            SubStringBuilder("3\n4"),
            Trim(SubStringBuilder("\n\t 3\n4 \r\n  ")),
        )
    }

    private data class SubStringBuilder(
        private val seq: CharSequence,
    ) : CharSequence by seq {
        override fun subSequence(
            startIndex: Int,
            endIndex: Int,
        ) = SubStringBuilder(
            seq.subSequence(
                startIndex,
                endIndex,
            )
        )
    }
}
