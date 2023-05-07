//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.compose

import android.util.Log.w
import androidx.compose.ui.text.AnnotatedString

internal typealias AddSpan = AnnotatedString.Builder.(
    span: Any,
    start: Int,
    end: Int,
) -> Unit

internal val warn: AddSpan = { span, start, end ->
    val type = span.javaClass.name
    w("AString", "dropping span with type '$type' at $start:$end")
}
