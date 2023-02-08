//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class AStringFactoryTest {

    @Test
    public void nullAsAString_should_return_NullValueProvider() {
        assertSame(AStringFactory.nullAsAString, AStringFactory.nullAsAString);
    }

    @Test
    public void createFromCharSequence_should_return_NullValueProvider() {
        assertSame(AStringFactory.nullAsAString, AStringFactory.createFromCharSequence(null));
    }

    @Test
    public void createFromQuantityStringResource_should_return_NullValueProvider() {
        assertSame(AStringFactory.nullAsAString, AStringFactory.createFromQuantityStringResource(0, 1));
        assertSame(AStringFactory.nullAsAString, AStringFactory.createFromQuantityStringResource(0, 1, 2));
    }

    @Test
    public void createFromQuantityStringResource_with_null_args_should_return_QuantityStringResource() {
        assertEquals(AStringFactory.createFromQuantityStringResource(1, 2),
                AStringFactory.createFromQuantityStringResource(1, 2, (Object[]) null));
    }

    @Test
    public void createFromQuantityTextResource_should_return_NullValueProvider() {
        assertSame(AStringFactory.nullAsAString, AStringFactory.createFromQuantityTextResource(0, 1));
    }

    @Test
    public void createFromStringResource_should_return_NullValueProvider() {
        assertSame(AStringFactory.nullAsAString, AStringFactory.createFromStringResource(0));
        assertSame(AStringFactory.nullAsAString, AStringFactory.createFromStringResource(0, 1));
    }

    @Test
    public void createFromStringResource_with_null_args_should_return_StringResource() {
        assertEquals(AStringFactory.createFromStringResource(1),
                AStringFactory.createFromStringResource(1, (Object[]) null));
    }

    @Test
    public void createFromTextResource_should_return_NullValueProvider() {
        assertSame(AStringFactory.nullAsAString, AStringFactory.createFromTextResource(0));
    }

    @Test
    public void appIdAString_should_return_AppIdProvider() {
        assertSame(ContextValueProvider.AppIdProvider, AStringFactory.appIdAString);
    }

    @Test
    public void appVersionAString_should_return_AppVersionProvider() {
        assertSame(ContextValueProvider.AppVersionProvider, AStringFactory.appVersionAString);
    }
}
