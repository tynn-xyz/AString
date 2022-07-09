//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static xyz.tynn.astring.testing.mockk.MockKt.init;
import static xyz.tynn.astring.testing.mockk.MockKt.verify;

import android.view.View;

import androidx.fragment.app.Fragment;

import org.junit.Before;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;

public class AStringKtTest {

    @MockK
    AString aString;

    @MockK
    Fragment fragment;
    @MockK
    View view;

    @Before
    public void setup() {
        init(this, true);
    }

    @Test
    public void invoke_should_delegate_to_fragment() {
        AStringKt.invoke(aString, fragment);

        verify(() -> aString.invoke(fragment.requireContext()));
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
        AStringKt.invoke(aString, view);

        verify(() -> aString.invoke(view.getContext()));
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
