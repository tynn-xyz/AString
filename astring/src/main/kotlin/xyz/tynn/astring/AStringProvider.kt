//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:[JvmName("AStringFactory") JvmMultifileClass Suppress("FunctionName")]

package xyz.tynn.astring

import android.content.Context
import android.content.pm.PackageInfo
import android.os.Parcelable
import java.io.Serializable

/**
 * Functional interface providing a [CharSequence] from a [Context]
 *
 * This differs from [AString] in not implementing [Parcelable]
 */
@InefficientAStringApi
public fun interface AStringProvider : Serializable {
    public operator fun invoke(context: Context): CharSequence?
}

/**
 * Creates an [AString] by wrapping the [AStringProvider]
 *
 * **Note**: While [AString] implements [Parcelable],
 * the [AStringProvider] only implements [Serializable]
 * and thus can be implemented with a single lambda
 */
@[InefficientAStringApi JvmName("createAString")]
public fun AString(
    provider: AStringProvider,
): AString = Delegate.wrap(
    provider,
)

/**
 * An `AString` always providing the application id
 *
 * @see Context.getPackageName
 */
public val AppId: AString = Provider.AppId.toAString()

/**
 * An `AString` always providing the application version
 *
 * @see PackageInfo.versionName
 */
public val AppVersion: AString = Provider.AppVersion.toAString()
