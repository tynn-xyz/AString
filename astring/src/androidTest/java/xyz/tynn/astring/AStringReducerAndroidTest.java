//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static xyz.tynn.astring.AStringFactory.createFromCharSequence;
import static xyz.tynn.astring.test.AStringAssert.assertParcelableAStringInvocation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.junit.Test;

import java.util.List;

public class AStringReducerAndroidTest {

    private final Iterable<AString> aStrings = List.of(AString.Null);

    @Test
    public void delegate_should_implement_parcelable() {
        String value = "value";
        assertParcelableAStringInvocation(Delegate.wrap($ -> value, aStrings));
        assertParcelableAStringInvocation(Delegate.wrap($ -> value,
                AString.Null, createFromCharSequence("value")));
    }

    @Test
    @SuppressWarnings("Convert2Lambda")
    public void interface_should_not_be_efficient() {
        assertNotEquals(AStringFactory.reduce(aStrings, new AString.Reducer() {
            @Override
            public CharSequence invoke(@NonNull Iterable<? extends CharSequence> values) {
                return "";
            }
        }), AStringFactory.reduce(aStrings, new AString.Reducer() {
            @Override
            public CharSequence invoke(@NonNull Iterable<? extends CharSequence> values) {
                return "";
            }
        }));
    }

    @Test
    @SuppressWarnings("Convert2Lambda")
    public void interface_ref_should_be_efficient() {
        AString.Reducer function = new AString.Reducer() {
            @Override
            public CharSequence invoke(@NonNull Iterable<? extends CharSequence> values) {
                return "";
            }
        };
        assertEquals(AStringFactory.reduce(aStrings, function),
                AStringFactory.reduce(aStrings, function));
    }

    @Test
    public void instance_should_be_efficient() {
        assertEquals(AStringFactory.reduce(aStrings, new Reducer()),
                AStringFactory.reduce(aStrings, new Reducer()));
    }

    @Test
    public void instance_ref_should_be_efficient() {
        AString.Reducer function = new Reducer();
        assertEquals(AStringFactory.reduce(aStrings, function),
                AStringFactory.reduce(aStrings, function));
    }

    @Test
    public void function_should_not_be_efficient() {
        assertNotEquals(AStringFactory.reduce(aStrings, this::function),
                AStringFactory.reduce(aStrings, this::function));
    }

    @Test
    public void function_ref_should_be_efficient() {
        AString.Reducer function = this::function;
        assertEquals(AStringFactory.reduce(aStrings, function),
                AStringFactory.reduce(aStrings, function));
    }

    @Test
    public void lambda_should_not_be_efficient() {
        assertNotEquals(AStringFactory.reduce(aStrings, values -> ""),
                AStringFactory.reduce(aStrings, values -> ""));
    }

    @Test
    public void lambda_ref_should_be_efficient() {
        AString.Reducer function = values -> "";
        assertEquals(AStringFactory.reduce(aStrings, function),
                AStringFactory.reduce(aStrings, function));
    }

    private CharSequence function(Iterable<? extends CharSequence> values) {
        return "";
    }

    private final static class Reducer implements AString.Reducer {

        @Nullable
        @Override
        public CharSequence invoke(@NonNull Iterable<? extends CharSequence> values) {
            return null;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            return obj instanceof Reducer;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
