//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.example.base

import xyz.tynn.astring.*

val aString = "AString".asAString()
val accessibilityPaneTitle = "accessibility pane title".asAString()
val action1 = StringResource(R.string.astring_action, 1)
val action2 = StringResource(R.string.astring_action, 2)
val action3 = StringResource(R.string.astring_action, 3)
val append = StringResource(R.string.astring_append)
val appendRange = StringResource(R.string.astring_append_range, 123)
val contentDescription = "content description".asAString()
val dialog = StringResource(R.string.astring_dialog)
val error = StringResource(R.string.astring_error)
val errorIcon = StringResource(R.string.astring_error_with_icon)
val hint = QuantityStringResource(R.plurals.astring_text_hint, 0)
val message = StringResource(R.string.astring_message)
val stateDescription = "state description".asAString()
val text = TextResource(R.string.astring_text)
val textType = QuantityTextResource(R.plurals.astring_text_type, 5)
val title = StringResource(R.string.astring_title)
val tooltipText = QuantityStringResource(R.plurals.astring_tooltip_text, 2, 2)
