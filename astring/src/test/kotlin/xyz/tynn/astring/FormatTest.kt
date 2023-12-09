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

internal class FormatTest {

    private val locale = Locale.CANADA
    private val format = FormatAString()
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
            Format.wrap(format, null, formatArgs).invoke(context),
        )
    }

    @Test
    fun `invoke should return string with locale`() {
        assertEquals(
            "123",
            Format.wrap(format, locale, formatArgs).invoke(mockk()),
        )
    }

    @Test
    fun `invoke should invoke AString args`() {
        assertEquals(
            "123",
            Format.wrap(format, locale, arrayOf("1".asAString(), 2, "3"))
                .invoke(mockk()),
        )
    }

    @Test
    fun `equals should be true for matching formats and args`() {
        assertTrue {
            Format.wrap(format, null, null) ==
                    Format.wrap(format, locale, arrayOf())
        }
        assertTrue {
            Format.wrap(format, Locale.GERMAN, arrayOf()) ==
                    Format.wrap(format, locale, arrayOf())
        }
        assertTrue {
            Format.wrap(format, locale, null) ==
                    Format.wrap(format, locale, arrayOf())
        }
        assertTrue {
            Format.wrap(format, null, arrayOf()) ==
                    Format.wrap(format, null, arrayOf())
        }
        assertTrue {
            Format.wrap(format, locale, arrayOf()) ==
                    Format.wrap(format, locale, arrayOf())
        }
        assertTrue {
            Format.wrap(format, null, formatArgs) ==
                    Format.wrap(format, null, formatArgs)
        }
        assertTrue {
            Format.wrap(format, locale, formatArgs) ==
                    Format.wrap(format, locale, formatArgs)
        }
    }

    @Test
    fun `equals should be false for non matching locales`() {
        assertFalse {
            Format.wrap(format, Locale.GERMAN, formatArgs) ==
                    Format.wrap(format, locale, formatArgs)
        }
    }

    @Test
    fun `equals should be false for non matching formats`() {
        assertFalse {
            Format.wrap(format, null, arrayOf()) ==
                    Format.wrap(TextResource(1), null, arrayOf())
        }
        assertFalse {
            Format.wrap(format, null, formatArgs) ==
                    Format.wrap(TextResource(1), null, formatArgs)
        }
    }

    @Test
    fun `equals should be false for non matching format args`() {
        assertFalse {
            Format.wrap(format, null, arrayOf(1, 2, 3)) ==
                    Format.wrap(format, null, formatArgs)
        }
        assertFalse {
            Format.wrap(format, locale, arrayOf(1, 2, 3)) ==
                    Format.wrap(format, locale, formatArgs)
        }
        assertFalse {
            Format.wrap(format, null, arrayOf()) ==
                    Format.wrap(format, null, formatArgs)
        }
        assertFalse {
            Format.wrap(format, locale, arrayOf()) ==
                    Format.wrap(format, locale, formatArgs)
        }
    }

    @Test
    fun `equals should be false for non StringFormat`() {
        assertFalse {
            Format.wrap(format, null, arrayOf()).equals("foo")
        }
        assertFalse {
            Format.wrap(format, null, arrayOf()) == mockk<AString>()
        }
        assertFalse {
            Format.wrap(format, null, arrayOf()).equals(mockk<Wrapper>())
        }
        assertFalse {
            Format.wrap(format, null, arrayOf()).equals(mockk<Provider>())
        }
        assertFalse {
            Format.wrap(format, null, arrayOf()).equals(mockk<Resource>())
        }
    }

    @Test
    fun `hashCode should return delegate to locale and format and args`() {
        assertEquals(
            40362,
            Format.wrap(format, null, null).hashCode(),
        )
        assertEquals(
            40362,
            Format.wrap(format, locale, null).hashCode(),
        )
        assertEquals(
            40362,
            Format.wrap(format, null, arrayOf()).hashCode(),
        )
        assertEquals(
            40362,
            Format.wrap(format, locale, arrayOf()).hashCode(),
        )
        assertEquals(
            117355,
            Format.wrap(format, null, formatArgs).hashCode(),
        )
        assertEquals(
            -1299659918,
            Format.wrap(format, locale, formatArgs).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(String($format))",
            Format.wrap(format, null, arrayOf()).toString(),
        )
        assertEquals(
            "AString(Format($format,AString(CharSequence(1))))",
            Format.wrap(format, null, arrayOf("1".asAString())).toString(),
        )
        assertEquals(
            "AString(Format($format,1,2,3))",
            Format.wrap(format, null, formatArgs).toString(),
        )
        assertEquals(
            "AString(Format($locale,$format,1,2,3))",
            Format.wrap(format, locale, formatArgs).toString(),
        )
    }

    private class FormatAString : AString {
        override fun invoke(context: Context) = "%s%d%s"
        override fun writeToParcel(dest: Parcel, flags: Int) = Unit
        override fun equals(other: Any?) = other is FormatAString
        override fun hashCode() = 11
    }
}
