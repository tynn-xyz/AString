//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static java.lang.Character.charCount;
import static java.lang.Character.codePointAt;
import static java.lang.Character.isWhitespace;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

@SuppressLint("UnsafeOptInUsageError")
enum Predicate implements AString.Reducer {

    AnyValue {
        @Override
        boolean test(CharSequence value) {
            return true;
        }

        @Override
        @androidx.annotation.NonNull
        public String toString() {
            return "Any";
        }
    },

    NonBlank {
        @Override
        boolean test(CharSequence value) {
            if (value != null) for (int i = 0, cp; i < value.length(); i += charCount(cp))
                if (!isWhitespace(cp = codePointAt(value, i)))
                    return true;
            return false;
        }

        @Override
        @androidx.annotation.NonNull
        public String toString() {
            return "NonBlank";
        }
    },

    NonEmpty {
        @Override
        boolean test(CharSequence value) {
            return value != null && value.length() > 0;
        }

        @Override
        @androidx.annotation.NonNull
        public String toString() {
            return "NonEmpty";
        }
    },

    NonNull {
        @Override
        boolean test(CharSequence value) {
            return value != null;
        }

        @Override
        @androidx.annotation.NonNull
        public String toString() {
            return "NonNull";
        }
    },

    ;

    abstract boolean test(CharSequence value);

    @Nullable
    @Override
    public CharSequence invoke(@androidx.annotation.NonNull Iterable<? extends CharSequence> values) {
        for (CharSequence value : values) if (test(value)) return value;
        return null;
    }
}
