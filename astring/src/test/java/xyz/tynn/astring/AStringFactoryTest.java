//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import java.util.Locale;

public class AStringFactoryTest {

    @Test
    public void nullAsAString_should_return_NULL() {
        assertSame(Wrapper.NULL, AStringFactory.nullAsAString);
    }

    @Test
    public void wrapNullAsAString_should_return_NULL() {
        assertSame(Wrapper.NULL, AStringFactory.wrapNullAsAString(null));
    }

    @Test
    public void wrapNullAsAString_should_return_identity() {
        AString aString = AStringFactory.createFromQuantityStringResource(1, 2);
        assertSame(aString, AStringFactory.wrapNullAsAString(aString));
    }

    @Test
    public void createFromCharSequence_should_return_NULL() {
        assertSame(Wrapper.NULL, AStringFactory.createFromCharSequence(null));
    }

    @Test
    public void createFromQuantityStringResource_should_return_NULL() {
        assertSame(Wrapper.NULL, AStringFactory.createFromQuantityStringResource(0, 1));
        assertSame(Wrapper.NULL, AStringFactory.createFromQuantityStringResource(0, 1, 2));
    }

    @Test
    public void createFromQuantityStringResource_with_null_args_should_return_QuantityStringResource() {
        assertEquals(AStringFactory.createFromQuantityStringResource(1, 2),
                AStringFactory.createFromQuantityStringResource(1, 2, (Object[]) null));
    }

    @Test
    public void createFromQuantityTextResource_should_return_NULL() {
        assertSame(Wrapper.NULL, AStringFactory.createFromQuantityTextResource(0, 1));
    }

    @Test
    public void createFromStringResource_should_return_NULL() {
        assertSame(Wrapper.NULL, AStringFactory.createFromStringResource(0));
        assertSame(Wrapper.NULL, AStringFactory.createFromStringResource(0, 1));
    }

    @Test
    public void createFromStringResource_with_null_args_should_return_StringResource() {
        assertEquals(AStringFactory.createFromStringResource(1),
                AStringFactory.createFromStringResource(1, (Object[]) null));
    }

    @Test
    public void createFromTextResource_should_return_NULL() {
        assertSame(Wrapper.NULL, AStringFactory.createFromTextResource(0));
    }

    @Test
    public void format_should_return_ToString() {
        assertEquals(AStringFactory.formatWithAString(Wrapper.NULL, 1, "2"),
                AStringFactory.formatWithAString(Wrapper.NULL, 1, "2"));
    }

    @Test
    public void format_without_args_should_return_ToString() {
        assertEquals(AStringFactory.formatWithAString(Wrapper.NULL),
                AStringFactory.formatWithAString(Wrapper.NULL, (Object[]) null));
    }

    @Test
    public void format_should_be_NULL_on_null_format() {
        assertSame(Wrapper.NULL, AStringFactory.formatWithAString(null, 1, "2"));
        assertSame(Wrapper.NULL, AStringFactory.formatWithAString(Wrapper.NULL, 1, "2"));
    }

    @Test
    public void format_without_locale_should_return_ToString() {
        assertEquals(AStringFactory.formatWithAString(Wrapper.NULL, 1, "2"),
                AStringFactory.formatWithAString(Wrapper.NULL, null, 1, "2"));
    }

    @Test
    public void format_without_locale_and_args_should_return_ToString() {
        assertEquals(AStringFactory.formatWithAString(Wrapper.NULL),
                AStringFactory.formatWithAString(Wrapper.NULL, null, (Object[]) null));
    }

    @Test
    public void format_with_locale_should_return_ToString() {
        assertEquals(AStringFactory.formatWithAString(Wrapper.NULL, Locale.UK, 1, "2"),
                AStringFactory.formatWithAString(Wrapper.NULL, Locale.UK, 1, "2"));
    }

    @Test
    public void format_with_locale_should_be_NULL_on_null_format() {
        assertEquals(Wrapper.NULL, AStringFactory.formatWithAString(null, Locale.UK, 1, "2"));
        assertEquals(Wrapper.NULL, AStringFactory.formatWithAString(Wrapper.NULL, Locale.UK, 1, "2"));
    }

    @Test
    public void wrapToString_should_be_NULL_on_null() {
        assertEquals(Wrapper.NULL, AStringFactory.wrapToString(null));
        assertEquals(Wrapper.NULL, AStringFactory.wrapToString(Wrapper.NULL));
    }

    @Test
    public void wrapToString_should_be_identity_on_Provider() {
        assertSame(Provider.AppIdProvider, AStringFactory.wrapToString(Provider.AppIdProvider));
        assertSame(Provider.AppVersionProvider, AStringFactory.wrapToString(Provider.AppVersionProvider));
    }

    @Test
    public void wrapToString_should_be_identity_on_ToString() {
        AString aString = AStringFactory.formatWithAString(Provider.AppIdProvider, 1);
        assertSame(aString, AStringFactory.wrapToString(aString));
    }

    @Test
    public void wrapToString_should_be_Wrapper_on_Wrapper() {
        AString aString = AStringFactory.createFromCharSequence("value");
        assertSame(aString, AStringFactory.wrapToString(aString));
        assertEquals(aString, AStringFactory.wrapToString(
                AStringFactory.createFromCharSequence(new StringBuilder("value"))));
    }

    @Test
    public void appIdAString_should_return_AppIdProvider() {
        assertSame(Provider.AppIdProvider, AStringFactory.appIdAString);
    }

    @Test
    public void appVersionAString_should_return_AppVersionProvider() {
        assertSame(Provider.AppVersionProvider, AStringFactory.appVersionAString);
    }
}
