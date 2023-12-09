//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static android.content.res.Resources.ID_NULL;
import static xyz.tynn.astring.AString.Null;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Implementation delegating to a resource with or without format arguments
 */
@SuppressLint("UnsafeOptInUsageError")
final class Resource implements AStringProvider {

    private final int resId;
    private final Integer quantity;

    private Resource(int resId, Integer quantity) {
        this.resId = resId;
        this.quantity = quantity;
    }

    static AString wrap(int resId, Integer quantity) {
        if (resId == ID_NULL) return Null;
        return Delegate.wrap(new Resource(resId, quantity));
    }

    static AString wrap(int resId, Integer quantity, Object[] formatArgs) {
        AString resource = wrap(resId, quantity);
        if (resource == Null) return resource;
        return Format.wrap(resource, null, formatArgs);
    }

    @Override
    public CharSequence invoke(@NonNull Context context) {
        return quantity == null ? context.getText(resId) :
                context.getResources().getQuantityText(resId, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource that = (Resource) o;
        return resId == that.resId && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, quantity);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (quantity != null) sb.append("Quantity");
        sb.append("TextResource(").append(resId);
        if (quantity != null) sb.append(',').append(quantity);
        return sb.append(')').toString();
    }
}
