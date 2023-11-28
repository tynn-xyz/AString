//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class QuantityTextResourceTest {

    @Test
    fun `invoke should return text`() {
        val context = mockk<Context> {
            every { resources.getQuantityText(1, 2) } returns "foo"
        }

        assertEquals(
            "foo",
            Resource.wrap(1, 2).invoke(context),
        )
    }

    @Test
    fun `equals should be true for matching text resources and quantities`() {
        assertTrue {
            Resource.wrap(1, 2) ==
                    Resource.wrap(1, 2)
        }
    }

    @Test
    fun `equals should be false for non matching text resources or quantities`() {
        assertFalse {
            Resource.wrap(1, 2) ==
                    Resource.wrap(1, 1)
        }
        assertFalse {
            Resource.wrap(1, 2) ==
                    Resource.wrap(2, 2)
        }
    }

    @Test
    fun `equals should be false for non QuantityTextResourceDelegate`() {
        assertFalse {
            Resource.wrap(1, 2).equals("foo")
        }
        assertFalse {
            Resource.wrap(1, 2) == mockk<AString>()
        }
        assertFalse {
            Resource.wrap(1, 2).equals(mockk<Wrapper>())
        }
        assertFalse {
            Resource.wrap(1, 2).equals(mockk<Provider>())
        }
        assertFalse {
            Resource.wrap(1, 2).equals(mockk<Resource>())
        }
    }

    @Test
    fun `hashCode should return delegate to text resource and quantity`() {
        assertEquals(
            994,
            Resource.wrap(1, 2).hashCode(),
        )
        assertEquals(
            1025,
            Resource.wrap(2, 2).hashCode(),
        )
    }

    @Test
    fun `toString should return typed string`() {
        assertEquals(
            "AString(QuantityTextResource(1,2))",
            Resource.wrap(1, 2).toString(),
        )
    }
}
