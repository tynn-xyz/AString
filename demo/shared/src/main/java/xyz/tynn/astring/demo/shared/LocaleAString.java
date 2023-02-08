//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.demo.shared;

import static androidx.core.os.ConfigurationCompat.getLocales;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;

import xyz.tynn.astring.AString;

final class LocaleAString implements AString {

    @Nullable
    @Override
    public CharSequence invoke(@NonNull Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        Locale locale = getLocales(configuration).get(0);
        return locale == null ? null : locale.getDisplayName(locale);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Creator<LocaleAString> CREATOR = new Creator<LocaleAString>() {

        @Override
        public LocaleAString createFromParcel(Parcel source) {
            return new LocaleAString();
        }

        @Override
        public LocaleAString[] newArray(int size) {
            return new LocaleAString[size];
        }
    };
}
