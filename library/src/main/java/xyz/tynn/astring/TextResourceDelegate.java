//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

final class TextResourceDelegate implements AString {

    private final int resId;

    TextResourceDelegate(@StringRes int resId) {
        this.resId = resId;
    }

    @Nullable
    @Override
    public CharSequence invoke(@NonNull Context context) {
        return context.getText(resId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextResourceDelegate that = (TextResourceDelegate) o;
        return resId == that.resId;
    }

    @Override
    public int hashCode() {
        return resId;
    }

    @NonNull
    @Override
    public String toString() {
        return "AString(" + "TextResource(" + resId + "))";
    }
}
