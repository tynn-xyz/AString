//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;

/**
 * Implementation delegating to a quantity text resource
 */
final class QuantityTextResourceDelegate implements AString {

    private final int resId;
    private final int quantity;

    QuantityTextResourceDelegate(@PluralsRes int resId, int quantity) {
        this.resId = resId;
        this.quantity = quantity;
    }

    @Nullable
    @Override
    public CharSequence invoke(@NonNull Context context) {
        return context.getResources().getQuantityText(resId, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityTextResourceDelegate that = (QuantityTextResourceDelegate) o;
        return resId == that.resId && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return 31 * resId + quantity;
    }

    @NonNull
    @Override
    public String toString() {
        return "AString(" + "QuantityTextResource(" + resId + ',' + quantity + "))";
    }
}
