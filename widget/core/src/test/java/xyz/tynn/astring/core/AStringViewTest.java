//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static androidx.core.view.ViewCompat.setAccessibilityPaneTitle;
import static androidx.core.view.ViewCompat.setStateDescription;
import static androidx.core.view.ViewCompat.setTooltipText;
import static xyz.tynn.astring.core.test.MockKt.clearAll;
import static xyz.tynn.astring.core.test.MockKt.init;
import static xyz.tynn.astring.core.test.MockKt.prepare;
import static xyz.tynn.astring.core.test.MockKt.verify;

import android.view.View;

import androidx.core.view.ViewCompat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import xyz.tynn.astring.AString;

public class AStringViewTest {

    @MockK
    AString aString;

    @MockK
    View view;

    @Before
    public void setup() {
        init(this, true);
        prepare(ViewCompat.class);
    }

    @After
    public void teardown() {
        clearAll();
    }

    @Test
    public void setAccessibilityPaneTitle_should_delegate_to_ViewCompat() {
        AStringView.setAccessibilityPaneTitle(view, aString);

        verify(() -> setAccessibilityPaneTitle(view, aString.invoke(view.getContext())));
    }

    @Test
    public void setContentDescription_should_delegate_to_view() {
        AStringView.setContentDescription(view, aString);

        verify(() -> view.setContentDescription(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setContentDescription_should_throw_on_null_view() {
        AStringView.setContentDescription(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setContentDescription_should_throw_on_null_string() {
        AStringView.setContentDescription(view, null);
    }

    @Test
    public void setStateDescription_should_delegate_to_ViewCompat() {
        AStringView.setStateDescription(view, aString);

        verify(() -> setStateDescription(view, aString.invoke(view.getContext())));
    }

    @Test
    public void setTooltipText_should_delegate_to_ViewCompat() {
        AStringView.setTooltipText(view, aString);

        verify(() -> setTooltipText(view, aString.invoke(view.getContext())));
    }
}
