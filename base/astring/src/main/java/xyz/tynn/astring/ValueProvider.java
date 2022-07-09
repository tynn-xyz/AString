//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

enum ValueProvider implements AString {

    /**
     * Implementation providing {@link Context#getPackageName()}
     */
    AppIdProvider {
        @Nullable
        @Override
        public CharSequence invoke(@NonNull Context context) {
            return context.getPackageName();
        }

        @NonNull
        @Override
        public String toString() {
            return "AString(" + "Context(" + "appId" + "))";
        }
    },

    /**
     * Implementation providing {@link PackageInfo#versionName}
     */
    AppVersionProvider {
        @Nullable
        @Override
        public CharSequence invoke(@NonNull Context context) {
            try {
                String packageName = context.getPackageName();
                PackageManager packageManager = context.getPackageManager();
                return packageManager.getPackageInfo(packageName, 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        @NonNull
        @Override
        public String toString() {
            return "AString(" + "Context(" + "appVersion" + "))";
        }
    },

    /**
     * Implementation wrapping {@code null}
     */
    NullValueProvider {
        @Nullable
        @Override
        public CharSequence invoke(@Nullable Context context) {
            return null;
        }

        @NonNull
        @Override
        public String toString() {
            return "AString(" + "CharSequence(" + null + "))";
        }
    },

    ;

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }

    public static final Creator<ValueProvider> CREATOR = new Creator<ValueProvider>() {

        @Override
        public ValueProvider createFromParcel(Parcel source) {
            return ValueProvider.valueOf(source.readString());
        }

        @Override
        public ValueProvider[] newArray(int size) {
            return new ValueProvider[size];
        }
    };
}
