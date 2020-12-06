//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.view.View;
import androidx.core.view.ViewCompat;
import io.mockk.impl.annotations.MockK;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static androidx.core.view.ViewCompat.setAccessibilityPaneTitle;
import static androidx.core.view.ViewCompat.setTooltipText;
import static xyz.tynn.astring.mockk.MockKt.*;

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

    @Test(expected = NullPointerException.class)
    public void setContentDescription_should_throw_on_null_view() {
        AStringView.setContentDescription(null, aString);
    }

    @Test(expected = NullPointerException.class)
    public void setContentDescription_should_throw_on_null_string() {
        AStringView.setContentDescription(view, null);
    }

    @Test
    @Ignore("Requires ViewCompat 1.5.0")
    public void setStateDescription_should_delegate_to_ViewCompat() {
        AStringView.setStateDescription(view, aString);

//        verify(() -> setStateDescription(view, aString.invoke(view.getContext())));
    }

    @Test
    public void setTooltipText_should_delegate_to_ViewCompat() {
        AStringView.setTooltipText(view, aString);

        verify(() -> setTooltipText(view, aString.invoke(view.getContext())));
    }
}
