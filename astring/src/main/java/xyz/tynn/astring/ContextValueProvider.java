//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static android.content.pm.PackageManager.PackageInfoFlags.of;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.TIRAMISU;

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
        @SuppressWarnings("deprecation")
        public CharSequence invoke(@NonNull Context context) {
            try {
                String packageName = context.getPackageName();
                PackageManager packageManager = context.getPackageManager();
                if (SDK_INT < TIRAMISU)
                    return packageManager.getPackageInfo(packageName, 0).versionName;
                return packageManager.getPackageInfo(packageName, of(0)).versionName;
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
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name());
    }

    public static final Creator<ContextValueProvider> CREATOR = new Creator<>() {

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
