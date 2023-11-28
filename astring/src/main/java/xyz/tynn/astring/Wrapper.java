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
final class Wrapper implements AString {

    static final Wrapper NULL = new Wrapper(null);

    private final CharSequence value;

    private Wrapper(@Nullable CharSequence value) {
        this.value = value;
    }

    static Wrapper wrap(CharSequence value) {
        return value == null ? NULL : new Wrapper(value);
    }

    @Override
    public CharSequence invoke(@Nullable Context context) {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wrapper that = (Wrapper) o;
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
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        TextUtils.writeToParcel(value, dest, flags);
    }

    Wrapper wrapToString() {
        CharSequence value = this.value;
        return value == null || value instanceof String ? this : new Wrapper(value.toString());
    }

    public static final Creator<Wrapper> CREATOR = new Creator<>() {

        @Override
        public Wrapper createFromParcel(Parcel source) {
            return wrap(CHAR_SEQUENCE_CREATOR.createFromParcel(source));
        }

        @Override
        public Wrapper[] newArray(int size) {
            return new Wrapper[size];
        }
    };
}
