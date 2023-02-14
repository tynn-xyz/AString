//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static xyz.tynn.astring.core.test.MockKt.verify;

import android.widget.Switch;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringSwitchTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    Switch view;

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
