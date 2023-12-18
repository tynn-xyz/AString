//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static java.lang.Character.charCount;
import static java.lang.Character.codePointAt;
import static java.lang.Character.codePointBefore;
import static java.lang.Character.isWhitespace;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressLint("UnsafeOptInUsageError")
enum Transformer implements AString.Transformer {

    ToString() {
        @Nullable
        @Override
        public CharSequence invoke(@Nullable CharSequence value) {
            return value == null ? null : value.toString();
        }

        @NonNull
        @Override
        public String toString() {
            return "String";
        }
    },

    Trim() {
        @Nullable
        @Override
        public CharSequence invoke(@Nullable CharSequence value) {
            if (value == null) return null;
            if (value instanceof String) return value.toString().trim();
            final int length = value.length();
            if (length == 0) return value;
            int start = 0, end = length;
            for (int cp; start < end; start += charCount(cp))
                if (!isWhitespace(cp = codePointAt(value, start)))
                    break;
            if (start == end) return "";
            for (int cp; start < end; end -= charCount(cp))
                if (!isWhitespace(cp = codePointBefore(value, end)))
                    break;
            return start == 0 && end == length ? value : value.subSequence(start, end);
        }

        @NonNull
        @Override
        public String toString() {
            return "Trim";
        }
    },
}
