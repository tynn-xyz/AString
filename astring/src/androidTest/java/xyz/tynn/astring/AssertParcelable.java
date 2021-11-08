//  Copyright 2021 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring;

import static android.os.Parcel.obtain;
import static org.junit.Assert.assertEquals;

import android.os.Parcel;
import android.os.Parcelable;

class AssertParcelable {

    private static ClassLoader getClassloader() {
        return AssertParcelable.class.getClassLoader();
    }

    static <T extends Parcelable> void assertParcelable(T expected) {
        Parcel parcel = obtain();
        parcel.writeValue(expected);
        parcel.setDataPosition(0);
        assertEquals(expected, parcel.readValue(getClassloader()));
    }
}
