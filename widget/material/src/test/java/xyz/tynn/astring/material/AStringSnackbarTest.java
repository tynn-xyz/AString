//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.material;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static org.junit.Assert.assertSame;
import static xyz.tynn.astring.material.test.MockKt.clearAll;
import static xyz.tynn.astring.material.test.MockKt.init;
import static xyz.tynn.astring.material.test.MockKt.prepare;
import static xyz.tynn.astring.material.test.MockKt.verify;
import static xyz.tynn.astring.material.test.MockkMaterialKt.mockkAStringInvoke;
import static xyz.tynn.astring.material.test.MockkMaterialKt.mockkGetMainLooper;
import static xyz.tynn.astring.material.test.MockkMaterialKt.mockkSnackbarMake;

import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.material.snackbar.Snackbar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import xyz.tynn.astring.AString;

public class AStringSnackbarTest {

    @MockK
    AString aString;

    @MockK
    Snackbar snackbar;

    @MockK
    View view;
    @MockK
    OnClickListener listener;

    @Before
    public void setup() {
        prepare(Looper.class);
        mockkGetMainLooper();
        init(this, true);
        prepare(Snackbar.class);
        mockkSnackbarMake(snackbar);
    }

    @After
    public void teardown() {
        clearAll();
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void make_should_delegate_to_snackbar() {
        assertSame(snackbar,
                AStringSnackbar.make(view, aString, LENGTH_LONG));

        verify(() -> Snackbar.make(view, aString.invoke(view.getContext()), LENGTH_LONG));
    }

    @Test
    public void make_should_default_to_empty_string() {
        mockkAStringInvoke(aString, null);

        assertSame(snackbar,
                AStringSnackbar.make(view, aString, LENGTH_LONG));

        verify(() -> Snackbar.make(view, "", LENGTH_LONG));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void make_should_throw_on_null_view() {
        AStringSnackbar.make(null, aString, LENGTH_LONG);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void make_should_throw_on_null_string() {
        AStringSnackbar.make(view, null, LENGTH_LONG);
    }

    @Test
    public void setAction_should_delegate_to_snackbar() {
        AStringSnackbar.setAction(snackbar, aString, listener);

        verify(() -> snackbar.setAction(aString.invoke(snackbar.getContext()), listener));
    }

    @Test
    public void setAction_with_null_listener_should_delegate_to_snackbar() {
        AStringSnackbar.setAction(snackbar, aString, null);

        verify(() -> snackbar.setAction(aString.invoke(snackbar.getContext()), null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setAction_should_throw_on_null_snackbar() {
        AStringSnackbar.setAction(null, aString, listener);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setAction_should_throw_on_null_string() {
        AStringSnackbar.setAction(snackbar, null, listener);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void setText_should_delegate_to_snackbar() {
        AStringSnackbar.setText(snackbar, aString);

        verify(() -> snackbar.setText(aString.invoke(snackbar.getContext())));
    }

    @Test
    public void setText_should_default_to_empty_string() {
        mockkAStringInvoke(aString, null);

        AStringSnackbar.setText(snackbar, aString);

        verify(() -> snackbar.setText(""));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_snackbar() {
        AStringSnackbar.setText(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_string() {
        AStringSnackbar.setText(snackbar, null);
    }
}
