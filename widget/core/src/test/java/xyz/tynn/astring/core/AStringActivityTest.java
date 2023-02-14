//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static xyz.tynn.astring.core.test.MockKt.verify;

import android.app.Activity;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringActivityTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    Activity activity;

    @Test
    public void setTitle_should_delegate_to_activity() {
        AStringActivity.setTitle(activity, aString);

        verify(() -> activity.setTitle(aString.invoke(activity)));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTitle_should_throw_on_null_dialog() {
        AStringActivity.setTitle(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTitle_should_throw_on_null_string() {
        AStringActivity.setTitle(activity, null);
    }
}
