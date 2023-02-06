//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static androidx.core.os.ParcelCompat.readBoolean;
import static androidx.core.os.ParcelCompat.writeBoolean;
import static java.lang.Integer.MIN_VALUE;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.annotation.StringRes;

import java.util.Arrays;
import java.util.Objects;

/**
 * Implementation delegating to a resource with or without format arguments
 */
final class ResourceDelegate implements AString {

    private final boolean isPlural, isText;
    private final int resId, quantity;
    private final Object[] formatArgs;

    private ResourceDelegate(
            boolean isPlural, boolean isText,
            int resId, int quantity, Object[] formatArgs) {
        this.isPlural = isPlural;
        this.isText = isText;
        this.resId = resId;
        this.quantity = quantity;
        this.formatArgs = formatArgs == null || formatArgs.length == 0 ? null
                : formatArgs;
    }

    @Nullable
    @Override
    public CharSequence invoke(@NonNull Context context) {
        return isPlural ? invokeWithQuantity(context)
                : invokeWithoutQuantity(context);
    }

    private CharSequence invokeWithQuantity(Context context) {
        Resources resources = context.getResources();
        return isText ? resources.getQuantityText(resId, quantity) : formatArgs == null
                ? resources.getQuantityString(resId, quantity)
                : resources.getQuantityString(resId, quantity, formatArgs);
    }

    private CharSequence invokeWithoutQuantity(Context context) {
        return isText ? context.getText(resId) : formatArgs == null
                ? context.getString(resId)
                : context.getString(resId, formatArgs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceDelegate that = (ResourceDelegate) o;
        if (isPlural != that.isPlural) return false;
        if (isText != that.isText) return false;
        if (resId != that.resId) return false;
        if (quantity != that.quantity) return false;
        return Arrays.equals(formatArgs, that.formatArgs);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(isPlural, isText, resId, quantity)
                + Arrays.hashCode(formatArgs);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AString(");
        if (isPlural) sb.append("Quantity");
        sb.append(isText ? "Text" : "String");
        sb.append("Resource(").append(resId);
        if (isPlural) sb.append(',').append(quantity);
        if (formatArgs != null) for (Object o : formatArgs)
            sb.append(',').append(o);
        return sb.append("))").toString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        writeBoolean(dest, isPlural);
        writeBoolean(dest, isText);
        dest.writeInt(resId);
        dest.writeInt(quantity);
        dest.writeValue(formatArgs);
    }

    /**
     * Implementation delegating to a quantity string resource
     * with or without format arguments
     */
    static ResourceDelegate quantityString(
            @PluralsRes int resId, int quantity, @Nullable Object[] formatArgs) {
        return new ResourceDelegate(true, false, resId, quantity, formatArgs);
    }

    /**
     * Implementation delegating to a quantity text resource
     */
    static ResourceDelegate quantityText(@PluralsRes int resId, int quantity) {
        return new ResourceDelegate(true, true, resId, quantity, null);
    }

    /**
     * Implementation delegating to a string resource
     * with or without format arguments
     */
    static ResourceDelegate string(@StringRes int resId, @Nullable Object[] formatArgs) {
        return new ResourceDelegate(false, false, resId, MIN_VALUE, formatArgs);
    }

    /**
     * Implementation delegating to a text resource
     */
    static ResourceDelegate text(@StringRes int resId) {
        return new ResourceDelegate(false, true, resId, MIN_VALUE, null);
    }

    public static final Creator<ResourceDelegate> CREATOR = new Creator<ResourceDelegate>() {

        @Override
        public ResourceDelegate createFromParcel(Parcel source) {
            return new ResourceDelegate(
                    readBoolean(source), readBoolean(source),
                    source.readInt(), source.readInt(),
                    (Object[]) source.readValue(getClass().getClassLoader()));
        }

        @Override
        public ResourceDelegate[] newArray(int size) {
            return new ResourceDelegate[size];
        }
    };
}
