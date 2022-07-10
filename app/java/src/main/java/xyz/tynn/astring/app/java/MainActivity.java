//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.app.java;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.widget.TextView.BufferType.SPANNABLE;
import static android.widget.Toast.LENGTH_SHORT;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static xyz.tynn.astring.app.shared.AStringsKt.getAString;
import static xyz.tynn.astring.app.shared.AStringsKt.getAccessibilityPaneTitle;
import static xyz.tynn.astring.app.shared.AStringsKt.getAction1;
import static xyz.tynn.astring.app.shared.AStringsKt.getAction2;
import static xyz.tynn.astring.app.shared.AStringsKt.getAction3;
import static xyz.tynn.astring.app.shared.AStringsKt.getAppend;
import static xyz.tynn.astring.app.shared.AStringsKt.getAppendRange;
import static xyz.tynn.astring.app.shared.AStringsKt.getContentDescription;
import static xyz.tynn.astring.app.shared.AStringsKt.getDialog;
import static xyz.tynn.astring.app.shared.AStringsKt.getError;
import static xyz.tynn.astring.app.shared.AStringsKt.getErrorIcon;
import static xyz.tynn.astring.app.shared.AStringsKt.getHint;
import static xyz.tynn.astring.app.shared.AStringsKt.getMessage;
import static xyz.tynn.astring.app.shared.AStringsKt.getStateDescription;
import static xyz.tynn.astring.app.shared.AStringsKt.getTextType;
import static xyz.tynn.astring.app.shared.AStringsKt.getTooltipText;
import static xyz.tynn.astring.app.shared.databinding.ActivityMainBinding.inflate;
import static xyz.tynn.astring.core.AStringTextView.append;
import static xyz.tynn.astring.core.AStringTextView.setError;
import static xyz.tynn.astring.core.AStringTextView.setHint;
import static xyz.tynn.astring.core.AStringTextView.setText;
import static xyz.tynn.astring.core.AStringToast.makeText;
import static xyz.tynn.astring.core.AStringView.setAccessibilityPaneTitle;
import static xyz.tynn.astring.core.AStringView.setContentDescription;
import static xyz.tynn.astring.core.AStringView.setStateDescription;
import static xyz.tynn.astring.core.AStringView.setTooltipText;
import static xyz.tynn.astring.material.AStringSnackbar.make;
import static xyz.tynn.astring.material.AStringSnackbar.setAction;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import xyz.tynn.astring.app.shared.AStringsKt;
import xyz.tynn.astring.app.shared.R;
import xyz.tynn.astring.app.shared.databinding.ActivityMainBinding;
import xyz.tynn.astring.appcompat.AStringAlertDialog;
import xyz.tynn.astring.appcompat.AStringAlertDialogBuilder;
import xyz.tynn.astring.material.AStringSnackbar;

public class MainActivity extends AppCompatActivity {

    private CharSequence tag = null;

    @SuppressWarnings("ConstantConditions")
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
        setupDialogs(binding);
        setText(binding.viewAppId, AStringsKt.getAStringPackage());
        setText(binding.viewAppVersion, AStringsKt.getAStringVersion());
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

    private void setupDialogs(ActivityMainBinding binding) {
        binding.buttonAlertDialog.setOnClickListener(v -> {
            MaterialAlertDialogBuilder b = new MaterialAlertDialogBuilder(this);
            AStringAlertDialogBuilder.setMessage(b, getMessage());
            AStringAlertDialogBuilder.setPositiveButton(b, getAction1(), null);
            AStringAlertDialogBuilder.setNeutralButton(b, getAction2(), null);
            AStringAlertDialogBuilder.setNegativeButton(b, getAction3(), null);
            AlertDialog d = b.create();
            AStringAlertDialog.setTitle(d, AStringsKt.getTitle());
            d.show();
        });
        binding.buttonAlertDialogLegacy.setOnClickListener(v -> {
            Builder b = new Builder(this);
            xyz.tynn.astring.core.AStringAlertDialogBuilder.setTitle(b, AStringsKt.getTitle());
            xyz.tynn.astring.core.AStringAlertDialogBuilder.setMessage(b, getMessage());
            xyz.tynn.astring.core.AStringAlertDialogBuilder.setPositiveButton(b, getAction1(), null);
            xyz.tynn.astring.core.AStringAlertDialogBuilder.setNeutralButton(b, getAction2(), null);
            android.app.AlertDialog d = b.create();
            xyz.tynn.astring.core.AStringAlertDialog.setButton(d, BUTTON_NEGATIVE, getAction3(), (OnClickListener) null);
            d.show();
        });
        binding.buttonSnackbar.setOnClickListener(v -> {
            make(binding.getRoot(), getDialog(), LENGTH_LONG)
                    .show();
        });
        binding.buttonSnackbarUpdate.setOnClickListener(v -> {
            Snackbar s = Snackbar.make(binding.getRoot(), "", LENGTH_LONG);
            AStringSnackbar.setText(s, getDialog());
            setAction(s, getAction1(), this::doNothing);
            s.show();
        });
        binding.buttonToast.setOnClickListener(v -> {
            makeText(this, getDialog(), LENGTH_SHORT)
                    .show();
        });
        binding.buttonToastUpdate.setOnClickListener(v -> {
            Toast t = Toast.makeText(this, "", LENGTH_SHORT);
            t.setText(getDialog().invoke(this));
            t.show();
        });
    }

    private void doNothing(View v) {
    }
}
