//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

@file:[JvmName("AStringFactory") JvmMultifileClass Suppress("FunctionName")]

package xyz.tynn.astring

import android.content.Context
import android.content.pm.PackageInfo

/**
 * An `AString` always providing the application id
 *
 * @see Context.getPackageName
 */
public val AppId: AString = Provider.AppId

/**
 * An `AString` always providing the application version
 *
 * @see PackageInfo.versionName
 */
public val AppVersion: AString = Provider.AppVersion
