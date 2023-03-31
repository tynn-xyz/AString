//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static xyz.tynn.astring.test.AStringAssert.assertParcelableAStringEquality;
import static xyz.tynn.astring.test.AStringAssert.assertParcelableAStringIdentity;
import static xyz.tynn.astring.test.AStringAssert.assertParcelableAStringInvocation;

import org.junit.Test;

import java.util.Date;

public class ParcelableAStringTest {

    public static final int RES_ID = 123;
    public static final int QUANTITY = 456;
    public static final Object[] FORMAT_ARGS = {"arg1", 2, 3L, 4.5, 6F, new Date()};

    @Test
    public void CharSequenceWrapper_should_implement_parcelable() {
        assertParcelableAStringEquality(new CharSequenceWrapper("test-string"));
        assertParcelableAStringInvocation(new CharSequenceWrapper("test-string"));
    }

    @Test
    public void CharSequenceWrapper_of_null_should_implement_parcelable() {
        assertParcelableAStringEquality(new CharSequenceWrapper(null));
        assertParcelableAStringInvocation(new CharSequenceWrapper(null));
    }

    @Test
    public void CharSequenceWrapper_NULL_should_implement_parcelable() {
        assertParcelableAStringIdentity(CharSequenceWrapper.NULL);
        assertParcelableAStringInvocation(CharSequenceWrapper.NULL);
    }

    @Test
    public void ResourceDelegate_quantityString_should_implement_parcelable() {
        assertParcelableAStringEquality(ResourceDelegate.quantityString(RES_ID, QUANTITY, FORMAT_ARGS));
    }

    @Test
    public void ResourceDelegate_quantityString_without_args_should_implement_parcelable() {
        assertParcelableAStringEquality(ResourceDelegate.quantityString(RES_ID, QUANTITY, null));
    }

    @Test
    public void ResourceDelegate_quantityText_should_implement_parcelable() {
        assertParcelableAStringEquality(ResourceDelegate.quantityText(RES_ID, QUANTITY));
    }

    @Test
    public void ResourceDelegate_string_should_implement_parcelable() {
        assertParcelableAStringEquality(ResourceDelegate.string(RES_ID, FORMAT_ARGS));
    }

    @Test
    public void ResourceDelegate_string_without_args_should_implement_parcelable() {
        assertParcelableAStringEquality(ResourceDelegate.string(RES_ID, null));
    }

    @Test
    public void ResourceDelegate_text_should_implement_parcelable() {
        assertParcelableAStringEquality(ResourceDelegate.text(RES_ID));
    }

    @Test
    public void ContextValueProvider_AppIdProvider_should_implement_parcelable() {
        assertParcelableAStringIdentity(ContextValueProvider.AppIdProvider);
        assertParcelableAStringInvocation(ContextValueProvider.AppIdProvider);
    }

    @Test
    public void ContextValueProvider_AppVersionProvider_should_implement_parcelable() {
        assertParcelableAStringIdentity(ContextValueProvider.AppVersionProvider);
        assertParcelableAStringInvocation(ContextValueProvider.AppVersionProvider);
    }
}
