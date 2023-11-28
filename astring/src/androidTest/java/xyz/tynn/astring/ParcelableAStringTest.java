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
    private static final AString FORMAT = new Format();
    private static final Object[] FORMAT_ARGS = {"arg1", 2, 3L, 4.5, 6F, new Date()};

    @Test
    public void Wrapper_should_implement_parcelable() {
        assertParcelableAStringEquality(Wrapper.wrap("test-string"));
        assertParcelableAStringInvocation(Wrapper.wrap("test-string"));
    }

    @Test
    public void Wrapper_of_null_should_implement_parcelable() {
        assertParcelableAStringEquality(Wrapper.wrap(null));
        assertParcelableAStringInvocation(Wrapper.wrap(null));
    }

    @Test
    public void Wrapper_NULL_should_implement_parcelable() {
        assertParcelableAStringIdentity(Wrapper.NULL);
        assertParcelableAStringInvocation(Wrapper.NULL);
    }

    @Test
    public void ToString_should_implement_parcelable() {
        assertParcelableAStringEquality(ToString.wrap(FORMAT, LOCALE, FORMAT_ARGS));
        assertParcelableAStringInvocation(ToString.wrap(FORMAT, LOCALE, FORMAT_ARGS));
    }

    @Test
    public void ToString_without_locale_should_implement_parcelable() {
        assertParcelableAStringEquality(ToString.wrap(FORMAT, null, FORMAT_ARGS));
        assertParcelableAStringInvocation(ToString.wrap(FORMAT, null, FORMAT_ARGS));
    }

    @Test
    public void ToString_without_args_should_implement_parcelable() {
        assertParcelableAStringEquality(ToString.wrap(FORMAT, LOCALE, null));
        assertParcelableAStringInvocation(ToString.wrap(FORMAT, LOCALE, null));
    }

    @Test
    public void ToString_without_args_and_locale_should_implement_parcelable() {
        assertParcelableAStringEquality(ToString.wrap(FORMAT, null, null));
        assertParcelableAStringInvocation(ToString.wrap(FORMAT, null, null));
    }

    @Test
    public void Resource_quantity_text_should_implement_parcelable() {
        assertParcelableAStringEquality(Resource.wrap(RES_ID, QUANTITY));
    }

    @Test
    public void Resource_quantity_string_with_args_should_implement_parcelable() {
        assertParcelableAStringEquality(Resource.wrap(RES_ID, QUANTITY, FORMAT_ARGS));
    }

    @Test
    public void Resource_quantity_string_without_args_should_implement_parcelable() {
        assertParcelableAStringEquality(Resource.wrap(RES_ID, QUANTITY, null));
    }

    @Test
    public void Resource_text_should_implement_parcelable() {
        assertParcelableAStringEquality(Resource.wrap(RES_ID, null));
    }

    @Test
    public void Resource_string_with_args_should_implement_parcelable() {
        assertParcelableAStringEquality(Resource.wrap(RES_ID, null, FORMAT_ARGS));
    }

    @Test
    public void Resource_string_without_args_should_implement_parcelable() {
        assertParcelableAStringEquality(Resource.wrap(RES_ID, null, null));
    }

    @Test
    public void AppIdProvider_should_implement_parcelable() {
        assertParcelableAStringIdentity(Provider.AppIdProvider);
        assertParcelableAStringInvocation(Provider.AppIdProvider);
    }

    @Test
    public void AppVersionProvider_should_implement_parcelable() {
        assertParcelableAStringIdentity(Provider.AppVersionProvider);
        assertParcelableAStringInvocation(Provider.AppVersionProvider);
    }

    private static class Format implements AString {

        @Nullable
        @Override
        public String invoke(@NonNull Context context) {
            return "%s%d%d%f%f%s";
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            return obj instanceof Format;
        }

        @Override
        public int hashCode() {
            return 43;
        }

        public static final Creator<Format> CREATOR = new Creator<>() {

            @Override
            public Format createFromParcel(Parcel source) {
                return new Format();
            }

            @Override
            public Format[] newArray(int size) {
                return new Format[size];
            }
        };
    }
}
