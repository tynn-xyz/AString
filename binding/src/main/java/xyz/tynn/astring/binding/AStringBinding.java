//  Copyright 2026 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.binding;

import static xyz.tynn.astring.AStringFactory.createFromCharSequence;
import static xyz.tynn.astring.AStringKt.invokeWithContext;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.InverseMethod;

import xyz.tynn.astring.AString;

/**
 * AString converter methods to support two-way data binding
 */
public interface AStringBinding {

    @InverseMethod("wrap")
    static String load(@NonNull Context context, AString value) {
        CharSequence string = invokeWithContext(context, value);
        return string == null ? null : string.toString();
    }

    @NonNull
    static AString wrap(@SuppressWarnings("unused") Context context, String value) {
        return createFromCharSequence(value);
    }
}
