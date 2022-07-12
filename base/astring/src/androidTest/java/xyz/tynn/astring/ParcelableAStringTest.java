//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static xyz.tynn.astring.test.AssertParcelable.assertParcelable;

import org.junit.Test;

import java.util.Date;

public class ParcelableAStringTest {

    public static final int RES_ID = 123;
    public static final int QUANTITY = 456;
    public static final Object[] FORMAT_ARGS = {"arg1", 2, 3L, 4.5, 6F, new Date()};

    @Test
    public void CharSequence_should_implement_parcelable() {
        assertParcelable(new CharSequenceWrapper("test-string"));
    }

    @Test
    public void NullValueProvider_should_implement_parcelable() {
        assertParcelable(new CharSequenceWrapper(null));
    }

    @Test
    public void QuantityStringResourceDelegate_should_implement_parcelable() {
        assertParcelable(ResourceDelegate.quantityString(RES_ID, QUANTITY, FORMAT_ARGS));
    }

    @Test
    public void QuantityTextResourceDelegate_should_implement_parcelable() {
        assertParcelable(ResourceDelegate.quantityText(RES_ID, QUANTITY));
    }

    @Test
    public void StringResourceDelegate_should_implement_parcelable() {
        assertParcelable(ResourceDelegate.string(RES_ID, FORMAT_ARGS));
    }

    @Test
    public void TextResourceDelegate_should_implement_parcelable() {
        assertParcelable(ResourceDelegate.text(RES_ID));
    }

    @Test
    public void AppAStrings_AppIdProvider_should_implement_parcelable() {
        assertParcelable(ContextValueProvider.AppIdProvider);
    }

    @Test
    public void AppAStrings_AppVersionProvider_should_implement_parcelable() {
        assertParcelable(ContextValueProvider.AppVersionProvider);
    }
}
