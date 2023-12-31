//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static xyz.tynn.astring.test.AStringAssert.assertParcelableAStringEquality;
import static xyz.tynn.astring.test.AStringAssert.assertParcelableAStringIdentity;
import static xyz.tynn.astring.test.AStringAssert.assertParcelableAStringInvocation;

import android.content.Context;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.junit.Test;

import java.util.Date;
import java.util.Locale;

public class ParcelableAStringTest {

    private static final AString FORMAT = new FormatAString();
    private static final Object[] FORMAT_ARGS = {"arg1", 2, 3L, 4.5, 6F, new Date(), Wrapper.wrap("test-string")};

    @Test
    public void Wrapper_should_implement_parcelable() {
        assertParcelableAStringIdentity(Wrapper.wrap(null));
        assertParcelableAStringInvocation(Wrapper.wrap(null));
        assertParcelableAStringEquality(Wrapper.wrap("test-string"));
        assertParcelableAStringInvocation(Wrapper.wrap("test-string"));
    }

    @Test
    public void Format_should_implement_parcelable() {
        assertParcelableAStringEquality(Format.wrap(FORMAT, Locale.UK, FORMAT_ARGS));
        assertParcelableAStringInvocation(Format.wrap(FORMAT, Locale.UK, FORMAT_ARGS));
        assertParcelableAStringEquality(Format.wrap(FORMAT, null, FORMAT_ARGS));
        assertParcelableAStringInvocation(Format.wrap(FORMAT, null, FORMAT_ARGS));
        assertParcelableAStringEquality(Format.wrap(FORMAT, Locale.UK, null));
        assertParcelableAStringInvocation(Format.wrap(FORMAT, Locale.UK, null));
        assertParcelableAStringEquality(Format.wrap(FORMAT, null, null));
        assertParcelableAStringInvocation(Format.wrap(FORMAT, null, null));
    }

    @Test
    public void Resource_should_implement_parcelable() {
        assertParcelableAStringEquality(Resource.wrap(123, null));
        assertParcelableAStringEquality(Resource.wrap(123, 456));
        assertParcelableAStringEquality(Resource.wrap(123, null, FORMAT_ARGS));
        assertParcelableAStringEquality(Resource.wrap(123, 456, FORMAT_ARGS));
        assertParcelableAStringEquality(Resource.wrap(123, null, null));
        assertParcelableAStringEquality(Resource.wrap(123, 456, null));
    }

    @Test
    public void Delegate_should_implement_parcelable() {
        assertParcelableAStringIdentity(Delegate.wrap(null));
        assertParcelableAStringInvocation(Delegate.wrap(null));
        assertParcelableAStringEquality(Delegate.wrap(Provider.AppId));
        assertParcelableAStringInvocation(Delegate.wrap(Provider.AppId));
        assertParcelableAStringEquality(Delegate.wrap(Provider.AppVersion));
        assertParcelableAStringInvocation(Delegate.wrap(Provider.AppVersion));
        assertParcelableAStringInvocation(Delegate.wrap(Object::toString));
        assertParcelableAStringIdentity(Delegate.wrap((AString.Transformer) null, null));
        assertParcelableAStringInvocation(Delegate.wrap((AString.Transformer) null, null));
        assertParcelableAStringEquality(Delegate.wrap(Transformer.ToString, FORMAT));
        assertParcelableAStringInvocation(Delegate.wrap(Transformer.ToString, FORMAT));
        assertParcelableAStringEquality(Delegate.wrap(Transformer.Trim, FORMAT));
        assertParcelableAStringInvocation(Delegate.wrap(Transformer.Trim, FORMAT));
        assertParcelableAStringIdentity(Delegate.wrap((AString.Reducer) null));
        assertParcelableAStringInvocation(Delegate.wrap((AString.Reducer) null));
        assertParcelableAStringEquality(Delegate.wrap(Predicate.AnyValue, FORMAT));
        assertParcelableAStringInvocation(Delegate.wrap(Predicate.AnyValue, FORMAT));
        assertParcelableAStringEquality(Delegate.wrap(Predicate.NonBlank, FORMAT));
        assertParcelableAStringInvocation(Delegate.wrap(Predicate.NonBlank, FORMAT));
        assertParcelableAStringEquality(Delegate.wrap(Predicate.NonEmpty, FORMAT));
        assertParcelableAStringInvocation(Delegate.wrap(Predicate.NonEmpty, FORMAT));
        assertParcelableAStringEquality(Delegate.wrap(Predicate.NonNull, FORMAT));
        assertParcelableAStringInvocation(Delegate.wrap(Predicate.NonNull, FORMAT));
    }

    private static class FormatAString implements AString {

        @Nullable
        @Override
        public String invoke(@NonNull Context context) {
            return "%s%d%d%f%f%s%s";
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            return obj instanceof FormatAString;
        }

        @Override
        public int hashCode() {
            return 43;
        }

        public static final Creator<FormatAString> CREATOR = new Creator<>() {

            @Override
            public FormatAString createFromParcel(Parcel source) {
                return new FormatAString();
            }

            @Override
            public FormatAString[] newArray(int size) {
                return new FormatAString[size];
            }
        };
    }
}
