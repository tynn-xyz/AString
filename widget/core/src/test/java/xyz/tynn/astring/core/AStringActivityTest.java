//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static xyz.tynn.astring.core.test.MockKt.init;
import static xyz.tynn.astring.core.test.MockKt.verify;

import android.app.Activity;

import org.junit.Before;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import xyz.tynn.astring.AString;

public class AStringActivityTest {

    @MockK
    AString aString;

    @MockK
    Activity activity;

    @Before
    public void setup() {
        init(this, true);
    }

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
