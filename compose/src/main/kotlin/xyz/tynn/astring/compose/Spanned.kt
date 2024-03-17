//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.compose

import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.ITALIC
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import android.os.Build.VERSION_CODES.P
import android.os.Build.VERSION_CODES.Q
import android.text.Annotation
import android.text.Layout.Alignment.ALIGN_CENTER
import android.text.Layout.Alignment.ALIGN_NORMAL
import android.text.Layout.Alignment.ALIGN_OPPOSITE
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.AlignmentSpan
import android.text.style.BackgroundColorSpan
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.LeadingMarginSpan
import android.text.style.LineHeightSpan
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
import androidx.annotation.RequiresApi
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.AnnotatedString.Builder
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.VerbatimTtsAnnotation
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontFamily.Companion.Cursive
import androidx.compose.ui.text.font.FontFamily.Companion.Default
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontFamily.Companion.Serif
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift.Companion.Subscript
import androidx.compose.ui.text.style.BaselineShift.Companion.Superscript
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.Unspecified
import androidx.compose.ui.text.style.TextDecoration.Companion.LineThrough
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.text.getSpans

@ExperimentalTextApi
internal fun CharSequence.toAnnotatedString(
    addSpan: AddSpan = warn,
) = when (val text = this) {
    is AnnotatedString -> text
    !is Spanned -> AnnotatedString(text.toString())
    else -> Builder(text.length).apply {
        append(text.toString())
        text.getSpans<Any>().forEach { span ->
            maybeAddSpan(
                span,
                text.getSpanStart(span),
                text.getSpanEnd(span),
                addSpan,
            )
        }
    }.toAnnotatedString()
}

@ExperimentalTextApi
private fun Builder.maybeAddSpan(
    span: Any,
    start: Int,
    end: Int,
    addSpan: AddSpan,
) = span.toParagraphStyle()?.let {
    addStyle(it, start, end)
} ?: span.toSpanStyle()?.let {
    addStyle(it, start, end)
} ?: span.toStringAnnotation()?.let { (tag, annotation) ->
    addStringAnnotation(tag, annotation, start, end)
} ?: span.toTtsAnnotation()?.let {
    addTtsAnnotation(it, start, end)
} ?: span.toUrlAnnotation()?.let {
    addUrlAnnotation(it, start, end)
} ?: addSpan(span, start, end)

private fun Any.toParagraphStyle() = when (this) {
    !is android.text.style.ParagraphStyle -> null

    is AlignmentSpan -> ParagraphStyle(
        textAlign = when (alignment) {
            ALIGN_CENTER -> Center
            ALIGN_NORMAL -> Unspecified
            ALIGN_OPPOSITE -> Unspecified
            else -> Unspecified
        },
    )

    is LeadingMarginSpan -> null
    is LineHeightSpan -> null

    else -> null
}

private fun Any.toSpanStyle(): SpanStyle? = when (this) {
    !is CharacterStyle -> null

    is AbsoluteSizeSpan -> null

    is BackgroundColorSpan -> SpanStyle(
        background = Color(backgroundColor),
    )

    is ForegroundColorSpan -> SpanStyle(
        color = Color(foregroundColor),
    )

    is LocaleSpan -> SpanStyle(
        localeList = if (SDK_INT < N) locale?.run {
            LocaleList(Locale(toLanguageTag()))
        } else locales.toLocaleList(),
    )

    is RelativeSizeSpan -> SpanStyle(
        fontSize = sizeChange.em,
    )

    is ScaleXSpan -> SpanStyle(
        textGeometricTransform = TextGeometricTransform(
            scaleX = scaleX,
        ),
    )

    is SubscriptSpan -> SpanStyle(
        baselineShift = Subscript,
    )

    is SuperscriptSpan -> SpanStyle(
        baselineShift = Superscript,
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

    is TextAppearanceSpan -> if (SDK_INT >= Q) SpanStyle(
        color = textColor?.run {
            Color(defaultColor)
        } ?: Color.Unspecified,
        fontSize = textSize.let {
            if (it == -1) TextUnit.Unspecified else it.sp
        },
        fontStyle = Italic.takeIf {
            textStyle and ITALIC == ITALIC
        },
        fontFamily = family.toFontFamily(typeface),
        fontWeight = textFontWeight.let {
            val bold = Bold.takeIf { textStyle and BOLD == BOLD }
            if (it == -1 || bold != null && bold.weight > it) bold
            else FontWeight(it)
        },
        fontFeatureSettings = fontFeatureSettings,
        localeList = textLocales?.toLocaleList(),
        shadow = if (shadowColor != 0 && shadowRadius != 0f) Shadow(
            color = Color(shadowColor),
            offset = Offset(shadowDx, shadowDy),
            blurRadius = shadowRadius,
        ) else null,
    ) else SpanStyle(
        color = textColor?.run {
            Color(defaultColor)
        } ?: Color.Unspecified,
        fontSize = textSize.let {
            if (it == -1) TextUnit.Unspecified else it.sp
        },
        fontStyle = Italic.takeIf {
            textStyle and ITALIC == ITALIC
        },
        fontFamily = family.toFontFamily(),
        fontWeight = Bold.takeIf {
            textStyle and BOLD == BOLD
        },
    )

    is TypefaceSpan -> SpanStyle(
        fontFamily = family.toFontFamily(
            if (SDK_INT >= P) typeface else null,
        ),
    )

    is UnderlineSpan -> SpanStyle(
        textDecoration = Underline,
    )

    else -> underlying.takeUnless {
        it == this
    }?.toSpanStyle()
}

private fun Any.toStringAnnotation() = when (this) {
    is Annotation -> key.orEmpty() to value.orEmpty()
    else -> null
}

@ExperimentalTextApi
private fun Any.toUrlAnnotation() = when (this) {
    is URLSpan -> UrlAnnotation(url)
    else -> null
}

private fun Any.toTtsAnnotation() = when {
    this !is TtsSpan -> null
    type != TYPE_VERBATIM -> null
    else -> args.getString(
        ARG_VERBATIM,
    )?.let(::VerbatimTtsAnnotation)
}

private fun String?.toFontFamily(
    typeface: Typeface? = null,
) = if (typeface == null) when (this) {
    null -> null
    Cursive.name -> Cursive
    Monospace.name -> Monospace
    SansSerif.name -> SansSerif
    Serif.name -> Serif
    else -> Default
} else FontFamily(typeface)

@RequiresApi(N)
private fun android.os.LocaleList.toLocaleList() = LocaleList(
    toLanguageTags(),
)
