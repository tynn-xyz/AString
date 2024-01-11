//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.material;

import static xyz.tynn.astring.material.test.MockKt.verify;

import com.google.android.material.textfield.TextInputLayout;

import org.junit.Rule;
import org.junit.Test;

import io.mockk.impl.annotations.RelaxedMockK;
import io.mockk.junit4.MockKRule;
import xyz.tynn.astring.AString;

public class AStringTextInputLayoutTest {

    @Rule
    public final MockKRule mockkRule = new MockKRule(this);

    @RelaxedMockK
    AString aString;

    @RelaxedMockK
    TextInputLayout view;

    @Test
    public void setEndIconContentDescription_should_delegate_to_view() {
        AStringTextInputLayout.setEndIconContentDescription(view, aString);

        verify(() -> view.setEndIconContentDescription(aString.invoke(view.getContext())));
    }

    @Test
    public void setEndIconContentDescription_should_delegate_null_to_view() {
        AStringTextInputLayout.setEndIconContentDescription(view, null);

        verify(() -> view.setEndIconContentDescription(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setEndIconContentDescription_should_throw_on_null_view() {
        AStringTextInputLayout.setEndIconContentDescription(null, aString);
    }

    @Test
    public void setErrorContentDescription_should_delegate_to_view() {
        AStringTextInputLayout.setErrorContentDescription(view, aString);

        verify(() -> view.setErrorContentDescription(aString.invoke(view.getContext())));
    }

    @Test
    public void setErrorContentDescription_should_delegate_null_to_view() {
        AStringTextInputLayout.setErrorContentDescription(view, null);

        verify(() -> view.setErrorContentDescription(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setErrorContentDescription_should_throw_on_null_view() {
        AStringTextInputLayout.setErrorContentDescription(null, aString);
    }

    @Test
    public void setError_should_delegate_to_view() {
        AStringTextInputLayout.setError(view, aString);

        verify(() -> view.setError(aString.invoke(view.getContext())));
    }

    @Test
    public void setError_should_delegate_null_to_view() {
        AStringTextInputLayout.setError(view, null);

        verify(() -> view.setError(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setError_should_throw_on_null_view() {
        AStringTextInputLayout.setError(null, aString);
    }

    @Test
    public void setHelperText_should_delegate_to_view() {
        AStringTextInputLayout.setHelperText(view, aString);

        verify(() -> view.setHelperText(aString.invoke(view.getContext())));
    }

    @Test
    public void setHelperText_should_delegate_null_to_view() {
        AStringTextInputLayout.setHelperText(view, null);

        verify(() -> view.setHelperText(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setHelperText_should_throw_on_null_view() {
        AStringTextInputLayout.setHelperText(null, aString);
    }

    @Test
    public void setHint_should_delegate_to_view() {
        AStringTextInputLayout.setHint(view, aString);

        verify(() -> view.setHint(aString.invoke(view.getContext())));
    }

    @Test
    public void setHint_should_delegate_null_to_view() {
        AStringTextInputLayout.setHint(view, null);

        verify(() -> view.setHint(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setHint_should_throw_on_null_view() {
        AStringTextInputLayout.setHint(null, aString);
    }

    @Test
    public void setPlaceholderText_should_delegate_to_view() {
        AStringTextInputLayout.setPlaceholderText(view, aString);

        verify(() -> view.setPlaceholderText(aString.invoke(view.getContext())));
    }

    @Test
    public void setPlaceholderText_should_delegate_null_to_view() {
        AStringTextInputLayout.setPlaceholderText(view, null);

        verify(() -> view.setPlaceholderText(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setPlaceholderText_should_throw_on_null_view() {
        AStringTextInputLayout.setPlaceholderText(null, aString);
    }

    @Test
    public void setPrefixText_should_delegate_to_view() {
        AStringTextInputLayout.setPrefixText(view, aString);

        verify(() -> view.setPrefixText(aString.invoke(view.getContext())));
    }

    @Test
    public void setPrefixText_should_delegate_null_to_view() {
        AStringTextInputLayout.setPrefixText(view, null);

        verify(() -> view.setPrefixText(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setPrefixText_should_throw_on_null_view() {
        AStringTextInputLayout.setPrefixText(null, aString);
    }

    @Test
    public void setStartIconContentDescription_should_delegate_to_view() {
        AStringTextInputLayout.setStartIconContentDescription(view, aString);

        verify(() -> view.setStartIconContentDescription(aString.invoke(view.getContext())));
    }

    @Test
    public void setStartIconContentDescription_should_delegate_null_to_view() {
        AStringTextInputLayout.setStartIconContentDescription(view, null);

        verify(() -> view.setStartIconContentDescription(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setStartIconContentDescription_should_throw_on_null_view() {
        AStringTextInputLayout.setStartIconContentDescription(null, aString);
    }

    @Test
    public void setSuffixText_should_delegate_to_view() {
        AStringTextInputLayout.setSuffixText(view, aString);

        verify(() -> view.setSuffixText(aString.invoke(view.getContext())));
    }

    @Test
    public void setSuffixText_should_delegate_null_to_view() {
        AStringTextInputLayout.setSuffixText(view, null);

        verify(() -> view.setSuffixText(null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void setSuffixText_should_throw_on_null_view() {
        AStringTextInputLayout.setSuffixText(null, aString);
    }
}
