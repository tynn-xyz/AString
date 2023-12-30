//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static xyz.tynn.astring.AStringFactory.createAString;
import static xyz.tynn.astring.AStringFactory.mapAString;

import androidx.annotation.Nullable;

import org.junit.Test;

public class AStringTransformerTest {

    private final AString aString = createAString(context -> "");

    @Test
    public void delegate_should_wrap_null_AString() {
        AString.Transformer function = value -> value;
        assertEquals(Delegate.wrap(AString.Null, function),
                Delegate.wrap(null, function));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void map_should_wrap_null_AString() {
        AString.Transformer function = value -> value;
        assertEquals(
                Delegate.wrap(AString.Null, function),
                mapAString(null, function)
        );
    }

    @Test
    @SuppressWarnings("Convert2Lambda")
    public void interface_should_not_be_efficient() {
        assertNotEquals(mapAString(aString, new AString.Transformer() {
            @Override
            public CharSequence invoke(@Nullable CharSequence value) {
                return "";
            }
        }), mapAString(aString, new AString.Transformer() {
            @Override
            public CharSequence invoke(@Nullable CharSequence value) {
                return "";
            }
        }));
    }

    @Test
    @SuppressWarnings("Convert2Lambda")
    public void interface_ref_should_be_efficient() {
        AString.Transformer function = new AString.Transformer() {
            @Override
            public CharSequence invoke(@Nullable CharSequence value) {
                return "";
            }
        };
        assertEquals(mapAString(aString, function), mapAString(aString, function));
    }

    @Test
    public void instance_should_be_efficient() {
        assertEquals(mapAString(aString, new Transformer()), mapAString(aString, new Transformer()));
    }

    @Test
    public void instance_ref_should_be_efficient() {
        AString.Transformer function = new Transformer();
        assertEquals(mapAString(aString, function), mapAString(aString, function));
    }

    @Test
    public void function_should_not_be_efficient() {
        assertNotEquals(mapAString(aString, this::function), mapAString(aString, this::function));
    }

    @Test
    public void function_ref_should_be_efficient() {
        AString.Transformer function = this::function;
        assertEquals(mapAString(aString, function), mapAString(aString, function));
    }

    @Test
    public void lambda_should_not_be_efficient() {
        assertNotEquals(mapAString(aString, value -> ""), mapAString(aString, value -> ""));
    }

    @Test
    public void lambda_ref_should_be_efficient() {
        AString.Transformer function = value -> "";
        assertEquals(mapAString(aString, function), mapAString(aString, function));
    }

    @Test
    public void delegate_should_return_Null_on_null_transformer() {
        assertEquals(AString.Null, Delegate.wrap((AString) null, null));
    }

    private CharSequence function(CharSequence value) {
        return "";
    }

    private final static class Transformer implements AString.Transformer {

        @Nullable
        @Override
        public CharSequence invoke(@Nullable CharSequence value) {
            return null;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            return obj instanceof Transformer;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
