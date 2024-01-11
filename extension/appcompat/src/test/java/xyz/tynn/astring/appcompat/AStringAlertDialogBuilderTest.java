//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat;

import static org.junit.Assert.assertSame;
import static xyz.tynn.astring.appcompat.test.MockKt.verify;

import android.content.DialogInterface.OnClickListener;

import androidx.appcompat.app.AlertDialog;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringAlertDialogBuilderTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    AlertDialog.Builder builder;

    @MockK
    OnClickListener listener;

    @Test
    public void setNegativeButton_should_delegate_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setNegativeButton(builder, aString, listener));

        verify(() -> builder.setNegativeButton(aString.invoke(builder.getContext()), listener));
    }

    @Test
    public void setNegativeButton_with_null_listener_should_delegate_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setNegativeButton(builder, aString, null));

        verify(() -> builder.setNegativeButton(aString.invoke(builder.getContext()), null));
    }

    @Test
    public void setNegativeButton_with_null_listener_should_delegate_null_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setNegativeButton(builder, null, listener));

        verify(() -> builder.setNegativeButton(null, listener));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setNegativeButton_should_throw_on_null_builder() {
        AStringAlertDialogBuilder.setNegativeButton(null, aString, listener);
    }

    @Test
    public void setNeutralButton_should_delegate_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setNeutralButton(builder, aString, listener));

        verify(() -> builder.setNeutralButton(aString.invoke(builder.getContext()), listener));
    }

    @Test
    public void setNeutralButton_with_null_listener_should_delegate_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setNeutralButton(builder, aString, null));

        verify(() -> builder.setNeutralButton(aString.invoke(builder.getContext()), null));
    }

    @Test
    public void setNeutralButton_with_null_listener_should_delegate_null_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setNeutralButton(builder, null, listener));

        verify(() -> builder.setNeutralButton(null, listener));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setNeutralButton_should_throw_on_null_builder() {
        AStringAlertDialogBuilder.setNeutralButton(null, aString, listener);
    }

    @Test
    public void setPositiveButton_should_delegate_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setPositiveButton(builder, aString, listener));

        verify(() -> builder.setPositiveButton(aString.invoke(builder.getContext()), listener));
    }

    @Test
    public void setPositiveButton_with_null_listener_should_delegate_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setPositiveButton(builder, aString, null));

        verify(() -> builder.setPositiveButton(aString.invoke(builder.getContext()), null));
    }

    @Test
    public void setPositiveButton_with_null_listener_should_delegate_null_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setPositiveButton(builder, null, listener));

        verify(() -> builder.setPositiveButton(null, listener));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setPositiveButton_should_throw_on_null_builder() {
        AStringAlertDialogBuilder.setPositiveButton(null, aString, listener);
    }

    @Test
    public void setMessage_should_delegate_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setMessage(builder, aString));

        verify(() -> builder.setMessage(aString.invoke(builder.getContext())));
    }

    @Test
    public void setMessage_should_delegate_null_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setMessage(builder, null));

        verify(() -> builder.setMessage(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setMessage_should_throw_on_null_builder() {
        AStringAlertDialogBuilder.setMessage(null, aString);
    }

    @Test
    public void setTitle_should_delegate_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setTitle(builder, aString));

        verify(() -> builder.setTitle(aString.invoke(builder.getContext())));
    }

    @Test
    public void setTitle_should_delegate_null_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setTitle(builder, null));

        verify(() -> builder.setTitle(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTitle_should_throw_on_null_builder() {
        AStringAlertDialogBuilder.setTitle(null, aString);
    }
}
