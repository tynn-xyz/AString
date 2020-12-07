//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.testing.mockk

import io.mockk.MockKAnnotations
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify

fun init(
    obj: Any,
    relaxed: Boolean,
) = MockKAnnotations.init(
    obj,
    relaxed = relaxed,
)

fun <T : Any> prepare(
    type: Class<T>,
) = mockkStatic(type.kotlin)

fun clearAll() = unmockkAll()

fun verify(
    verifyBlock: UnitBlock,
) = verify {
    verifyBlock()
}
