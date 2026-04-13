//  Copyright 2024-2026 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.integration.compose

import android.graphics.Typeface.BOLD
import android.graphics.Typeface.ITALIC
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.compose.runtime.Composable
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.android.tools.screenshot.PreviewTest
import xyz.tynn.astring.asAString

private val aString = buildSpannedString {
    inSpans(StyleSpan(BOLD)) {
        inSpans(UnderlineSpan()) {
            append("ASt")
        }
        append("ring")
    }
    append(' ')
    inSpans(URLSpan("https://google.com")) {
        inSpans(StyleSpan(ITALIC)) {
            inSpans(UnderlineSpan()) {
                append("val")
            }
            append("ue")
        }
    }
}.asAString()

@[PreviewTest Composable PreviewConfig]
fun ComposeWidgetPreviewTest() {
    ComposeWidget(aString)
}
