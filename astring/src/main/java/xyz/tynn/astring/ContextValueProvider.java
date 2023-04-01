//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Parcel;

import androidx.annotation.NonNull;

enum ContextValueProvider implements AString {

    /**
     * Implementation providing {@link Context#getPackageName()}
     */
    AppIdProvider {
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

    ;

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }

    public static final Creator<ContextValueProvider> CREATOR = new Creator<ContextValueProvider>() {

        @Override
        public ContextValueProvider createFromParcel(Parcel source) {
            return ContextValueProvider.valueOf(source.readString());
        }

        @Override
        public ContextValueProvider[] newArray(int size) {
            return new ContextValueProvider[size];
        }
    };
}
