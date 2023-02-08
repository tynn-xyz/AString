//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.IntDef;

@IntDef({LENGTH_LONG, LENGTH_SHORT})
public @interface ToastDuration {
}
