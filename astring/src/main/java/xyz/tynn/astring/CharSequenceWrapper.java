//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static android.text.TextUtils.CHAR_SEQUENCE_CREATOR;

import android.content.Context;
import android.os.Parcel;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * Implementation wrapping a {@link CharSequence}
 */
final class CharSequenceWrapper implements AString {

    static final CharSequenceWrapper NULL = new CharSequenceWrapper(null);

    private final CharSequence value;

    CharSequenceWrapper(@Nullable CharSequence value) {
        this.value = value;
    }

    @Nullable
    @Override
    public CharSequence invoke(@Nullable Context context) {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharSequenceWrapper that = (CharSequenceWrapper) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @NonNull
    @Override
    public String toString() {
        return "AString(" + "CharSequence(" + value + "))";
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        TextUtils.writeToParcel(value, dest, flags);
    }

    public static final Creator<CharSequenceWrapper> CREATOR = new Creator<CharSequenceWrapper>() {

        @Override
        public CharSequenceWrapper createFromParcel(Parcel source) {
            CharSequence value = CHAR_SEQUENCE_CREATOR.createFromParcel(source);
            return value == null ? NULL : new CharSequenceWrapper(value);
        }

        @Override
        public CharSequenceWrapper[] newArray(int size) {
            return new CharSequenceWrapper[size];
        }
    };
}
