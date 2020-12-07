//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import android.widget.Switch;
import io.mockk.impl.annotations.MockK;
import org.junit.Before;
import org.junit.Test;
import xyz.tynn.astring.AString;

import static xyz.tynn.astring.testing.mockk.MockKt.init;
import static xyz.tynn.astring.testing.mockk.MockKt.verify;

public class AStringSwitchTest {

    @MockK
    AString aString;

    @MockK
    Switch view;

    @Before
    public void setup() {
        init(this, true);
    }

    @Test
    public void setTextOff_should_delegate_to_view() {
        AStringSwitch.setTextOff(view, aString);

        verify(() -> view.setTextOff(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOff_should_throw_on_null_view() {
        AStringSwitch.setTextOff(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOff_should_throw_on_null_string() {
        AStringSwitch.setTextOff(view, null);
    }

    @Test
    public void setTextOn_should_delegate_to_view() {
        AStringSwitch.setTextOn(view, aString);

        verify(() -> view.setTextOn(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOn_should_throw_on_null_view() {
        AStringSwitch.setTextOn(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOn_should_throw_on_null_string() {
        AStringSwitch.setTextOn(view, null);
    }
}
