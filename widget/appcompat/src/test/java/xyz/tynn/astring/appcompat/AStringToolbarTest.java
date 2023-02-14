//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat;

import static xyz.tynn.astring.appcompat.test.MockKt.verify;

import androidx.appcompat.widget.Toolbar;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringToolbarTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    Toolbar view;

    @Test
    public void setLogoDescription_should_delegate_to_view() {
        AStringToolbar.setLogoDescription(view, aString);

        verify(() -> view.setLogoDescription(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setLogoDescription_should_throw_on_null_view() {
        AStringToolbar.setLogoDescription(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setLogoDescription_should_throw_on_null_string() {
        AStringToolbar.setLogoDescription(view, null);
    }

    @Test
    public void setSubtitle_should_delegate_to_view() {
        AStringToolbar.setSubtitle(view, aString);

        verify(() -> view.setSubtitle(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setSubtitle_should_throw_on_null_view() {
        AStringToolbar.setSubtitle(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setSubtitle_should_throw_on_null_string() {
        AStringToolbar.setSubtitle(view, null);
    }

    @Test
    public void setTitle_should_delegate_to_view() {
        AStringToolbar.setTitle(view, aString);

        verify(() -> view.setTitle(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTitle_should_throw_on_null_view() {
        AStringToolbar.setTitle(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTitle_should_throw_on_null_string() {
        AStringToolbar.setTitle(view, null);
    }
}
