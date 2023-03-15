//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static xyz.tynn.astring.core.test.MockKt.verify;

import android.widget.ToggleButton;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringToggleButtonTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    ToggleButton view;

    @Test
    public void setTextOff_should_delegate_to_view() {
        AStringToggleButton.setTextOff(view, aString);

        verify(() -> view.setTextOff(aString.invoke(view.getContext())));
    }

    @Test
    public void setTextOff_should_delegate_null_to_view() {
        AStringToggleButton.setTextOff(view, null);

        verify(() -> view.setTextOff(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOff_should_throw_on_null_view() {
        AStringToggleButton.setTextOff(null, aString);
    }

    @Test
    public void setTextOn_should_delegate_to_view() {
        AStringToggleButton.setTextOn(view, aString);

        verify(() -> view.setTextOn(aString.invoke(view.getContext())));
    }

    @Test
    public void setTextOn_should_delegate_null_to_view() {
        AStringToggleButton.setTextOn(view, null);

        verify(() -> view.setTextOn(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOn_should_throw_on_null_view() {
        AStringToggleButton.setTextOn(null, aString);
    }
}
