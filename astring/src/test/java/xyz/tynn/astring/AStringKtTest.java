//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static org.junit.Assert.assertEquals;
import static io.mockk.MockKKt.every;

import android.view.View;

import androidx.fragment.app.Fragment;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;

public class AStringKtTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    Fragment fragment;
    @RelaxedMockK
    View view;

    @Test
    public void invoke_should_delegate_to_fragment() {
        every(scope -> aString.invoke(fragment.requireContext())).returns("fragment");

        assertEquals("fragment", AStringKt.invoke(aString, fragment));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void invoke_fragment_should_throw_on_null_string_with() {
        AStringKt.invoke(null, fragment);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void invoke_should_throw_on_null_fragment() {
        AStringKt.invoke(aString, (Fragment) null);
    }

    @Test
    public void invoke_should_delegate_to_view() {
        every(scope -> aString.invoke(view.getContext())).returns("view");

        assertEquals("view", AStringKt.invoke(aString, view));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void invoke_with_view_should_throw_on_null_string() {
        AStringKt.invoke(null, view);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void invoke_should_throw_on_null_view() {
        AStringKt.invoke(aString, (View) null);
    }
}
