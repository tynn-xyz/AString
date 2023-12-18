//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring

import xyz.tynn.astring.Predicate.NonBlank
import xyz.tynn.astring.Predicate.NonEmpty
import xyz.tynn.astring.Predicate.NonNull
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class PredicateTest {

    @Test
    fun `NonBlank should be named`() {
        assertEquals(
            "NonBlank",
            NonBlank.toString(),
        )
    }

    @Test
    fun `NonBlank should be false for null and blank`() {
        assertFalse(NonBlank.test(null))
        assertFalse(NonBlank.test(""))
        assertFalse(NonBlank.test(" "))
        assertFalse(NonBlank.test("\r"))
        assertFalse(NonBlank.test("\n\t"))
        assertTrue(NonBlank.test("\n\t 3\n"))
    }

    @Test
    fun `NonEmpty should be named`() {
        assertEquals(
            "NonEmpty",
            NonEmpty.toString(),
        )
    }

    @Test
    fun `NonEmpty should be false for null and empty`() {
        assertFalse(NonEmpty.test(null))
        assertFalse(NonEmpty.test(""))
        assertTrue(NonEmpty.test(" "))
        assertTrue(NonEmpty.test("\r"))
        assertTrue(NonEmpty.test("\n\t"))
        assertTrue(NonEmpty.test("\n\t 3\n"))
    }

    @Test
    fun `NonNull should be named`() {
        assertEquals(
            "NonNull",
            NonNull.toString(),
        )
    }

    @Test
    fun `NonNull should be false for null`() {
        assertFalse(NonNull.test(null))
        assertTrue(NonNull.test(""))
        assertTrue(NonNull.test(" "))
        assertTrue(NonNull.test("\r"))
        assertTrue(NonNull.test("\n\t"))
        assertTrue(NonNull.test("\n\t 3\n"))
    }
}
