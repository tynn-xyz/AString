//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.compose

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.BOLD_ITALIC
import android.graphics.Typeface.ITALIC
import android.graphics.Typeface.NORMAL
import android.graphics.drawable.Drawable
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import android.os.Build.VERSION_CODES.N
import android.os.Build.VERSION_CODES.O
import android.os.Build.VERSION_CODES.P
import android.os.Build.VERSION_CODES.Q
import android.text.Annotation
import android.text.Layout.Alignment.ALIGN_CENTER
import android.text.style.AlignmentSpan
import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle.wrap
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.LocaleSpan
import android.text.style.RelativeSizeSpan
import android.text.style.ScaleXSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.TextAppearanceSpan
import android.text.style.TtsSpan
import android.text.style.TtsSpan.ARG_VERBATIM
import android.text.style.TtsSpan.TYPE_VERBATIM
import android.text.style.TypefaceSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.Log.w
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.VerbatimTtsAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily.Companion.Cursive
import androidx.compose.ui.text.font.FontFamily.Companion.Default
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontFamily.Companion.Serif
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.LoadedFontFamily
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift.Companion.Subscript
import androidx.compose.ui.text.style.BaselineShift.Companion.Superscript
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextDecoration.Companion.LineThrough
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.os.persistableBundleOf
import androidx.core.text.buildSpannedString
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.Date
import java.util.Locale.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import android.os.LocaleList as AndroidLocaleList

@ExperimentalTextApi
@Config(sdk = [33])
@RunWith(RobolectricTestRunner::class)
internal class SpannedTest {

    private val string = "some string"

    @Test
    fun `toAnnotatedString should return string for AnnotatedString`() {
        val annotatedString = mockk<AnnotatedString>()
        assertSame(
            annotatedString,
            annotatedString.toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should return wrapped string for String`() {
        assertEquals(
            AnnotatedString(string),
            string.toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should return wrapped toString for CharSequence`() {
        val charSequence = mockk<CharSequence> {
            every {
                this@mockk.toString()
            } returns string
        }
        assertEquals(
            AnnotatedString(string),
            charSequence.toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should include UrlAnnotation`() {
        assertEquals(
            buildAnnotatedString(string) {
                addUrlAnnotation(
                    UrlAnnotation(
                        url = string,
                    ),
                    1,
                    3,
                )
            },
            buildSpannedString(
                string,
                URLSpan(string),
                1..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should include VerbatimTtsAnnotation`() {
        val verbatim = "verbatim"
        assertEquals(
            buildAnnotatedString(string) {
                addTtsAnnotation(
                    VerbatimTtsAnnotation(
                        verbatim,
                    ),
                    1,
                    2,
                )
            },
            buildSpannedString(
                string,
                TtsSpan(
                    TYPE_VERBATIM,
                    persistableBundleOf(
                        ARG_VERBATIM to verbatim
                    )
                ),
                1..2,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should include ParagraphStyle`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    ParagraphStyle(
                        textAlign = Center,
                    ),
                    1,
                    3,
                )
            },
            buildSpannedString(
                string,
                AlignmentSpan.Standard(
                    ALIGN_CENTER,
                ),
                1..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should include StringAnnotation`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStringAnnotation(
                    "key",
                    "value",
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                Annotation("key", "value"),
                2..3,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStringAnnotation(
                    "",
                    "",
                    2,
                    4,
                )
            },
            buildSpannedString(
                string,
                Annotation(null, null),
                2..4,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should warn about unknown span`() {
        mockkStatic(Log::class) {
            assertEquals(
                AnnotatedString(string),
                buildSpannedString(
                    string,
                    Date(),
                    1..3,
                ).toAnnotatedString(),
            )

            verify {
                w("AString", "dropping span with type 'java.util.Date' at 1:3")
            }
        }
    }

    @Test
    fun `toAnnotatedString should add custom span`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStringAnnotation(
                    "ArrayList",
                    "[1, 2]",
                    1,
                    3,
                )
            },
            buildSpannedString(
                string,
                arrayListOf(1, 2),
                1..3,
            ).toAnnotatedString { span, start, end ->
                addStringAnnotation(
                    span.javaClass.simpleName,
                    span.toString(),
                    start,
                    end,
                )
            },
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from BackgroundColorSpan`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        background = Color(123),
                    ),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                BackgroundColorSpan(123),
                2..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from ForegroundColorSpan`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        color = Color(123),
                    ),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                ForegroundColorSpan(123),
                2..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from LocaleSpan`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        localeList = LocaleList(
                            Locale("en"),
                            Locale("de-DE"),
                        ),
                    ),
                    2,
                    4,
                )
            },
            buildSpannedString(
                string,
                LocaleSpan(
                    AndroidLocaleList(
                        ENGLISH,
                        GERMANY,
                    )
                ),
                2..4,
            ).toAnnotatedString(),
        )
    }

    @Test
    @Config(sdk = [M, N])
    fun `toAnnotatedString should add SpanStyle from LocaleSpan with single locale`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        localeList = LocaleList(
                            Locale("en-GB"),
                        ),
                    ),
                    2,
                    4,
                )
            },
            buildSpannedString(
                string,
                LocaleSpan(UK),
                2..4,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from RelativeSizeSpan`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontSize = 2.1.em,
                    ),
                    1,
                    4,
                )
            },
            buildSpannedString(
                string,
                RelativeSizeSpan(2.1f),
                1..4,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from ScaleXSpan`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        textGeometricTransform = TextGeometricTransform(
                            scaleX = 1.2f,
                        ),
                    ),
                    0,
                    3,
                )
            },
            buildSpannedString(
                string,
                ScaleXSpan(1.2f),
                0..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from SubscriptSpan`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        baselineShift = Subscript,
                    ),
                    1,
                    3,
                )
            },
            buildSpannedString(
                string,
                SubscriptSpan(),
                1..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from SuperscriptSpan`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        baselineShift = Superscript,
                    ),
                    1,
                    3,
                )
            },
            buildSpannedString(
                string,
                SuperscriptSpan(),
                1..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from StrikethroughSpan`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        textDecoration = LineThrough,
                    ),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                StrikethroughSpan(),
                2..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from StyleSpan`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontWeight = Bold,
                    ),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                StyleSpan(BOLD),
                2..3,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontWeight = Bold,
                        fontStyle = Italic,
                    ),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                StyleSpan(BOLD_ITALIC),
                2..3,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontStyle = Italic,
                    ),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                StyleSpan(ITALIC),
                2..3,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                StyleSpan(NORMAL),
                2..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from TextAppearanceSpan`() {
        val typeface = mockk<Typeface>()
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        color = Color(112),
                        fontSize = 12.sp,
                        fontWeight = Bold,
                        fontStyle = Italic,
                        fontFamily = Monospace,
                    ),
                    2,
                    4,
                )
            },
            buildSpannedString(
                string,
                TextAppearanceSpan(
                    "monospace",
                    BOLD_ITALIC,
                    12,
                    ColorStateList.valueOf(
                        112,
                    ),
                    null,
                ),
                2..4,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontWeight = FontWeight(800),
                        fontFamily = mockk {
                            every {
                                this@mockk == match<LoadedFontFamily> {
                                    it.typeface.getProperty("typeface") == typeface
                                }
                            } returns true
                        },
                        fontFeatureSettings = "fontFeatureSettings",
                        localeList = LocaleList(
                            Locale("en-US"),
                            Locale("fr-FR"),
                        ),
                    ),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                mockk<TextAppearanceSpan>(relaxed = true) {
                    every {
                        this@mockk.typeface
                    } returns typeface
                    every {
                        fontFeatureSettings
                    } returns "fontFeatureSettings"
                    every {
                        textLocales
                    } returns AndroidLocaleList(
                        US,
                        FRANCE,
                    )
                    every { textFontWeight } returns 800
                    every { textStyle } returns BOLD
                    every { shadowDx } returns 1f
                    every { shadowDy } returns 2f
                    every { textColor } returns null
                    every { textSize } returns -1
                },
                2..3,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontWeight = Bold,
                    ),
                    2,
                    5,
                )
            },
            buildSpannedString(
                string,
                mockk<TextAppearanceSpan>(relaxed = true) {
                    every { textFontWeight } returns 600
                    every { textStyle } returns BOLD
                    every { family } returns null
                    every { this@mockk.typeface } returns null
                    every { fontFeatureSettings } returns null
                    every { textColor } returns null
                    every { textLocales } returns null
                    every { textSize } returns -1
                },
                2..5,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        shadow = Shadow(
                            Color(42),
                            Offset(1f, 2f),
                            blurRadius = 3f,
                        ),
                    ),
                    2,
                    5,
                )
            },
            buildSpannedString(
                string,
                mockk<TextAppearanceSpan>(relaxed = true) {
                    every { shadowColor } returns 42
                    every { shadowDx } returns 1f
                    every { shadowDy } returns 2f
                    every { shadowRadius } returns 3f
                    every { family } returns null
                    every { this@mockk.typeface } returns null
                    every { fontFeatureSettings } returns null
                    every { textColor } returns null
                    every { textLocales } returns null
                    every { textFontWeight } returns -1
                    every { textSize } returns -1
                },
                2..5,
            ).toAnnotatedString(),
        )
    }

    @Test
    @Config(sdk = [P, Q])
    fun `toAnnotatedString should add SpanStyle from TextAppearanceSpan with family`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        color = Color(112),
                        fontSize = 12.sp,
                        fontWeight = Bold,
                        fontStyle = Italic,
                        fontFamily = Serif,
                    ),
                    2,
                    4,
                )
            },
            buildSpannedString(
                string,
                TextAppearanceSpan(
                    "serif",
                    BOLD_ITALIC,
                    12,
                    ColorStateList.valueOf(
                        112,
                    ),
                    null,
                ),
                2..4,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                mockk<TextAppearanceSpan>(relaxed = true) {
                    every { family } returns null
                    every { textColor } returns null
                    every { textSize } returns -1
                    if (SDK_INT >= Q) {
                        every { fontFeatureSettings } returns null
                        every { textLocales } returns null
                        every { textFontWeight } returns -1
                        every { typeface } returns null
                    }
                },
                2..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from TypefaceSpan`() {
        val typeface = mockk<Typeface>()
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontFamily = mockk {
                            every {
                                this@mockk == match<LoadedFontFamily> {
                                    it.typeface.getProperty("typeface") == typeface
                                }
                            } returns true
                        },
                    ),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                TypefaceSpan(typeface),
                2..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    @Config(sdk = [O, P])
    fun `toAnnotatedString should add SpanStyle from TypefaceSpan with font family`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(),
                    1,
                    2,
                )
            },
            buildSpannedString(
                string,
                TypefaceSpan(null),
                1..2,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontFamily = Cursive,
                    ),
                    1,
                    3,
                )
            },
            buildSpannedString(
                string,
                TypefaceSpan("cursive"),
                1..3,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontFamily = Monospace,
                    ),
                    1,
                    4,
                )
            },
            buildSpannedString(
                string,
                TypefaceSpan("monospace"),
                1..4,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontFamily = SansSerif,
                    ),
                    1,
                    5,
                )
            },
            buildSpannedString(
                string,
                TypefaceSpan("sans-serif"),
                1..5,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontFamily = Serif,
                    ),
                    1,
                    6,
                )
            },
            buildSpannedString(
                string,
                TypefaceSpan("serif"),
                1..6,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontFamily = Default,
                    ),
                    1,
                    7,
                )
            },
            buildSpannedString(
                string,
                TypefaceSpan("fantasy"),
                1..7,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontFamily = Default,
                    ),
                    1,
                    8,
                )
            },
            buildSpannedString(
                string,
                TypefaceSpan("system-ui"),
                1..8,
            ).toAnnotatedString(),
        )
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        fontFamily = Default,
                    ),
                    1,
                    9,
                )
            },
            buildSpannedString(
                string,
                TypefaceSpan("family"),
                1..9,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from UnderlineSpan`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        textDecoration = Underline,
                    ),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                UnderlineSpan(),
                2..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should add SpanStyle from wrapped CharacterStyle`() {
        assertEquals(
            buildAnnotatedString(string) {
                addStyle(
                    SpanStyle(
                        textDecoration = Underline,
                    ),
                    2,
                    3,
                )
            },
            buildSpannedString(
                string,
                wrap(UnderlineSpan()),
                2..3,
            ).toAnnotatedString(),
        )
    }

    @Test
    fun `toAnnotatedString should skip unknown wrapped CharacterStyle`() {
        assertEquals(
            AnnotatedString(string),
            buildSpannedString(
                string,
                wrap(ImageSpan(mockk<Drawable>())),
                2..3,
            ).toAnnotatedString(),
        )
    }

    @Suppress("SameParameterValue")
    private inline fun buildAnnotatedString(
        value: String,
        builder: (AnnotatedString.Builder).() -> Unit
    ) = buildAnnotatedString {
        append(value)
        builder()
    }

    @Suppress("SameParameterValue")
    private fun buildSpannedString(
        value: String,
        span: Any,
        range: IntRange,
    ) = buildSpannedString {
        append(value)
        setSpan(
            span,
            range.first,
            range.last,
            0,
        )
    }
}
