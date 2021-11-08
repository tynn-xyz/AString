//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static xyz.tynn.astring.AssertParcelable.assertParcelable;

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
    public void NullValueWrapper_should_implement_parcelable() {
        assertParcelable(NullValueWrapper.I);
    }

    @Test
    public void QuantityStringResourceDelegate_should_implement_parcelable() {
        assertParcelable(new QuantityStringResourceDelegate(RES_ID, QUANTITY, FORMAT_ARGS));
    }

    @Test
    public void QuantityTextResourceDelegate_should_implement_parcelable() {
        assertParcelable(new QuantityTextResourceDelegate(RES_ID, QUANTITY));
    }

    @Test
    public void StringResourceDelegate_should_implement_parcelable() {
        assertParcelable(new StringResourceDelegate(RES_ID, FORMAT_ARGS));
    }

    @Test
    public void TextResourceDelegate_should_implement_parcelable() {
        assertParcelable(new TextResourceDelegate(RES_ID));
    }
}
