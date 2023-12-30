//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.annotation.SuppressLint;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

@SuppressLint("UnsafeOptInUsageError")
class Joiner implements AString.Reducer {

    private final String separator;
    private final Predicate predicate;

    Joiner(String separator, Predicate predicate) {
        this.separator = separator;
        this.predicate = predicate;
    }

    @Nullable
    @Override
    public CharSequence invoke(@NonNull Iterable<? extends CharSequence> values) {
        boolean isSpanned = false;
        SpannableStringBuilder sb = new SpannableStringBuilder();
        int count = 0;
        for (CharSequence value : values)
            if (predicate.test(value)) {
                isSpanned |= value instanceof Spanned;
                if (count++ > 0) sb.append(separator);
                sb.append(value == null ? "null" : value);
            }
        return isSpanned ? sb : sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joiner)) return false;
        Joiner that = (Joiner) o;
        return predicate == that.predicate && separator.equals(that.separator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(separator, predicate);
    }

    @NonNull
    @Override
    public String toString() {
        return "Join('" + separator + "'," + predicate + ')';
    }
}
