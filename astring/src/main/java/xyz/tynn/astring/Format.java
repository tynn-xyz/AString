//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.N;
import static androidx.core.os.ParcelCompat.readParcelable;
import static androidx.core.os.ParcelCompat.readSerializable;
import static java.lang.String.format;
import static java.util.Arrays.copyOf;

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
final class Format implements AString {

    private final AString delegate;
    private final Locale locale;
    private final Object[] formatArgs, aStringArgs;

    private Format(AString delegate, Locale locale, Object[] formatArgs) {
        this.delegate = delegate;
        this.locale = locale;
        this.formatArgs = formatArgs;
        this.aStringArgs = createAStringArgs(formatArgs);
    }

    private static Object[] createAStringArgs(Object[] formatArgs) {
        if (formatArgs != null) for (Object arg : formatArgs)
            if (arg instanceof AString)
                return copyOf(formatArgs, formatArgs.length, Object[].class);
        return null;
    }

    static AString wrap(AString delegate, Locale locale, Object[] formatArgs) {
        if (delegate == null || delegate == Null) return Null;
        if (formatArgs == null || formatArgs.length == 0) {
            if (delegate instanceof Wrapper) return ((Wrapper) delegate).wrapToString();
            if (delegate instanceof Format) return delegate;
            locale = null;
            formatArgs = null;
        } else if (delegate instanceof Format) {
            Format format = (Format) delegate;
            if (format.formatArgs == null) delegate = format.delegate;
        }
        return new Format(delegate, locale, formatArgs);
    }

    @Override
    public String invoke(@NonNull Context context) {
        CharSequence formatString = delegate.invoke(context);
        if (formatString == null) return null;
        Object[] formatArgs = getFormatArgs(context);
        if (formatArgs == null) return formatString.toString();
        return format(getLocale(context), formatString.toString(), formatArgs);
    }

    private Object[] getFormatArgs(Context context) {
        Object[] formatArgs = this.formatArgs;
        if (formatArgs == null) return null;
        Object[] aStringArgs = this.aStringArgs;
        if (aStringArgs == null) return formatArgs;
        for (int i = 0; i < formatArgs.length; i++)
            if (formatArgs[i] instanceof AString)
                aStringArgs[i] = ((AString) formatArgs[i]).invoke(context);
        return aStringArgs;
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
        Format that = (Format) o;
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

    public static final Creator<Format> CREATOR = new Creator<>() {

        @Override
        public Format createFromParcel(Parcel source) {
            ClassLoader classLoader = getClass().getClassLoader();
            return new Format(
                    readParcelable(source, classLoader, AString.class),
                    readSerializable(source, classLoader, Locale.class),
                    (Object[]) source.readValue(classLoader));
        }

        @Override
        public Format[] newArray(int size) {
            return new Format[size];
        }
    };
}
