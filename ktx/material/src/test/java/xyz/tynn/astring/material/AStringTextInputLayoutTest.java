//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.material;

import static xyz.tynn.astring.testing.mockk.MockKt.init;
import static xyz.tynn.astring.testing.mockk.MockKt.verify;

import com.google.android.material.textfield.TextInputLayout;

import org.junit.Before;
import org.junit.Test;

import io.mockk.impl.annotations.MockK;
import xyz.tynn.astring.AString;

public class AStringTextInputLayoutTest {

    @MockK
    AString aString;

    @MockK
    TextInputLayout view;

    @Before
    public void setup() {
        init(this, true);
    }

    @Test
    public void setEndIconContentDescription_should_delegate_to_view() {
        AStringTextInputLayout.setEndIconContentDescription(view, aString);

        verify(() -> view.setEndIconContentDescription(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setEndIconContentDescription_should_throw_on_null_view() {
        AStringTextInputLayout.setEndIconContentDescription(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setEndIconContentDescription_should_throw_on_null_string() {
        AStringTextInputLayout.setEndIconContentDescription(view, null);
    }

    @Test
    public void setErrorContentDescription_should_delegate_to_view() {
        AStringTextInputLayout.setErrorContentDescription(view, aString);

        verify(() -> view.setErrorContentDescription(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setErrorContentDescription_should_throw_on_null_view() {
        AStringTextInputLayout.setErrorContentDescription(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setErrorContentDescription_should_throw_on_null_string() {
        AStringTextInputLayout.setErrorContentDescription(view, null);
    }

    @Test
    public void setError_should_delegate_to_view() {
        AStringTextInputLayout.setError(view, aString);

        verify(() -> view.setError(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setError_should_throw_on_null_view() {
        AStringTextInputLayout.setError(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setError_should_throw_on_null_string() {
        AStringTextInputLayout.setError(view, null);
    }

    @Test
    public void setHelperText_should_delegate_to_view() {
        AStringTextInputLayout.setHelperText(view, aString);

        verify(() -> view.setHelperText(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setHelperText_should_throw_on_null_view() {
        AStringTextInputLayout.setHelperText(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setHelperText_should_throw_on_null_string() {
        AStringTextInputLayout.setHelperText(view, null);
    }

    @Test
    public void setHint_should_delegate_to_view() {
        AStringTextInputLayout.setHint(view, aString);

        verify(() -> view.setHint(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setHint_should_throw_on_null_view() {
        AStringTextInputLayout.setHint(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setHint_should_throw_on_null_string() {
        AStringTextInputLayout.setHint(view, null);
    }

    @Test
    public void setPlaceholderText_should_delegate_to_view() {
        AStringTextInputLayout.setPlaceholderText(view, aString);

        verify(() -> view.setPlaceholderText(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setPlaceholderText_should_throw_on_null_view() {
        AStringTextInputLayout.setPlaceholderText(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setPlaceholderText_should_throw_on_null_string() {
        AStringTextInputLayout.setPlaceholderText(view, null);
    }

    @Test
    public void setPrefixText_should_delegate_to_view() {
        AStringTextInputLayout.setPrefixText(view, aString);

        verify(() -> view.setPrefixText(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setPrefixText_should_throw_on_null_view() {
        AStringTextInputLayout.setPrefixText(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setPrefixText_should_throw_on_null_string() {
        AStringTextInputLayout.setPrefixText(view, null);
    }

    @Test
    public void setStartIconContentDescription_should_delegate_to_view() {
        AStringTextInputLayout.setStartIconContentDescription(view, aString);

        verify(() -> view.setStartIconContentDescription(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setStartIconContentDescription_should_throw_on_null_view() {
        AStringTextInputLayout.setStartIconContentDescription(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setStartIconContentDescription_should_throw_on_null_string() {
        AStringTextInputLayout.setStartIconContentDescription(view, null);
    }

    @Test
    public void setSuffixText_should_delegate_to_view() {
        AStringTextInputLayout.setSuffixText(view, aString);

        verify(() -> view.setSuffixText(aString.invoke(view.getContext())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setSuffixText_should_throw_on_null_view() {
        AStringTextInputLayout.setSuffixText(null, aString);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setSuffixText_should_throw_on_null_string() {
        AStringTextInputLayout.setSuffixText(view, null);
    }
}
