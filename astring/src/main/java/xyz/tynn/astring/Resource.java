//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static android.content.res.Resources.ID_NULL;
import static androidx.core.os.ParcelCompat.readBoolean;
import static androidx.core.os.ParcelCompat.writeBoolean;
import static xyz.tynn.astring.Wrapper.NULL;

import android.content.Context;
import android.os.Parcel;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Implementation delegating to a resource with or without format arguments
 */
final class Resource implements AString {

    private final int resId;
    private final Integer quantity;

    private Resource(int resId, Integer quantity) {
        this.resId = resId;
        this.quantity = quantity;
    }

    static AString wrap(int resId, Integer quantity) {
        if (resId == ID_NULL) return NULL;
        return new Resource(resId, quantity);
    }

    static AString wrap(int resId, Integer quantity, Object[] formatArgs) {
        if (resId == ID_NULL) return NULL;
        return ToString.wrap(new Resource(resId, quantity), null, formatArgs);
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
        StringBuilder sb = new StringBuilder("AString(");
        if (quantity != null) sb.append("Quantity");
        sb.append("TextResource(").append(resId);
        if (quantity != null) sb.append(',').append(quantity);
        return sb.append("))").toString();
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(resId);
        writeBoolean(dest, quantity != null);
        if (quantity != null) dest.writeInt(quantity);
    }

    public static final Creator<Resource> CREATOR = new Creator<>() {

        @Override
        public Resource createFromParcel(Parcel source) {
            return new Resource(source.readInt(),
                    readBoolean(source) ? source.readInt() : null);
        }

        @Override
        public Resource[] newArray(int size) {
            return new Resource[size];
        }
    };
}
