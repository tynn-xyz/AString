//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static androidx.core.view.ViewCompat.setAccessibilityPaneTitle;
import static androidx.core.view.ViewCompat.setStateDescription;
import static androidx.core.view.ViewCompat.setTooltipText;
import static xyz.tynn.astring.core.test.MockKt.prepare;
import static xyz.tynn.astring.core.test.MockKt.verify;

import android.view.View;

import androidx.core.view.ViewCompat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringViewTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    View view;

    @Before
    public void setup() {
        prepare(ViewCompat.class);
    }

    @Test
    public void setAccessibilityPaneTitle_should_delegate_to_ViewCompat() {
        AStringView.setAccessibilityPaneTitle(view, aString);

        verify(() -> setAccessibilityPaneTitle(view, aString.invoke(view.getContext())));
    }

    @Test
    public void setAccessibilityPaneTitle_should_delegate_null_to_ViewCompat() {
        AStringView.setAccessibilityPaneTitle(view, null);

        verify(() -> setAccessibilityPaneTitle(view, null));
    }

    @Test
    public void setContentDescription_should_delegate_to_view() {
        AStringView.setContentDescription(view, aString);

        verify(() -> view.setContentDescription(aString.invoke(view.getContext())));
    }

    @Test
    public void setContentDescription_should_delegate_null_to_view() {
        AStringView.setContentDescription(view, null);

        verify(() -> view.setContentDescription(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setContentDescription_should_throw_on_null_view() {
        AStringView.setContentDescription(null, aString);
    }

    @Test
    public void setStateDescription_should_delegate_to_ViewCompat() {
        AStringView.setStateDescription(view, aString);

        verify(() -> setStateDescription(view, aString.invoke(view.getContext())));
    }

    @Test
    public void setStateDescription_should_delegate_null_to_ViewCompat() {
        AStringView.setStateDescription(view, null);

        verify(() -> setStateDescription(view, null));
    }

    @Test
    public void setTooltipText_should_delegate_to_ViewCompat() {
        AStringView.setTooltipText(view, aString);

        verify(() -> setTooltipText(view, aString.invoke(view.getContext())));
    }

    @Test
    public void setTooltipText_should_delegate_null_to_ViewCompat() {
        AStringView.setTooltipText(view, null);

        verify(() -> setTooltipText(view, null));
    }
}
