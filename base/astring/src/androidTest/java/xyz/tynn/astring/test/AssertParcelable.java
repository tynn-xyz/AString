//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.test;

import static android.os.Parcel.obtain;
import static androidx.core.os.ParcelCompat.readParcelable;
import static org.junit.Assert.assertEquals;

import android.os.Parcel;
import android.os.Parcelable;

public class AssertParcelable {

    private static ClassLoader getClassloader() {
        return AssertParcelable.class.getClassLoader();
    }

    public static <T extends Parcelable> void assertParcelable(T expected) {
        Parcel parcel = obtain();
        try {
            parcel.writeParcelable(expected, 0);
            parcel.setDataPosition(0);
            assertEquals(expected, readParcelable(parcel, getClassloader(), expected.getClass()));
        } finally {
            parcel.recycle();
        }
    }
}
