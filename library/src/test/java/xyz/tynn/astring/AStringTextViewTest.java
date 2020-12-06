//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import androidx.core.widget.TextViewCompat;
import io.mockk.impl.annotations.MockK;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.widget.TextView.BufferType.SPANNABLE;
import static xyz.tynn.astring.mockk.MockKt.*;

public class AStringTextViewTest {

    @MockK
    AString aString;

    @MockK
    TextView view;

    @Before
    public void setup() {
        init(this, true);
        prepare(TextViewCompat.class);
    }

    @After
    public void teardown() {
        clearAll();
    }

    @Test
    public void append_should_delegate_to_view() {
        AStringTextView.append(view, aString);

        verify(() -> view.append(aString.invoke(view.getContext())));
    }

    @Test(expected = NullPointerException.class)
    public void append_should_throw_on_null_view() {
        AStringTextView.append(null, aString);
    }

    @Test(expected = NullPointerException.class)
    public void append_should_throw_on_null_string() {
        AStringTextView.append(view, null);
    }

    @Test
    public void append_with_range_should_delegate_to_view() {
        AStringTextView.append(view, aString, 1, 2);

        verify(() -> view.append(aString.invoke(view.getContext()), 1, 2));
    }

    @Test(expected = NullPointerException.class)
    public void append_with_range_should_throw_on_null_view() {
        AStringTextView.append(null, aString, 1, 2);
    }

    @Test(expected = NullPointerException.class)
    public void append_with_range_should_throw_on_null_string() {
        AStringTextView.append(view, null, 1, 2);
    }

    @Test
    public void setText_should_delegate_to_view() {
        AStringTextView.setText(view, aString);

        verify(() -> view.setText(aString.invoke(view.getContext())));
    }

    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_view() {
        AStringTextView.setText(null, aString);
    }

    @Test(expected = NullPointerException.class)
    public void setText_should_throw_on_null_string() {
        AStringTextView.setText(view, null);
    }

    @Test
    public void setText_with_type_should_delegate_to_view() {
        AStringTextView.setText(view, aString, SPANNABLE);

        verify(() -> view.setText(aString.invoke(view.getContext()), SPANNABLE));
    }

    @Test(expected = NullPointerException.class)
    public void setText_with_type_should_throw_on_null_view() {
        AStringTextView.setText(null, aString, SPANNABLE);
    }

    @Test(expected = NullPointerException.class)
    public void setText_with_type_should_throw_on_null_string() {
        AStringTextView.setText(view, null, SPANNABLE);
    }

    @Test
    public void setError_should_delegate_to_view() {
        AStringTextView.setError(view, aString);

        verify(() -> view.setError(aString.invoke(view.getContext())));
    }

    @Test(expected = NullPointerException.class)
    public void setError_should_throw_on_null_view() {
        AStringTextView.setError(null, aString);
    }

    @Test(expected = NullPointerException.class)
    public void setError_should_throw_on_null_string() {
        AStringTextView.setError(view, null);
    }

    @Test
    public void setError_with_icon_should_delegate_to_view() {
        Drawable icon = new ColorDrawable();

        AStringTextView.setError(view, aString, icon);

        verify(() -> view.setError(aString.invoke(view.getContext()), icon));
    }

    @Test(expected = NullPointerException.class)
    public void setError_with_icon_should_throw_on_null_view() {
        AStringTextView.setError(null, aString, new ColorDrawable());
    }

    @Test(expected = NullPointerException.class)
    public void setError_with_icon_should_throw_on_null_string() {
        AStringTextView.setError(view, null, new ColorDrawable());
    }

    @Test
    public void setHint_should_delegate_to_view() {
        AStringTextView.setHint(view, aString);

        verify(() -> view.setHint(aString.invoke(view.getContext())));
    }

    @Test(expected = NullPointerException.class)
    public void setHint_should_throw_on_null_view() {
        AStringTextView.setHint(null, aString);
    }

    @Test(expected = NullPointerException.class)
    public void setHint_should_throw_on_null_string() {
        AStringTextView.setHint(view, null);
    }
}
