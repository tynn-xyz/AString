//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * Implementation delegating to a text resource
 */
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resId);
    }

    public static final Creator<TextResourceDelegate> CREATOR = new Creator<TextResourceDelegate>() {

        @Override
        public TextResourceDelegate createFromParcel(Parcel source) {
            return new TextResourceDelegate(source.readInt());
        }

        @Override
        public TextResourceDelegate[] newArray(int size) {
            return new TextResourceDelegate[size];
        }
    };
}
