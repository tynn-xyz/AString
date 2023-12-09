//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import kotlin.test.Test
import kotlin.test.assertSame

internal class AStringCompanionKtTest {

    @Test
    fun `Null should be the NULL singleton`() {
        assertSame(
            Wrapper.wrap(null),
            AString.Null,
        )
        assertSame(
            AString.Null,
            AString.Null,
        )
    }
}
