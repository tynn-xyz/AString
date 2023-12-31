//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class AStringFactoryTest {

    @Test
    @SuppressWarnings("EqualsWithItself")
    public void Null_should_return_Null() {
        assertSame(AString.Null, AString.Null);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void createAString_should_return_NULL() {
        assertSame(AString.Null, AStringFactory.createAString(null));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void mapAString_should_return_NULL() {
        assertSame(AString.Null, AStringFactory.mapAString(AString.Null, null));
    }

    @Test
    public void ensureNonNull_should_return_NULL() {
        assertSame(AString.Null, AStringFactory.ensureNonNull(null));
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
    public void format_without_args_should_return_Format() {
        assertEquals(AStringFactory.formatWithAString(AString.Null),
                AStringFactory.formatWithAString(AString.Null, (Object[]) null));
    }

    @Test
    public void format_without_locale_should_return_Format() {
        assertEquals(AStringFactory.formatWithAString(AString.Null),
                AStringFactory.formatWithAString(AString.Null, null, (Object[]) null));
        assertEquals(AStringFactory.formatWithAString(AString.Null, 1, "2"),
                AStringFactory.formatWithAString(AString.Null, null, 1, "2"));
    }

    @Test
    public void string_should_be_NULL_on_null() {
        assertEquals(AString.Null, AStringFactory.mapCharSequenceToString(null));
        assertEquals(AString.Null, AStringFactory.mapCharSequenceToString(AString.Null));
    }

    @Test
    public void trim_should_be_NULL_on_null() {
        assertEquals(AString.Null, AStringFactory.trimCharSequence(null));
        assertEquals(AString.Null, AStringFactory.trimCharSequence(AString.Null));
    }

    @Test
    public void mapBlankToNull_should_be_NULL_on_null() {
        assertEquals(AString.Null, AStringFactory.mapBlankToNull(null));
        assertEquals(AString.Null, AStringFactory.mapBlankToNull(AString.Null));
    }

    @Test
    public void mapEmptyToNull_should_be_NULL_on_null() {
        assertEquals(AString.Null, AStringFactory.mapEmptyToNull(null));
        assertEquals(AString.Null, AStringFactory.mapEmptyToNull(AString.Null));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void mapNullToDefault_should_not_be_null_on_null() {
        assertNotNull(AStringFactory.mapNullToDefault(null, (CharSequence) null));
        assertNotNull(AStringFactory.mapNullToDefault(null, (AString) null));
    }

    @Test
    public void AppId_should_return_AppId_provider_wrap() {
        assertEquals(Delegate.wrap(Provider.AppId), AStringFactory.getAppId());
    }

    @Test
    public void AppVersion_should_return_AppVersion_provider_wrap() {
        assertEquals(Delegate.wrap(Provider.AppVersion), AStringFactory.getAppVersion());
    }
}
