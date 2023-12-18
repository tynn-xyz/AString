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
    public void invokeWithContext_should_delegate_to_context() {
        every(scope -> aString.invoke(context)).returns("context");
        assertEquals("context", AStringKt.invokeWithContext(context, aString));
    }

    @Test
    public void invokeWithContext_should_delegate_null_to_context() {
        assertNull(AStringKt.invokeWithContext(context, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void invokeWithContext_should_throw_on_null_context() {
        AStringKt.invokeWithContext((Context) null, aString);
    }

    @Test
    public void invokeWithFragment_should_delegate_to_fragment() {
        every(scope -> aString.invoke(fragment.requireContext())).returns("fragment");

        assertEquals("fragment", AStringKt.invokeWithFragment(fragment, aString));
    }

    @Test
    public void invokeWithFragment_should_delegate_null_to_fragment() {
        assertNull(AStringKt.invokeWithFragment(fragment, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void invokeWithFragment_should_throw_on_null_fragment() {
        AStringKt.invokeWithFragment(null, aString);
    }

    @Test(expected = IllegalStateException.class)
    public void invokeWithFragment_should_throw_on_null_context() {
        every(scope -> fragment.requireContext()).answers((scope, $) -> scope.callOriginal());
        every(scope -> fragment.getContext()).returns(null);
        AStringKt.invokeWithFragment(fragment, aString);
    }

    @Test
    public void invokeWithView_should_delegate_to_view() {
        every(scope -> aString.invoke(view.getContext())).returns("view");
        assertEquals("view", AStringKt.invokeWithView(view, aString));
    }

    @Test
    public void invokeWithView_should_delegate_null_to_view() {
        assertNull(AStringKt.invokeWithView(view, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void invokeWithView_should_throw_on_null_view() {
        AStringKt.invokeWithView((View) null, aString);
    }
}
