//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.N;
import static androidx.core.os.ParcelCompat.readParcelable;
import static androidx.core.os.ParcelCompat.readSerializable;
import static java.lang.String.format;
import static xyz.tynn.astring.Wrapper.NULL;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcel;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation calling {@link CharSequence#toString()} on the result and
 * {@link String#format(Locale, String, Object...)} with provided format arguments
 */
final class ToString implements AString {

    private final AString delegate;
    private final Locale locale;
    private final Object[] formatArgs;

    private ToString(AString delegate, Locale locale, Object[] formatArgs) {
        this.delegate = delegate;
        this.locale = locale;
        this.formatArgs = formatArgs;
    }

    static AString wrap(AString delegate, Locale locale, Object[] formatArgs) {
        if (delegate == null || delegate == NULL) return NULL;
        if (formatArgs == null || formatArgs.length == 0) {
            if (delegate instanceof Provider || delegate instanceof ToString) return delegate;
            if (delegate instanceof Wrapper) return ((Wrapper) delegate).wrapToString();
            locale = null;
            formatArgs = null;
        } else if (delegate instanceof ToString) {
            ToString toString = (ToString) delegate;
            if (toString.formatArgs == null) delegate = toString.delegate;
        }
        return new ToString(delegate, locale, formatArgs);
    }

    @Override
    public String invoke(@NonNull Context context) {
        CharSequence formatString = delegate.invoke(context);
        return formatString == null ? null : formatArgs == null ? formatString.toString()
                : format(getLocale(context), formatString.toString(), formatArgs);
    }

    @SuppressWarnings("deprecation")
    private Locale getLocale(Context context) {
        if (locale != null) return locale;
        Configuration configuration = context.getResources().getConfiguration();
        if (SDK_INT < N) return configuration.locale;
        return configuration.getLocales().get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToString that = (ToString) o;
        return delegate.equals(that.delegate) &&
                Objects.equals(this.locale, that.locale) &&
                Arrays.equals(this.formatArgs, that.formatArgs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delegate, locale, Arrays.hashCode(formatArgs));
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AString(");
        if (formatArgs != null) {
            sb.append("Format(");
            if (locale != null) sb.append(locale).append(',');
            sb.append(delegate);
            for (Object o : formatArgs) sb.append(',').append(o);
        } else {
            sb.append("String(").append(delegate);
        }
        return sb.append("))").toString();
    }

    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(this.delegate, flags);
        dest.writeSerializable(locale);
        dest.writeValue(formatArgs);
    }

    public static final Creator<ToString> CREATOR = new Creator<>() {

        @Override
        public ToString createFromParcel(Parcel source) {
            ClassLoader classLoader = getClass().getClassLoader();
            return new ToString(
                    readParcelable(source, classLoader, AString.class),
                    readSerializable(source, classLoader, Locale.class),
                    (Object[]) source.readValue(classLoader));
        }

        @Override
        public ToString[] newArray(int size) {
            return new ToString[size];
        }
    };
}
