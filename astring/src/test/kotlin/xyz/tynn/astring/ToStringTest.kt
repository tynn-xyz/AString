//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import android.os.Parcel
import io.mockk.every
import io.mockk.mockk
import java.util.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class ToStringTest {

    private val locale = Locale.CANADA
    private val format = Format()
    private val formatArgs = arrayOf('1', 2, "3")

    @Test
    fun `invoke should return string without locale`() {
        val context = mockk<Context> {
            every { resources.configuration } returns mockk {
                @Suppress("DEPRECATION")
                locale = Locale.FRENCH
            }
        }

        assertEquals(
            "123",
            ToString.wrap(format, null, formatArgs).invoke(context),
        )
    }

    @Test
    fun `invoke should return string with locale`() {
        assertEquals(
            "123",
            ToString.wrap(format, locale, formatArgs).invoke(mockk()),
        )
    }

    @Test
    fun `invoke should invoke AString args`() {
        assertEquals(
            "123",
            ToString.wrap(format, locale, arrayOf("1".asAString(), 2, "3")).invoke(mockk()),
        )
    }

    @Test
    fun `equals should be true for matching formats and args`() {
        assertTrue {
            ToString.wrap(format, null, null) ==
                    ToString.wrap(format, locale, arrayOf())
        }
        assertTrue {
            ToString.wrap(format, Locale.GERMAN, arrayOf()) ==
                    ToString.wrap(format, locale, arrayOf())
        }
        assertTrue {
            ToString.wrap(format, locale, null) ==
                    ToString.wrap(format, locale, arrayOf())
        }
        assertTrue {
            ToString.wrap(format, null, arrayOf()) ==
                    ToString.wrap(format, null, arrayOf())
        }
        assertTrue {
            ToString.wrap(format, locale, arrayOf()) ==
                    ToString.wrap(format, locale, arrayOf())
        }
        assertTrue {
            ToString.wrap(format, null, formatArgs) ==
                    ToString.wrap(format, null, formatArgs)
        }
        assertTrue {
            ToString.wrap(format, locale, formatArgs) ==
                    ToString.wrap(format, locale, formatArgs)
        }
    }

    @Test
    fun `equals should be false for non matching locales`() {
        assertFalse {
            ToString.wrap(format, Locale.GERMAN, formatArgs) ==
                    ToString.wrap(format, locale, formatArgs)
        }
    }

    @Test
    fun `equals should be false for non matching formats`() {
        assertFalse {
            ToString.wrap(format, null, arrayOf()) ==
                    ToString.wrap(TextResource(1), null, arrayOf())
        }
        assertFalse {
            ToString.wrap(format, null, formatArgs) ==
                    ToString.wrap(TextResource(1), null, formatArgs)
        }
    }

    @Test
    fun `equals should be false for non matching format args`() {
        assertFalse {
            ToString.wrap(format, null, arrayOf(1, 2, 3)) ==
                    ToString.wrap(format, null, formatArgs)
        }
        assertFalse {
            ToString.wrap(format, locale, arrayOf(1, 2, 3)) ==
                    ToString.wrap(format, locale, formatArgs)
        }
        assertFalse {
            ToString.wrap(format, null, arrayOf()) ==
                    ToString.wrap(format, null, formatArgs)
        }
        assertFalse {
            ToString.wrap(format, locale, arrayOf()) ==
                    ToString.wrap(format, locale, formatArgs)
        }
    }

    @Test
    fun `equals should be false for non StringFormat`() {
        assertFalse {
            ToString.wrap(format, null, arrayOf()).equals("foo")
        }
        assertFalse {
            ToString.wrap(format, null, arrayOf()) == mockk<AString>()
        }
        assertFalse {
            ToString.wrap(format, null, arrayOf()).equals(mockk<Wrapper>())
        }
        assertFalse {
            ToString.wrap(format, null, arrayOf()).equals(mockk<Provider>())
        }
        assertFalse {
            ToString.wrap(format, null, arrayOf()).equals(mockk<Resource>())
        }
    }

    @Test
    fun `hashCode should return delegate to locale and format and args`() {
        assertEquals(
            40362,
            ToString.wrap(format, null, null).hashCode(),
        )
        assertEquals(
            40362,
            ToString.wrap(format, locale, null).hashCode(),
        )
        assertEquals(
            40362,
            ToString.wrap(format, null, arrayOf()).hashCode(),
        )
        assertEquals(
            40362,
            ToString.wrap(format, locale, arrayOf()).hashCode(),
        )
        assertEquals(
            117355,
            ToString.wrap(format, null, formatArgs).hashCode(),
        )
        assertEquals(
            -1299659918,
            ToString.wrap(format, locale, formatArgs).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(String($format))",
            ToString.wrap(format, null, arrayOf()).toString(),
        )
        assertEquals(
            "AString(Format($format,AString(CharSequence(1))))",
            ToString.wrap(format, null, arrayOf("1".asAString())).toString(),
        )
        assertEquals(
            "AString(Format($format,1,2,3))",
            ToString.wrap(format, null, formatArgs).toString(),
        )
        assertEquals(
            "AString(Format($locale,$format,1,2,3))",
            ToString.wrap(format, locale, formatArgs).toString(),
        )
    }

    private class Format : AString {
        override fun invoke(context: Context) = "%s%d%s"
        override fun writeToParcel(dest: Parcel, flags: Int) = Unit
        override fun equals(other: Any?) = other is Format
        override fun hashCode() = 11
    }
}
