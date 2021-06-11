//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import android.content.Context;
import android.widget.Toast;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import xyz.tynn.astring.AString;

import static android.widget.Toast.LENGTH_LONG;
import static org.junit.Assert.assertEquals;
import static xyz.tynn.astring.core.MockkDefinitionsKt.mockkToastMakeText;
import static xyz.tynn.astring.testing.mockk.MockKt.clearAll;
import static xyz.tynn.astring.testing.mockk.MockKt.init;
import static xyz.tynn.astring.testing.mockk.MockKt.prepare;
import static xyz.tynn.astring.testing.mockk.MockKt.verify;

public class AStringToastTest {

    @MockK
    AString aString;

    @MockK
    Toast toast;

    @MockK
    Context context;

    @Before
    public void setup() {
        init(this, true);
        prepare(Toast.class);
        mockkToastMakeText(toast);
    }

    @After
    public void teardown() {
        clearAll();
    }

    @Test
    public void makeText_should_delegate_to_toast() {
        assertEquals(toast,
                AStringToast.makeText(context, aString, LENGTH_LONG));

        verify(() -> Toast.makeText(context, aString.invoke(context), LENGTH_LONG));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void makeText_should_throw_on_null_string() {
        AStringToast.makeText(context, null, LENGTH_LONG);
    }

    @Test
    public void setText_should_delegate_to_toast() {
        AStringToast.setText(toast, context, aString);

        verify(() -> toast.setText(aString.invoke(context)));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_toast() {
        AStringToast.setText(null, context, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_context() {
        AStringToast.setText(toast, null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_string() {
        AStringToast.setText(toast, context, null);
    }
}
