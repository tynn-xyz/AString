//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static org.junit.Assert.assertEquals;
import static io.mockk.MockKKt.every;
import static xyz.tynn.astring.core.test.MockKt.prepare;

import android.content.Context;
import android.widget.Toast;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringToastTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    Toast toast;

    @MockK
    Context context;

    @Test
    public void makeText_should_delegate_to_toast() {
        prepare(Toast.class);
        every(scope -> makeText(context, aString.invoke(context), LENGTH_LONG)).returns(toast);

        assertEquals(toast,
                AStringToast.makeText(context, aString, LENGTH_LONG));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void makeText_should_throw_on_null_string() {
        AStringToast.makeText(context, null, LENGTH_LONG);
    }
}
