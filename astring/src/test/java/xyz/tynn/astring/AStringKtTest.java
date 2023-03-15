//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static io.mockk.MockKKt.every;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;

public class AStringKtTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @MockK
    Context context;
    @RelaxedMockK
    Fragment fragment;
    @RelaxedMockK
    View view;

    @Test
    public void invoke_should_delegate_to_context() {
        every(scope -> aString.invoke(context)).returns("context");

        assertEquals("context", AStringKt.invoke(context, aString));
    }

    @Test
    public void invoke_should_delegate_null_to_context() {
        assertNull(AStringKt.invoke(context, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void invoke_should_throw_on_null_context() {
        AStringKt.invoke((Context) null, aString);
    }

    @Test
    public void invoke_should_delegate_to_fragment() {
        every(scope -> aString.invoke(fragment.requireContext())).returns("fragment");

        assertEquals("fragment", AStringKt.invoke(fragment, aString));
    }

    @Test
    public void invoke_should_delegate_null_to_fragment() {
        assertNull(AStringKt.invoke(fragment, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void invoke_should_throw_on_null_fragment() {
        AStringKt.invoke((Fragment) null, aString);
    }

    @Test
    public void invoke_should_delegate_to_view() {
        every(scope -> aString.invoke(view.getContext())).returns("view");

        assertEquals("view", AStringKt.invoke(view, aString));
    }

    @Test
    public void invoke_should_delegate_null_to_view() {
        assertNull(AStringKt.invoke(view, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void invoke_should_throw_on_null_view() {
        AStringKt.invoke((View) null, aString);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void deprecated_invoke_should_delegate_to_fragment() {
        every(scope -> aString.invoke(fragment.requireContext())).returns("fragment");

        assertEquals("fragment", AStringKt.invoke(aString, fragment));
    }

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Test(expected = NullPointerException.class)
    public void deprecated_invoke_fragment_should_throw_on_null_string_with() {
        AStringKt.invoke(null, fragment);
    }

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Test(expected = NullPointerException.class)
    public void deprecated_invoke_should_throw_on_null_fragment() {
        AStringKt.invoke(aString, (Fragment) null);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void deprecated_invoke_should_delegate_to_view() {
        every(scope -> aString.invoke(view.getContext())).returns("view");

        assertEquals("view", AStringKt.invoke(aString, view));
    }

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Test(expected = NullPointerException.class)
    public void deprecated_invoke_with_view_should_throw_on_null_string() {
        AStringKt.invoke(null, view);
    }

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Test(expected = NullPointerException.class)
    public void deprecated_invoke_should_throw_on_null_view() {
        AStringKt.invoke(aString, (View) null);
    }
}
