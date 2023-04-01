//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.compose

import android.graphics.Typeface.BOLD
import android.graphics.Typeface.ITALIC
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import android.os.Build.VERSION_CODES.P
import android.text.Layout.Alignment.ALIGN_CENTER
import android.text.Spanned
import android.text.style.*
import android.text.style.TtsSpan.ARG_VERBATIM
import android.text.style.TtsSpan.TYPE_VERBATIM
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontFamily.Companion.Cursive
import androidx.compose.ui.text.font.FontFamily.Companion.Default
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontFamily.Companion.Serif
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextDecoration.Companion.LineThrough
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.core.text.getSpans

@ExperimentalTextApi
internal fun spannedString(
    string: CharSequence,
) = when (string) {
    is AnnotatedString -> string
    !is Spanned -> AnnotatedString(string.toString())
    else -> buildAnnotatedString {
        append(string.toString())
        string.getSpans<Any>().forEach { span ->
            maybeAddSpan(
                span,
                string.getSpanStart(span),
                string.getSpanEnd(span),
            )
        }
    }
}

@ExperimentalTextApi
private fun AnnotatedString.Builder.maybeAddSpan(
    span: Any,
    start: Int,
    end: Int,
) = span.asTtsAnnotation()?.let {
    addTtsAnnotation(it, start, end)
} ?: span.asParagraphStyle()?.let {
    addStyle(it, start, end)
} ?: span.asSpanStyle()?.let {
    addStyle(it, start, end)
}

private fun Any.asParagraphStyle() = when (this) {
    !is android.text.style.ParagraphStyle -> null
    is AlignmentSpan -> ParagraphStyle(
        textAlign = when (alignment) {
            ALIGN_CENTER -> Center
            else -> null
        },
    )
    else -> null
}

private fun Any.asSpanStyle() = when (this) {
    !is CharacterStyle -> null
    is BackgroundColorSpan -> SpanStyle(
        background = Color(backgroundColor),
    )
    is ForegroundColorSpan -> SpanStyle(
        color = Color(foregroundColor),
    )
    is LocaleSpan -> SpanStyle(
        localeList = if (SDK_INT < N) locale?.run {
            LocaleList(Locale(toLanguageTag()))
        } else LocaleList(locales.toLanguageTags())
    )
    is ScaleXSpan -> SpanStyle(
        textGeometricTransform = TextGeometricTransform(
            scaleX = scaleX,
        )
    )
    is StrikethroughSpan -> SpanStyle(
        textDecoration = LineThrough,
    )
    is StyleSpan -> SpanStyle(
        fontWeight = Bold.takeIf {
            style and BOLD == BOLD
        },
        fontStyle = Italic.takeIf {
            style and ITALIC == ITALIC
        },
    )
    is TypefaceSpan -> SpanStyle(
        fontFamily = if (SDK_INT >= P) typeface?.let {
            FontFamily(it)
        } else {
            null
        } ?: when (family) {
            "cursive" -> Cursive
            "monospace" -> Monospace
            "sans-serif" -> SansSerif
            "serif" -> Serif
            "system-ui" -> Default
            else -> null
        }
    )
    is UnderlineSpan -> SpanStyle(
        textDecoration = Underline,
    )
    else -> null
}

private fun Any.asTtsAnnotation() = when {
    this !is TtsSpan -> null
    type != TYPE_VERBATIM -> null
    else -> VerbatimTtsAnnotation(
        args.getString(
            ARG_VERBATIM,
            "",
        )
    )
}
