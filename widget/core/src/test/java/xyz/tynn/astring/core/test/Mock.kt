//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core.test

import io.mockk.mockkStatic
import io.mockk.verify

internal fun <T : Any> prepare(
    type: Class<T>,
) = mockkStatic(type.kotlin)

internal fun verify(
    verifyBlock: Runnable,
) = verify {
    verifyBlock.run()
}
