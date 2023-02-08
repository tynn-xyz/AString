//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.material;

import static org.junit.Assert.assertSame;
import static xyz.tynn.astring.material.test.MockKt.init;
import static xyz.tynn.astring.material.test.MockKt.verify;

import android.content.DialogInterface.OnClickListener;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.junit.Before;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import xyz.tynn.astring.AString;
import xyz.tynn.astring.appcompat.AStringAlertDialogBuilder;

public class AStringMaterialAlertDialogBuilderTest {

    @MockK
    AString aString;

    @MockK
    MaterialAlertDialogBuilder builder;

    @MockK
    OnClickListener listener;

    @Before
    public void setup() {
        init(this, true);
    }

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

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setNegativeButton_should_throw_on_null_builder() {
        AStringAlertDialogBuilder.setNegativeButton(null, aString, listener);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setNegativeButton_should_throw_on_null_string() {
        AStringAlertDialogBuilder.setNegativeButton(builder, null, listener);
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

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setNeutralButton_should_throw_on_null_builder() {
        AStringAlertDialogBuilder.setNeutralButton(null, aString, listener);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setNeutralButton_should_throw_on_null_string() {
        AStringAlertDialogBuilder.setNeutralButton(builder, null, listener);
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

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setPositiveButton_should_throw_on_null_builder() {
        AStringAlertDialogBuilder.setPositiveButton(null, aString, listener);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setPositiveButton_should_throw_on_null_string() {
        AStringAlertDialogBuilder.setPositiveButton(builder, null, listener);
    }

    @Test
    public void setMessage_should_delegate_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setMessage(builder, aString));

        verify(() -> builder.setMessage(aString.invoke(builder.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setMessage_should_throw_on_null_builder() {
        AStringAlertDialogBuilder.setMessage(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setMessage_should_throw_on_null_string() {
        AStringAlertDialogBuilder.setMessage(builder, null);
    }

    @Test
    public void setTitle_should_delegate_to_builder() {
        assertSame(builder,
                AStringAlertDialogBuilder.setTitle(builder, aString));

        verify(() -> builder.setTitle(aString.invoke(builder.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTitle_should_throw_on_null_builder() {
        AStringAlertDialogBuilder.setTitle(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTitle_should_throw_on_null_string() {
        AStringAlertDialogBuilder.setTitle(builder, null);
    }
}
