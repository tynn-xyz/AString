//  Copyright 2024 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.integration.binding;

import static android.util.Log.d;
import static xyz.tynn.astring.AStringKt.invokeWithContext;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.Observable.OnPropertyChangedCallback;
import androidx.databinding.ObservableField;

import java.util.Objects;

import xyz.tynn.astring.AString;
import xyz.tynn.astring.integration.binding.databinding.ActivityBindingBinding;

public class BindingActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_binding);
        ObservableField<AString> field = new ObservableField<>(AString.Null);
        field.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                log(field.get());
            }
        });
        binding.setData(new BindingData(field));
    }

    private void log(AString aString) {
        CharSequence string = invokeWithContext(this, aString);
        d("BindingActivity", Objects.toString(string));
    }
}
