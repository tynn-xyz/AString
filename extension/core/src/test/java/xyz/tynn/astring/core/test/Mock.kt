//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.core.test

import android.view.View
import io.mockk.justRun
import io.mockk.mockkStatic
import io.mockk.verify
import java.util.function.BiConsumer

internal fun <T : Any> prepare(
    type: Class<T>,
) = mockkStatic(type.kotlin)

internal fun justRun(
    stubBlock: BiConsumer<View, CharSequence>,
) = justRun {
    stubBlock.accept(any(), any())
}

internal fun verify(
    verifyBlock: Runnable,
) = verify {
    verifyBlock.run()
}
