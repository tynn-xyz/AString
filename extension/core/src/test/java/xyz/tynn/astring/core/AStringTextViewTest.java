//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core;

import static android.widget.TextView.BufferType.SPANNABLE;
import static xyz.tynn.astring.core.test.MockKt.verify;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringTextViewTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    TextView view;

    @Test
    public void append_should_delegate_to_view() {
        AStringTextView.append(view, aString);

        verify(() -> view.append(aString.invoke(view.getContext())));
    }

    @Test
    public void append_should_delegate_null_to_view() {
        AStringTextView.append(view, null);

        verify(() -> view.append(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void append_should_throw_on_null_view() {
        AStringTextView.append(null, aString);
    }

    @Test
    public void append_with_range_should_delegate_to_view() {
        AStringTextView.append(view, aString, 1, 2);

        verify(() -> view.append(aString.invoke(view.getContext()), 1, 2));
    }

    @Test
    public void append_with_range_should_delegate_null_to_view() {
        AStringTextView.append(view, null, 1, 2);

        verify(() -> view.append(null, 1, 2));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void append_with_range_should_throw_on_null_view() {
        AStringTextView.append(null, aString, 1, 2);
    }

    @Test
    public void setText_should_delegate_to_view() {
        AStringTextView.setText(view, aString);

        verify(() -> view.setText(aString.invoke(view.getContext())));
    }

    @Test
    public void setText_should_delegate_null_to_view() {
        AStringTextView.setText(view, null);

        verify(() -> view.setText(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_view() {
        AStringTextView.setText(null, aString);
    }

    @Test
    public void setText_with_type_should_delegate_to_view() {
        AStringTextView.setText(view, aString, SPANNABLE);

        verify(() -> view.setText(aString.invoke(view.getContext()), SPANNABLE));
    }

    @Test
    public void setText_with_type_should_delegate_null_to_view() {
        AStringTextView.setText(view, null, SPANNABLE);

        verify(() -> view.setText(null, SPANNABLE));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setText_with_type_should_throw_on_null_view() {
        AStringTextView.setText(null, aString, SPANNABLE);
    }

    @Test
    public void setError_should_delegate_to_view() {
        AStringTextView.setError(view, aString);

        verify(() -> view.setError(aString.invoke(view.getContext())));
    }

    @Test
    public void setError_should_delegate_null_to_view() {
        AStringTextView.setError(view, null);

        verify(() -> view.setError(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setError_should_throw_on_null_view() {
        AStringTextView.setError(null, aString);
    }

    @Test
    public void setError_with_icon_should_delegate_to_view() {
        AStringTextView.setError(view, aString, null);

        verify(() -> view.setError(aString.invoke(view.getContext()), null));
    }

    @Test
    public void setError_with_icon_should_delegate_null_to_view() {
        Drawable icon = new ColorDrawable();

        AStringTextView.setError(view, null, icon);

        verify(() -> view.setError(null, icon));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setError_with_icon_should_throw_on_null_view() {
        AStringTextView.setError(null, aString, new ColorDrawable());
    }

    @Test
    public void setHint_should_delegate_to_view() {
        AStringTextView.setHint(view, aString);

        verify(() -> view.setHint(aString.invoke(view.getContext())));
    }

    @Test
    public void setHint_should_delegate_null_to_view() {
        AStringTextView.setHint(view, null);

        verify(() -> view.setHint(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setHint_should_throw_on_null_view() {
        AStringTextView.setHint(null, aString);
    }
}
