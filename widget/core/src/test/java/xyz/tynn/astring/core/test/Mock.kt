//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core.test

import io.mockk.MockKAnnotations
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify

internal fun init(
    obj: Any,
    relaxed: Boolean,
) = MockKAnnotations.init(
    obj,
    relaxed = relaxed,
)

internal fun <T : Any> prepare(
    type: Class<T>,
) = mockkStatic(type.kotlin)

internal fun clearAll() = unmockkAll()

internal fun verify(
    verifyBlock: Runnable,
) = verify {
    verifyBlock.run()
}
