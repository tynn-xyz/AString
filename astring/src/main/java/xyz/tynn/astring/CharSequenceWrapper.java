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

    private final CharSequence string;

    CharSequenceWrapper(@NonNull CharSequence string) {
        this.string = string;
    }

    @NonNull
    @Override
    public CharSequence invoke(@Nullable Context context) {
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharSequenceWrapper that = (CharSequenceWrapper) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return string.hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        return "AString(" + "CharSequence(" + string + "))";
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        TextUtils.writeToParcel(string, dest, flags);
    }

    public static final Creator<CharSequenceWrapper> CREATOR = new Creator<CharSequenceWrapper>() {

        @Override
        public CharSequenceWrapper createFromParcel(Parcel source) {
            return new CharSequenceWrapper(CHAR_SEQUENCE_CREATOR.createFromParcel(source));
        }

        @Override
        public CharSequenceWrapper[] newArray(int size) {
            return new CharSequenceWrapper[size];
        }
    };
}
