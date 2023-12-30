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

    private static final Locale LOCALE = Locale.UK;
    private static final int RES_ID = 123;
    private static final int QUANTITY = 456;
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
        assertParcelableAStringEquality(Format.wrap(FORMAT, LOCALE, FORMAT_ARGS));
        assertParcelableAStringInvocation(Format.wrap(FORMAT, LOCALE, FORMAT_ARGS));
        assertParcelableAStringEquality(Format.wrap(FORMAT, null, FORMAT_ARGS));
        assertParcelableAStringInvocation(Format.wrap(FORMAT, null, FORMAT_ARGS));
        assertParcelableAStringEquality(Format.wrap(FORMAT, LOCALE, null));
        assertParcelableAStringInvocation(Format.wrap(FORMAT, LOCALE, null));
        assertParcelableAStringEquality(Format.wrap(FORMAT, null, null));
        assertParcelableAStringInvocation(Format.wrap(FORMAT, null, null));
    }

    @Test
    public void Resource_should_implement_parcelable() {
        assertParcelableAStringEquality(Resource.wrap(RES_ID, null));
        assertParcelableAStringEquality(Resource.wrap(RES_ID, QUANTITY));
        assertParcelableAStringEquality(Resource.wrap(RES_ID, null, FORMAT_ARGS));
        assertParcelableAStringEquality(Resource.wrap(RES_ID, QUANTITY, FORMAT_ARGS));
        assertParcelableAStringEquality(Resource.wrap(RES_ID, null, null));
        assertParcelableAStringEquality(Resource.wrap(RES_ID, QUANTITY, null));
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
        assertParcelableAStringIdentity(Delegate.wrap((AString) null, null));
        assertParcelableAStringInvocation(Delegate.wrap((AString) null, null));
        assertParcelableAStringEquality(Delegate.wrap(FORMAT, Predicate.NonBlank));
        assertParcelableAStringInvocation(Delegate.wrap(FORMAT, Predicate.NonBlank));
        assertParcelableAStringEquality(Delegate.wrap(FORMAT, Predicate.NonEmpty));
        assertParcelableAStringInvocation(Delegate.wrap(FORMAT, Predicate.NonEmpty));
        assertParcelableAStringEquality(Delegate.wrap(FORMAT, Predicate.NonNull));
        assertParcelableAStringInvocation(Delegate.wrap(FORMAT, Predicate.NonNull));
        assertParcelableAStringEquality(Delegate.wrap(FORMAT, Transformer.ToString));
        assertParcelableAStringInvocation(Delegate.wrap(FORMAT, Transformer.ToString));
        assertParcelableAStringEquality(Delegate.wrap(FORMAT, Transformer.Trim));
        assertParcelableAStringInvocation(Delegate.wrap(FORMAT, Transformer.Trim));
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
