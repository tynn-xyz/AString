//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import java.util.Arrays;

/**
 * Implementation delegating to a string resource
 * with or without format arguments
 */
final class StringResourceDelegate implements AString {

    private final int resId;
    private final Object[] formatArgs;

    StringResourceDelegate(@StringRes int resId, @Nullable Object[] formatArgs) {
        this.resId = resId;
        this.formatArgs = formatArgs == null || formatArgs.length == 0 ? null : formatArgs;
    }

    @Nullable
    @Override
    public CharSequence invoke(@NonNull Context context) {
        if (formatArgs == null)
            return context.getString(resId);
        return context.getString(resId, formatArgs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringResourceDelegate that = (StringResourceDelegate) o;
        if (resId != that.resId) return false;
        return Arrays.equals(formatArgs, that.formatArgs);
    }

    @Override
    public int hashCode() {
        return 31 * resId + Arrays.hashCode(formatArgs);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AString(")
                .append("StringResource(").append(resId);
        if (formatArgs != null)
            for (Object o : formatArgs)
                sb.append(',').append(o);
        return sb.append("))").toString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resId);
        dest.writeValue(formatArgs);
    }

    public static final Creator<StringResourceDelegate> CREATOR = new Creator<StringResourceDelegate>() {

        @Override
        public StringResourceDelegate createFromParcel(Parcel source) {
            return new StringResourceDelegate(source.readInt(),
                    (Object[]) source.readValue(getClass().getClassLoader()));
        }

        @Override
        public StringResourceDelegate[] newArray(int size) {
            return new StringResourceDelegate[size];
        }
    };
}
