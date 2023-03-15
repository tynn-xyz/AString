//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static xyz.tynn.astring.core.test.MockKt.verify;

import android.widget.TextSwitcher;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringTextSwitcherTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    TextSwitcher view;

    @Test
    public void setCurrentText_should_delegate_to_view() {
        AStringTextSwitcher.setCurrentText(view, aString);

        verify(() -> view.setCurrentText(aString.invoke(view.getContext())));
    }

    @Test
    public void setCurrentText_should_delegate_null_to_view() {
        AStringTextSwitcher.setCurrentText(view, null);

        verify(() -> view.setCurrentText(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setCurrentText_should_throw_on_null_view() {
        AStringTextSwitcher.setCurrentText(null, aString);
    }

    @Test
    public void setText_should_delegate_to_view() {
        AStringTextSwitcher.setText(view, aString);

        verify(() -> view.setText(aString.invoke(view.getContext())));
    }

    @Test
    public void setText_should_delegate_null_to_view() {
        AStringTextSwitcher.setText(view, null);

        verify(() -> view.setText(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_view() {
        AStringTextSwitcher.setText(null, aString);
    }
}
