//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.test

import io.mockk.MockKAnnotations
import io.mockk.verify

internal fun init(
    obj: Any,
    relaxed: Boolean,
) = MockKAnnotations.init(
    obj,
    relaxed = relaxed,
)

internal fun verify(
    verifyBlock: Runnable,
) = verify {
    verifyBlock.run()
}
