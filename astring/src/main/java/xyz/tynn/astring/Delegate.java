//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static androidx.core.os.ParcelCompat.readParcelableArrayTyped;
import static androidx.core.os.ParcelCompat.readSerializable;
import static java.util.Arrays.copyOf;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

final class Delegate implements AString {

    private static final AString[] EMPTY = new AString[0];

    private final Serializer<?> delegate;
    private final AString[] aStrings;

    private Delegate(Serializer<?> delegate, AString... aStrings) {
        this.delegate = delegate;
        this.aStrings = aStrings = aStrings == null ? EMPTY : aStrings;
        for (int i = 0; i < aStrings.length; i++)
            if (aStrings[i] == null) aStrings[i] = Null;
    }

    @InefficientAStringApi
    static AString wrap(AString.Provider provider) {
        if (provider == null) return Null;
        return new Delegate(new Serializer.Provider(provider), EMPTY);
    }

    @InefficientAStringApi
    static AString wrap(AString.Transformer transformer, AString aString) {
        if (transformer == null) return Null;
        if (aString == null || aString == Null) return Wrapper.wrap(transformer.invoke(null));
        if (aString instanceof Wrapper) return ((Wrapper) aString).map(transformer);
        return new Delegate(new Serializer.Transformer(transformer), aString);
    }

    @Nullable
    @Override
    public CharSequence invoke(@NonNull Context context) {
        return delegate.invoke(context, aStrings);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delegate)) return false;
        Delegate that = (Delegate) o;
        return delegate.equals(that.delegate) && Arrays.equals(aStrings, that.aStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delegate, Arrays.hashCode(aStrings));
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AString(");
        sb.append(delegate);
        for (AString o : aStrings) sb.append(',').append(o);
        return sb.append(')').toString();
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeSerializable(delegate);
        dest.writeParcelableArray(aStrings, 0);
    }

    private static abstract class Serializer<S extends Serializable> implements Serializable {

        protected final S delegate;

        Serializer(S delegate) {
            this.delegate = delegate;
        }

        @Nullable
        abstract CharSequence invoke(Context context, AString[] aStrings);

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Serializer)) return false;
            Serializer<?> that = (Serializer<?>) o;
            return delegate.equals(that.delegate);
        }

        @Override
        public final int hashCode() {
            return delegate.hashCode();
        }

        @InefficientAStringApi
        static final class Provider extends Serializer<AString.Provider> {

            Provider(AString.Provider delegate) {
                super(delegate);
            }

            @Nullable
            @Override
            public CharSequence invoke(Context context, AString[] aStrings) {
                return delegate.invoke(context);
            }

            @NonNull
            @Override
            public String toString() {
                return delegate.toString();
            }
        }

        @InefficientAStringApi
        static final class Transformer extends Serializer<AString.Transformer> {

            Transformer(AString.Transformer delegate) {
                super(delegate);
            }

            @Nullable
            @Override
            public CharSequence invoke(Context context, AString[] aStrings) {
                return delegate.invoke(aStrings[0].invoke(context));
            }

            @NonNull
            @Override
            public String toString() {
                return "Map(" + delegate + ')';
            }
        }
    }

    public static final Creator<Delegate> CREATOR = new Creator<>() {

        @Override
        public Delegate createFromParcel(Parcel source) {
            ClassLoader classLoader = getClass().getClassLoader();
            return new Delegate(
                    readSerializable(source, classLoader, Serializer.class),
                    readParcelableArray(source, classLoader));
        }

        private AString[] readParcelableArray(Parcel source, ClassLoader loader) {
            Parcelable[] parcelables = readParcelableArrayTyped(source, loader, AString.class);
            if (parcelables instanceof AString[]) return (AString[]) parcelables;
            if (parcelables == null || parcelables.length == 0) return EMPTY;
            return copyOf(parcelables, parcelables.length, AString[].class);
        }

        @Override
        public Delegate[] newArray(int size) {
            return new Delegate[size];
        }
    };
}
