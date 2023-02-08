//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static xyz.tynn.astring.core.test.MockKt.init;
import static xyz.tynn.astring.core.test.MockKt.verify;

import android.widget.ToggleButton;

import org.junit.Before;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import xyz.tynn.astring.AString;

public class AStringToggleButtonTest {

    @MockK
    AString aString;

    @MockK
    ToggleButton view;

    @Before
    public void setup() {
        init(this, true);
    }

    @Test
    public void setTextOff_should_delegate_to_view() {
        AStringToggleButton.setTextOff(view, aString);

        verify(() -> view.setTextOff(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOff_should_throw_on_null_view() {
        AStringToggleButton.setTextOff(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOff_should_throw_on_null_string() {
        AStringToggleButton.setTextOff(view, null);
    }

    @Test
    public void setTextOn_should_delegate_to_view() {
        AStringToggleButton.setTextOn(view, aString);

        verify(() -> view.setTextOn(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOn_should_throw_on_null_view() {
        AStringToggleButton.setTextOn(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOn_should_throw_on_null_string() {
        AStringToggleButton.setTextOn(view, null);
    }
}
