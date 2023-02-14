//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static xyz.tynn.astring.core.test.MockKt.verify;

import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Message;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringAlertDialogTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    AlertDialog dialog;

    @MockK
    OnClickListener listener;
    @MockK
    Message message;

    @Test
    public void setButton_with_listener_should_delegate_to_dialog() {
        AStringAlertDialog.setButton(dialog, BUTTON_NEUTRAL, aString, listener);

        verify(() -> dialog.setButton(BUTTON_NEUTRAL, aString.invoke(dialog.getContext()), listener));
    }

    @Test
    public void setButton_with_null_listener_should_delegate_to_dialog() {
        AStringAlertDialog.setButton(dialog, BUTTON_NEUTRAL, aString, (OnClickListener) null);

        verify(() -> dialog.setButton(BUTTON_NEUTRAL, aString.invoke(dialog.getContext()), (OnClickListener) null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setButton_with_listener_should_throw_on_null_dialog() {
        AStringAlertDialog.setButton(null, BUTTON_NEUTRAL, aString, listener);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setButton_with_listener_should_throw_on_null_string() {
        AStringAlertDialog.setButton(dialog, BUTTON_NEUTRAL, null, listener);
    }

    @Test
    public void setButton_with_message_should_delegate_to_dialog() {
        AStringAlertDialog.setButton(dialog, BUTTON_NEUTRAL, aString, message);

        verify(() -> dialog.setButton(BUTTON_NEUTRAL, aString.invoke(dialog.getContext()), message));
    }

    @Test
    public void setButton_with_null_message_should_delegate_to_dialog() {
        AStringAlertDialog.setButton(dialog, BUTTON_NEUTRAL, aString, (Message) null);

        verify(() -> dialog.setButton(BUTTON_NEUTRAL, aString.invoke(dialog.getContext()), (Message) null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setButton_with_message_should_throw_on_null_dialog() {
        AStringAlertDialog.setButton(null, BUTTON_NEUTRAL, aString, message);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setButton_with_message_should_throw_on_null_string() {
        AStringAlertDialog.setButton(dialog, BUTTON_NEUTRAL, null, message);
    }

    @Test
    public void setMessage_should_delegate_to_dialog() {
        AStringAlertDialog.setMessage(dialog, aString);

        verify(() -> dialog.setMessage(aString.invoke(dialog.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setMessage_should_throw_on_null_dialog() {
        AStringAlertDialog.setMessage(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setMessage_should_throw_on_null_string() {
        AStringAlertDialog.setMessage(dialog, null);
    }

    @Test
    public void setTitle_should_delegate_to_dialog() {
        AStringAlertDialog.setTitle(dialog, aString);

        verify(() -> dialog.setTitle(aString.invoke(dialog.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTitle_should_throw_on_null_dialog() {
        AStringAlertDialog.setTitle(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setTitle_should_throw_on_null_string() {
        AStringAlertDialog.setTitle(dialog, null);
    }
}
