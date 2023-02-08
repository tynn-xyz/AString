//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static android.content.DialogInterface.BUTTON_POSITIVE;

import androidx.annotation.IntDef;

@IntDef({BUTTON_POSITIVE, BUTTON_NEGATIVE, BUTTON_NEUTRAL})
public @interface DialogInterfaceButton {
}
