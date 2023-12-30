//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:[JvmName("AStringFactory") JvmMultifileClass Suppress("FunctionName")]

package xyz.tynn.astring

import android.content.Context
import android.content.pm.PackageInfo
import android.os.Parcelable
import java.io.Serializable

/**
 * Creates an [AString] by wrapping the [AString.Provider]
 *
 * **Note**: While [AString] implements [Parcelable],
 * the [AString.Provider] only implements [Serializable]
 * and thus can be implemented with a single lambda
 */
@[InefficientAStringApi JvmName("createAString")]
public fun AString(
    provider: AString.Provider,
): AString = Delegate.wrap(
    provider,
)

/**
 * An `AString` always providing the application id
 *
 * @see Context.getPackageName
 */
@OptIn(InefficientAStringApi::class)
public val AppId: AString = Delegate.wrap(
    Provider.AppId,
)

/**
 * An `AString` always providing the application version
 *
 * @see PackageInfo.versionName
 */
@OptIn(InefficientAStringApi::class)
public val AppVersion: AString = Delegate.wrap(
    Provider.AppVersion,
)
