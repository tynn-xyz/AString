//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.test;

import static android.os.Parcel.obtain;
import static androidx.core.os.ParcelCompat.readParcelable;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import android.content.Context;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import xyz.tynn.astring.AString;

/**
 * A set of assertion methods useful for testing implementations of
 * {@link AString}s
 * <p>
 * While identity or equality are encouraged for efficiency on use-cases
 * reducing the invocation count, the assertion on the invocation
 * {@link #assertParcelableAStringInvocation(AString)} checks, that the
 * implementation is consistent.
 */
public class AStringAssert {

    /**
     * Asserts that the {@code aString} and the instance recovered from the
     * {@link Parcel} refer to the same object. If they are not,
     * an {@link AssertionError} is thrown with the given message.
     *
     * @param message to use for the AssertionError, or null
     * @param aString to check
     */
    public static void assertParcelableAStringIdentity(
            @Nullable String message, @NonNull AString aString) {
        try (ParcelDelegate parcel = new ParcelDelegate()) {
            assertSame(message, aString, parcel.writeAndRead(aString));
        }
    }

    /**
     * Asserts that the {@code aString} and the instance recovered from the
     * {@link Parcel} refer to the same object. If they are not the same,
     * an {@link AssertionError} with a default message is thrown.
     *
     * @param aString to check
     */
    public static void assertParcelableAStringIdentity(@NonNull AString aString) {
        assertParcelableAStringIdentity(
                "AString read from parcel not identical to expected", aString);
    }

    /**
     * Asserts that the {@code aString} and the instance recovered from the
     * {@link Parcel} are equal. If they are not equal,
     * an {@link AssertionError} is thrown with the given message.
     *
     * @param message to use for the AssertionError, or null
     * @param aString to check
     */
    public static void assertParcelableAStringEquality(
            @Nullable String message, @NonNull AString aString) {
        try (ParcelDelegate parcel = new ParcelDelegate()) {
            assertEquals(message, aString, parcel.writeAndRead(aString));
        }
    }

    /**
     * Asserts that the {@code aString} and the instance recovered from the
     * {@link Parcel} are equal. If they are not equal,
     * an {@link AssertionError} with a default message is thrown.
     *
     * @param aString to check
     */
    public static void assertParcelableAStringEquality(@NonNull AString aString) {
        assertParcelableAStringEquality(
                "AString read from parcel not equal to expected", aString);
    }

    /**
     * Asserts that the result of the invocation on {@code aString} and the
     * result of the invocation on the instance recovered from the
     * {@link Parcel} are equal. If they are not equal,
     * an {@link AssertionError} is thrown with the given message.
     *
     * @param message to use for the AssertionError, or null
     * @param aString to check
     */
    public static void assertParcelableAStringInvocation(
            @Nullable String message, @NonNull AString aString) {
        Context context = getApplicationContext();
        try (ParcelDelegate parcel = new ParcelDelegate()) {
            AString newAString = parcel.writeAndRead(aString);
            assertEquals(message, aString.invoke(context), newAString.invoke(context));
        }
    }

    /**
     * Asserts that the result of the invocation on {@code aString} and the
     * result of the invocation on the instance recovered from the
     * {@link Parcel} are equal. If they are not equal,
     * an {@link AssertionError} with a default message is thrown.
     *
     * @param aString to check
     */
    public static void assertParcelableAStringInvocation(@NonNull AString aString) {
        assertParcelableAStringInvocation(
                "result of AString read from parcel not equal to expected result",
                aString);
    }

    private static class ParcelDelegate implements AutoCloseable {

        private final Parcel parcel = obtain();

        AString writeAndRead(AString aString) {
            int dataPosition = parcel.dataPosition();
            parcel.writeParcelable(aString, 0);
            parcel.setDataPosition(dataPosition);
            Class<? extends AString> aClass = aString.getClass();
            ClassLoader classloader = aClass.getClassLoader();
            return readParcelable(parcel, classloader, aClass);
        }

        @Override
        public void close() {
            parcel.recycle();
        }
    }
}
