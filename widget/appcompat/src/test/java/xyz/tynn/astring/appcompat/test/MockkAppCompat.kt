//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat.test

import android.text.TextUtils.isEmpty
import io.mockk.every

internal fun mockkTextUtilsIsEmpty() {
    every {
        isEmpty(any())
    } answers {
        firstArg<CharSequence?>().isNullOrEmpty()
    }
}
