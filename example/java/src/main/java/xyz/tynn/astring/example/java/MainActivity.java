//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.example.java;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import xyz.tynn.astring.example.common.AStringsKt;
import xyz.tynn.astring.example.common.R;
import xyz.tynn.astring.example.common.databinding.ActivityMainBinding;

import static android.widget.TextView.BufferType.SPANNABLE;
import static xyz.tynn.astring.core.AStringTextView.append;
import static xyz.tynn.astring.core.AStringTextView.setError;
import static xyz.tynn.astring.core.AStringTextView.setHint;
import static xyz.tynn.astring.core.AStringTextView.setText;
import static xyz.tynn.astring.core.AStringView.setAccessibilityPaneTitle;
import static xyz.tynn.astring.core.AStringView.setContentDescription;
import static xyz.tynn.astring.core.AStringView.setStateDescription;
import static xyz.tynn.astring.core.AStringView.setTooltipText;
import static xyz.tynn.astring.example.common.AStringsKt.*;
import static xyz.tynn.astring.example.common.databinding.ActivityMainBinding.inflate;

public class MainActivity extends AppCompatActivity {

    private CharSequence tag = null;

    private String getTag() {
        if (tag == null)
            tag = getAString().invoke(this);
        return tag.toString();
    }

    private Drawable getErrorDrawable() {
        Drawable d = ContextCompat.getDrawable(this, R.drawable.ic_error_icon);
        if (d != null)
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        return d;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupAStrings(inflate(getLayoutInflater()));
    }

    private void setupAStrings(ActivityMainBinding binding) {
        Log.d(getTag(), "in setupAStrings");
        setContentView(binding.getRoot());
        setText(binding.viewText, AStringsKt.getText());
        setText(binding.viewTextType, getTextType(), SPANNABLE);
        append(binding.viewAppend, getAppend());
        append(binding.viewAppendRange, getAppendRange(), 1, 7);
        setHint(binding.viewHint, getHint());
        setError(binding.viewError, getError());
        setError(binding.viewErrorIcon, getErrorIcon(), getErrorDrawable());
        setAccessibilityPaneTitle(binding.viewView, getAccessibilityPaneTitle());
        setContentDescription(binding.viewView, getContentDescription());
        setStateDescription(binding.viewView, getStateDescription());
        setTooltipText(binding.viewView, getTooltipText());
    }
}
