//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.material;

import static android.os.Looper.getMainLooper;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;
import static org.junit.Assert.assertSame;
import static io.mockk.MockKKt.every;
import static xyz.tynn.astring.material.test.MockKt.prepare;
import static xyz.tynn.astring.material.test.MockKt.verify;

import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.material.snackbar.Snackbar;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringSnackbarTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    Snackbar snackbar;

    @RelaxedMockK
    View view;
    @MockK
    OnClickListener listener;

    @BeforeClass
    public static void prepareMainLooper() {
        prepare(Looper.class);
        every(scope -> getMainLooper()).returns(null);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void make_should_delegate_to_snackbar() {
        prepare(Snackbar.class);
        every(scope -> make(view, aString.invoke(view.getContext()), LENGTH_LONG)).returns(snackbar);

        assertSame(snackbar,
                AStringSnackbar.make(view, aString, LENGTH_LONG));
    }

    @Test
    public void make_should_default_to_empty_string() {
        prepare(Snackbar.class);
        every(scope -> make(view, "", LENGTH_LONG)).returns(snackbar);
        every(scope -> aString.invoke(view.getContext())).returns(null);

        assertSame(snackbar,
                AStringSnackbar.make(view, aString, LENGTH_LONG));
    }

    @Test
    public void make_should_default_null_to_empty_string() {
        prepare(Snackbar.class);
        every(scope -> make(view, "", LENGTH_LONG)).returns(snackbar);

        assertSame(snackbar,
                AStringSnackbar.make(view, null, LENGTH_LONG));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void make_should_throw_on_null_view() {
        AStringSnackbar.make(null, aString, LENGTH_LONG);
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

    @Test
    public void setAction_with_null_listener_should_delegate_null_to_snackbar() {
        AStringSnackbar.setAction(snackbar, null, listener);

        verify(() -> snackbar.setAction(null, listener));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setAction_should_throw_on_null_snackbar() {
        AStringSnackbar.setAction(null, aString, listener);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void setText_should_delegate_to_snackbar() {
        AStringSnackbar.setText(snackbar, aString);

        verify(() -> snackbar.setText(aString.invoke(snackbar.getContext())));
    }

    @Test
    public void setText_should_default_to_empty_string() {
        every(scope -> aString.invoke(snackbar.getContext())).returns(null);

        AStringSnackbar.setText(snackbar, aString);

        verify(() -> snackbar.setText(""));
    }

    @Test
    public void setText_should_default_null_to_empty_string() {
        AStringSnackbar.setText(snackbar, null);

        verify(() -> snackbar.setText(""));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_snackbar() {
        AStringSnackbar.setText(null, aString);
    }
}
