//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat;

import static androidx.appcompat.widget.TooltipCompat.setTooltipText;
import static xyz.tynn.astring.appcompat.test.MockKt.clearAll;
import static xyz.tynn.astring.appcompat.test.MockKt.init;
import static xyz.tynn.astring.appcompat.test.MockKt.prepare;
import static xyz.tynn.astring.appcompat.test.MockKt.verify;
import static xyz.tynn.astring.appcompat.test.MockkAppCompatKt.mockkTextUtilsIsEmpty;

import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.TooltipCompat;

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
        prepare(TooltipCompat.class);
        prepare(TextUtils.class);
        mockkTextUtilsIsEmpty();
    }

    @After
    public void teardown() {
        clearAll();
    }

    @Test
    public void setTooltipText_should_delegate_to_TooltipCompat() {
        AStringView.setTooltipText(view, aString);

        verify(() -> setTooltipText(view, aString.invoke(view.getContext())));
    }
}
