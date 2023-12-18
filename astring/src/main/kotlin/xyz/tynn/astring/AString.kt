//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import android.os.Parcelable
import android.text.Spannable
import android.view.View
import androidx.fragment.app.Fragment
import java.io.Serializable
import java.util.Objects.requireNonNull
import kotlin.reflect.KProperty

/**
 * A [String] abstraction for *Android*.
 *
 * The purpose of this type is to provide context sensitive strings.
 * This could be a plain [CharSequence] or a [Spannable],
 * a string resource, a formatted quantity string or something completely different.
 *
 * **Note**: `AString` is almost always used from the main thread,
 * therefore all implementations must be non-blocking.
 */
public interface AString : Parcelable {

    /**
     * Provides a context sensitive string
     *
     * @param context to access resources
     * @return string value from context; might be null
     */
    public operator fun invoke(context: Context): CharSequence?
    public override fun describeContents(): Int = 0
    public override fun equals(other: Any?): Boolean
    public override fun hashCode(): Int

    public companion object {

        /**
         * An `AString` always providing `null`
         */
        @JvmField
        public val Null: AString = Wrapper.wrap(null)
    }

    /**
     * Functional interface providing a [CharSequence] from a [Context]
     *
     * This differs from [AString] in not implementing [Parcelable]
     */
    @InefficientAStringApi
    public fun interface Provider : Serializable {
        public operator fun invoke(context: Context): CharSequence?
    }

    /**
     * Functional interface transforming a [CharSequence] provided by an [AString]
     */
    @InefficientAStringApi
    public fun interface Transformer : Serializable {
        public operator fun invoke(value: CharSequence?): CharSequence?
    }
}

/**
 * Invokes the [aString] with [Context]
 */
@JvmName("invokeWithContext")
public fun Context.aString(
    aString: AString?,
): CharSequence? = aString?.invoke(
    requireNonNull(this, "context"),
)

/**
 * Invokes the [aString] with [Fragment.requireContext]
 *
 * @throws IllegalStateException if not currently associated with a context
 */
@JvmName("invokeWithFragment")
public fun Fragment.aString(
    aString: AString?,
): CharSequence? = aString?.invoke(
    requireContext(),
)

/**
 * Invokes the [aString] with [View.getContext]
 */
@JvmName("invokeWithView")
public fun View.aString(
    aString: AString?,
): CharSequence? = aString?.invoke(
    context,
)

/**
 * Delegates a `CharSequence?` property within a [Context] to the `AString`
 */
@JvmSynthetic
public operator fun AString?.getValue(
    thisRef: Context,
    property: KProperty<*>,
): CharSequence? = thisRef.aString(
    this,
)

/**
 * Delegates a `CharSequence?` property within a [Fragment] to the `AString`
 */
@JvmSynthetic
public operator fun AString?.getValue(
    thisRef: Fragment,
    property: KProperty<*>,
): CharSequence? = thisRef.aString(
    this,
)

/**
 * Delegates a `CharSequence?` property within a [View] to the `AString`
 */
@JvmSynthetic
public operator fun AString?.getValue(
    thisRef: View,
    property: KProperty<*>,
): CharSequence? = thisRef.aString(
    this,
)
