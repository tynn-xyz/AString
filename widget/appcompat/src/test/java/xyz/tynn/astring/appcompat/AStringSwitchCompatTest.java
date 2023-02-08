//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat;

import static xyz.tynn.astring.appcompat.test.MockKt.init;
import static xyz.tynn.astring.appcompat.test.MockKt.verify;

import androidx.appcompat.widget.SwitchCompat;

import org.junit.Before;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import xyz.tynn.astring.AString;

public class AStringSwitchCompatTest {

    @MockK
    AString aString;

    @MockK
    SwitchCompat view;

    @Before
    public void setup() {
        init(this, true);
    }

    @Test
    public void setTextOff_should_delegate_to_view() {
        AStringSwitchCompat.setTextOff(view, aString);

        verify(() -> view.setTextOff(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOff_should_throw_on_null_view() {
        AStringSwitchCompat.setTextOff(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOff_should_throw_on_null_string() {
        AStringSwitchCompat.setTextOff(view, null);
    }

    @Test
    public void setTextOn_should_delegate_to_view() {
        AStringSwitchCompat.setTextOn(view, aString);

        verify(() -> view.setTextOn(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOn_should_throw_on_null_view() {
        AStringSwitchCompat.setTextOn(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTextOn_should_throw_on_null_string() {
        AStringSwitchCompat.setTextOn(view, null);
    }
}