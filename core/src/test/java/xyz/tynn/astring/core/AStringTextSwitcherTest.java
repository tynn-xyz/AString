//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import android.widget.TextSwitcher;

import org.junit.Before;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import xyz.tynn.astring.AString;

import static xyz.tynn.astring.testing.mockk.MockKt.init;
import static xyz.tynn.astring.testing.mockk.MockKt.verify;

public class AStringTextSwitcherTest {

    @MockK
    AString aString;

    @MockK
    TextSwitcher view;

    @Before
    public void setup() {
        init(this, true);
    }

    @Test
    public void setCurrentText_should_delegate_to_view() {
        AStringTextSwitcher.setCurrentText(view, aString);

        verify(() -> view.setCurrentText(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setCurrentText_should_throw_on_null_view() {
        AStringTextSwitcher.setCurrentText(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setCurrentText_should_throw_on_null_string() {
        AStringTextSwitcher.setCurrentText(view, null);
    }

    @Test
    public void setText_should_delegate_to_view() {
        AStringTextSwitcher.setText(view, aString);

        verify(() -> view.setText(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_view() {
        AStringTextSwitcher.setText(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_string() {
        AStringTextSwitcher.setText(view, null);
    }
}
