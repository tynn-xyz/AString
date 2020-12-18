//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A {@link String} abstraction for <i>Android</i>.
 *
 * The purpose of this type is to provide context sensitive strings.
 * This could be a plain {@link CharSequence} or a {@link android.text.Spannable},
 * a string resource or a formatted quantity string.
 *
 * {@code AString} is almost always used from the main thread,
 * therefore all implementations must be non-blocking since
 */
public interface AString {

    /**
     * Provides a context sensitive string
     *
     * @param context to access resources
     * @return string value from context; might be null
     */
    @Nullable
    CharSequence invoke(@NonNull Context context);
}

