//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static xyz.tynn.astring.test.AStringAssert.assertParcelableAStringInvocation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.junit.Test;

public class AStringProviderAndroidTest {

    @Test
    public void delegate_should_implement_parcelable() {
        String value = "value";
        assertParcelableAStringInvocation(Delegate.wrap($ -> value));
    }

    @Test
    public void delegate_should_implement_parcelable_method() {
        assertParcelableAStringInvocation(AStringFactory.createAString(Context::getPackageName));
    }

    @Test
    @SuppressWarnings("Convert2Lambda")
    public void interface_should_not_be_efficient() {
        assertNotEquals(AStringFactory.createAString(new AStringProvider() {
            @Override
            public CharSequence invoke(@NonNull Context context) {
                return "";
            }
        }), AStringFactory.createAString(new AStringProvider() {
            @Override
            public CharSequence invoke(@NonNull Context context) {
                return "";
            }
        }));
    }

    @Test
    @SuppressWarnings("Convert2Lambda")
    public void interface_ref_should_be_efficient() {
        AStringProvider function = new AStringProvider() {
            @Override
            public CharSequence invoke(@NonNull Context context) {
                return "";
            }
        };
        assertEquals(AStringFactory.createAString(function),
                AStringFactory.createAString(function));
    }

    @Test
    public void instance_should_be_efficient() {
        assertEquals(AStringFactory.createAString(new Provider()),
                AStringFactory.createAString(new Provider()));
    }

    @Test
    public void instance_ref_should_be_efficient() {
        AStringProvider function = new Provider();
        assertEquals(AStringFactory.createAString(function),
                AStringFactory.createAString(function));
    }

    @Test
    public void function_should_not_be_efficient() {
        assertNotEquals(AStringFactory.createAString(this::function),
                AStringFactory.createAString(this::function));
    }

    @Test
    public void function_ref_should_be_efficient() {
        AStringProvider function = this::function;
        assertEquals(AStringFactory.createAString(function),
                AStringFactory.createAString(function));
    }

    @Test
    public void lambda_should_not_be_efficient() {
        assertNotEquals(AStringFactory.createAString(context -> ""),
                AStringFactory.createAString(context -> ""));
    }

    @Test
    public void lambda_ref_should_be_efficient() {
        AStringProvider function = context -> "";
        assertEquals(AStringFactory.createAString(function),
                AStringFactory.createAString(function));
    }

    private CharSequence function(Context context) {
        return "";
    }

    private final static class Provider implements AStringProvider {

        @Nullable
        @Override
        public CharSequence invoke(@NonNull Context context) {
            return null;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            return obj instanceof Provider;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
