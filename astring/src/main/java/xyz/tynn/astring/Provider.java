//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

@SuppressLint("UnsafeOptInUsageError")
enum Provider implements AStringProvider {

    /**
     * Implementation providing {@link Context#getPackageName()}
     */
    AppId {
        @Override
        public String invoke(@NonNull Context context) {
            return context.getPackageName();
        }

        @NonNull
        @Override
        public String toString() {
            return "Context(" + "AppId" + ')';
        }
    },

    /**
     * Implementation providing {@link PackageInfo#versionName}
     */
    AppVersion {
        @Override
        public String invoke(@NonNull Context context) {
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
            return "Context(" + "AppVersion" + ')';
        }
    },

    ;

    AString toAString() {
        return Delegate.wrap(this);
    }
}
