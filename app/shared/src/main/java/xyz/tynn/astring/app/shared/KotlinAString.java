//  Copyright 2022 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.app.shared;

import static android.util.Log.w;
import static xyz.tynn.astring.app.shared.KotlinAStringKt.kotlinAString;

import android.content.Context;
import android.os.Parcel;

import androidx.annotation.NonNull;

import xyz.tynn.astring.AString;

final class KotlinAString implements AString {

    @NonNull
    @Override
    public CharSequence invoke(@NonNull Context context) {
        try {
            return kotlinAString("Kotlin supported");
        } catch (Throwable t) {
            w("KotlinAString", t);
            return "Java only";
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Creator<KotlinAString> CREATOR = new Creator<KotlinAString>() {

        @Override
        public KotlinAString createFromParcel(Parcel source) {
            return new KotlinAString();
        }

        @Override
        public KotlinAString[] newArray(int size) {
            return new KotlinAString[size];
        }
    };
}
