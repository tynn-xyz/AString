//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.app.shared

import xyz.tynn.astring.*

public val aString: AString = "AString".asAString()
public val aStringPackage: AString = appIdAString
public val aStringVersion: AString = appVersionAString
public val accessibilityPaneTitle: AString = "accessibility pane title".asAString()
public val action1: AString = StringResource(R.string.astring_action, 1)
public val action2: AString = StringResource(R.string.astring_action, 2)
public val action3: AString = StringResource(R.string.astring_action, 3)
public val append: AString = StringResource(R.string.astring_append)
public val appendRange: AString = StringResource(R.string.astring_append_range, 123)
public val contentDescription: AString = "content description".asAString()
public val dialog: AString = StringResource(R.string.astring_dialog)
public val error: AString = StringResource(R.string.astring_error)
public val errorIcon: AString = StringResource(R.string.astring_error_with_icon)
public val hint: AString = QuantityStringResource(R.plurals.astring_text_hint, 0)
public val message: AString = StringResource(R.string.astring_message)
public val stateDescription: AString = "state description".asAString()
public val text: AString = TextResource(R.string.astring_text)
public val textType: AString = QuantityTextResource(R.plurals.astring_text_type, 5)
public val title: AString = StringResource(R.string.astring_title)
public val tooltipText: AString = QuantityStringResource(R.plurals.astring_tooltip_text, 2, 2)
