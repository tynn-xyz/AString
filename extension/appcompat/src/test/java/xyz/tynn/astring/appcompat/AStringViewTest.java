//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat;

import static androidx.appcompat.widget.TooltipCompat.setTooltipText;
import static xyz.tynn.astring.appcompat.test.MockKt.justRun;
import static xyz.tynn.astring.appcompat.test.MockKt.prepare;

import android.view.View;

import androidx.appcompat.widget.TooltipCompat;

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

    @Test
    public void setTooltipText_should_delegate_to_TooltipCompat() {
        prepare(TooltipCompat.class);
        justRun(() -> setTooltipText(view, aString.invoke(view.getContext())));

        AStringView.setTooltipText(view, aString);
    }
}
