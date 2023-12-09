//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import java.util.Locale;

public class AStringFactoryTest {

    @Test
    @SuppressWarnings("EqualsWithItself")
    public void Null_should_return_Null() {
        assertSame(AString.Null, AString.Null);
    }

    @Test
    public void ensureAStringNull_should_return_NULL() {
        assertSame(AString.Null, AStringFactory.ensureAStringNull(null));
    }

    @Test
    public void ensureAStringNull_should_return_identity() {
        AString aString = AStringFactory.createFromQuantityStringResource(1, 2);
        assertSame(aString, AStringFactory.ensureAStringNull(aString));
    }

    @Test
    public void createFromCharSequence_should_return_NULL() {
        assertSame(AString.Null, AStringFactory.createFromCharSequence(null));
    }

    @Test
    public void createFromQuantityStringResource_should_return_NULL() {
        assertSame(AString.Null, AStringFactory.createFromQuantityStringResource(0, 1));
        assertSame(AString.Null, AStringFactory.createFromQuantityStringResource(0, 1, 2));
    }

    @Test
    public void createFromQuantityStringResource_with_null_args_should_return_QuantityStringResource() {
        assertEquals(AStringFactory.createFromQuantityStringResource(1, 2),
                AStringFactory.createFromQuantityStringResource(1, 2, (Object[]) null));
    }

    @Test
    public void createFromQuantityTextResource_should_return_NULL() {
        assertSame(AString.Null, AStringFactory.createFromQuantityTextResource(0, 1));
    }

    @Test
    public void createFromStringResource_should_return_NULL() {
        assertSame(AString.Null, AStringFactory.createFromStringResource(0));
        assertSame(AString.Null, AStringFactory.createFromStringResource(0, 1));
    }

    @Test
    public void createFromStringResource_with_null_args_should_return_StringResource() {
        assertEquals(AStringFactory.createFromStringResource(1),
                AStringFactory.createFromStringResource(1, (Object[]) null));
    }

    @Test
    public void createFromTextResource_should_return_NULL() {
        assertSame(AString.Null, AStringFactory.createFromTextResource(0));
    }

    @Test
    public void format_should_return_Format() {
        assertEquals(AStringFactory.formatWithAString(AString.Null, 1, "2"),
                AStringFactory.formatWithAString(AString.Null, 1, "2"));
    }

    @Test
    public void format_without_args_should_return_Format() {
        assertEquals(AStringFactory.formatWithAString(AString.Null),
                AStringFactory.formatWithAString(AString.Null, (Object[]) null));
    }

    @Test
    public void format_should_be_NULL_on_null_format() {
        assertSame(AString.Null, AStringFactory.formatWithAString(null, 1, "2"));
        assertSame(AString.Null, AStringFactory.formatWithAString(AString.Null, 1, "2"));
    }

    @Test
    public void format_without_locale_should_return_Format() {
        assertEquals(AStringFactory.formatWithAString(AString.Null, 1, "2"),
                AStringFactory.formatWithAString(AString.Null, null, 1, "2"));
    }

    @Test
    public void format_without_locale_and_args_should_return_Format() {
        assertEquals(AStringFactory.formatWithAString(AString.Null),
                AStringFactory.formatWithAString(AString.Null, null, (Object[]) null));
    }

    @Test
    public void format_with_locale_should_return_Format() {
        assertEquals(AStringFactory.formatWithAString(AString.Null, Locale.UK, 1, "2"),
                AStringFactory.formatWithAString(AString.Null, Locale.UK, 1, "2"));
    }

    @Test
    public void format_with_locale_should_be_NULL_on_null_format() {
        assertEquals(AString.Null, AStringFactory.formatWithAString(null, Locale.UK, 1, "2"));
        assertEquals(AString.Null, AStringFactory.formatWithAString(AString.Null, Locale.UK, 1, "2"));
    }

    @Test
    public void mapToString_should_be_NULL_on_null() {
        assertEquals(AString.Null, AStringFactory.mapToString(null));
        assertEquals(AString.Null, AStringFactory.mapToString(AString.Null));
    }

    @Test
    public void mapToString_should_be_identity_on_Format() {
        AString aString = AStringFactory.formatWithAString(Provider.AppId.toAString(), 1);
        assertSame(aString, AStringFactory.mapToString(aString));
    }

    @Test
    public void mapToString_should_be_Wrapper_on_Wrapper() {
        AString aString = AStringFactory.createFromCharSequence("value");
        assertSame(aString, AStringFactory.mapToString(aString));
        assertEquals(aString, AStringFactory.mapToString(
                AStringFactory.createFromCharSequence(new StringBuilder("value"))));
    }

    @Test
    public void AppId_should_return_AppId_provider_wrap() {
        assertEquals(Provider.AppId.toAString(), AStringFactory.getAppId());
    }

    @Test
    public void AppVersion_should_return_AppVersion_provider_wrap() {
        assertEquals(Provider.AppVersion.toAString(), AStringFactory.getAppVersion());
    }
}
