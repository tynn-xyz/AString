//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;

import java.util.Arrays;

/**
 * Implementation delegating to a quantity string resource
 * with or without format arguments
 */
final class QuantityStringResourceDelegate implements AString {

    private final int resId;
    private final int quantity;
    private final Object[] formatArgs;

    QuantityStringResourceDelegate(@PluralsRes int resId, int quantity, @Nullable Object[] formatArgs) {
        this.resId = resId;
        this.quantity = quantity;
        this.formatArgs = formatArgs == null || formatArgs.length == 0 ? null : formatArgs;
    }

    @Nullable
    @Override
    public CharSequence invoke(@NonNull Context context) {
        Resources resources = context.getResources();
        if (formatArgs == null)
            return resources.getQuantityString(resId, quantity);
        return resources.getQuantityString(resId, quantity, formatArgs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityStringResourceDelegate that = (QuantityStringResourceDelegate) o;
        if (resId != that.resId || quantity != that.quantity) return false;
        return Arrays.equals(formatArgs, that.formatArgs);
    }

    @Override
    public int hashCode() {
        return 961 * resId + 31 * quantity + Arrays.hashCode(formatArgs);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AString(")
                .append("QuantityStringResource(")
                .append(resId).append(',').append(quantity);
        if (formatArgs != null)
            for (Object o : formatArgs)
                sb.append(',').append(o);
        return sb.append("))").toString();
    }
}
