//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Implementation wrapping {@code null}
 */
final class NullValueWrapper implements AString {

    @NonNull
    static NullValueWrapper I = new NullValueWrapper();

    @Nullable
    @Override
    public CharSequence invoke(@Nullable Context context) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        return "AString(" + "NullValue(" + null + "))";
    }
}
