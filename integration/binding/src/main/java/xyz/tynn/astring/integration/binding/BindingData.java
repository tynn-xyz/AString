//  Copyright 2024 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.integration.binding;

import androidx.databinding.ObservableField;

import xyz.tynn.astring.AString;

public class BindingData {

    private final ObservableField<AString> aString;

    BindingData(ObservableField<AString> aString) {
        this.aString = aString;
    }

    public ObservableField<AString> getAString() {
        return aString;
    }
}
