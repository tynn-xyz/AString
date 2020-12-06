//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface AString {
    @Nullable
    CharSequence invoke(@NonNull Context context);
}

