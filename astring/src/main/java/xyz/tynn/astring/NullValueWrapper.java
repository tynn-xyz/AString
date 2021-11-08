//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Implementation wrapping {@code null}
 */
final class NullValueWrapper implements AString {

    @NonNull
    static NullValueWrapper I = new NullValueWrapper();

    private NullValueWrapper() {
    }

    @Nullable
    @Override
    public CharSequence invoke(@Nullable Context context) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof NullValueWrapper;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Creator<NullValueWrapper> CREATOR = new Creator<NullValueWrapper>() {

        @Override
        public NullValueWrapper createFromParcel(Parcel source) {
            return I;
        }

        @Override
        public NullValueWrapper[] newArray(int size) {
            return new NullValueWrapper[size];
        }
    };
}
