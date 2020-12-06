//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.example.common

import xyz.tynn.astring.*

val aString = "AString".asAString()
val accessibilityPaneTitle = "accessibility pane title".asAString()
val append = StringResource(R.string.astring_append)
val appendRange = StringResource(R.string.astring_append_range, 123)
val contentDescription = "content description".asAString()
val error = StringResource(R.string.astring_error)
val errorIcon = StringResource(R.string.astring_error_with_icon)
val hint = QuantityStringResource(R.plurals.astring_text_hint, 0)
val stateDescription = "state description".asAString()
val text = TextResource(R.string.astring_text)
val textType = QuantityTextResource(R.plurals.astring_text_type, 5)
val tooltipText = QuantityStringResource(R.plurals.astring_tooltip_text, 2, 2)
