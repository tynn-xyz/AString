//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.appcompat.test

import io.mockk.justRun
import io.mockk.mockkStatic
import io.mockk.verify

internal fun <T : Any> prepare(
    type: Class<T>,
) = mockkStatic(type.kotlin)

internal fun justRun(
    stubBlock: Runnable,
) = justRun {
    stubBlock.run()
}

internal fun verify(
    verifyBlock: Runnable,
) = verify {
    verifyBlock.run()
}
