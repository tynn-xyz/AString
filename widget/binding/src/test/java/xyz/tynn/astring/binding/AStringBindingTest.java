//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.binding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static io.mockk.MockKKt.every;
import static xyz.tynn.astring.AStringFactory.createFromCharSequence;
import static xyz.tynn.astring.AStringFactory.nullAsAString;

import android.content.Context;

import androidx.databinding.InverseMethod;

import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.Method;

import io.mockk.impl.annotations.MockK;
import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringBindingTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @MockK
    Context context;

    @SuppressWarnings("ConstantConditions")
    @Test
    public void load_should_return_null_on_null_astring() {
        assertNull(AStringBinding.load(null, null));
        assertNull(AStringBinding.load(context, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void load_should_throw_on_null_view() {
        AStringBinding.load(null, aString);
    }

    @Test
    public void load_should_invoke_AString() {
        every(scope -> aString.invoke(context)).returns("aString");

        assertEquals("aString",
                AStringBinding.load(context, aString));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void wrap_should_return_nullAsAString_on_null_astring() {
        assertSame(nullAsAString,
                AStringBinding.wrap(null, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void wrap_should_createFromCharSequence() {
        assertEquals(createFromCharSequence("string"),
                AStringBinding.wrap(null, "string"));
    }

    @Test
    public void wrap_should_invert_load() throws Exception {
        Method load = AStringBinding.class.getMethod("load", Context.class, AString.class);

        @SuppressWarnings("ConstantConditions")
        String inverse = load.getAnnotation(InverseMethod.class).value();

        assertEquals(AStringBinding.class.getMethod("wrap", Context.class, String.class),
                AStringBinding.class.getMethod(inverse, Context.class, String.class));
    }
}
