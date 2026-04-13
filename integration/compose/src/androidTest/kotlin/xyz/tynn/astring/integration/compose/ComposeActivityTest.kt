//  Copyright 2024 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.integration.compose

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.AnnotatedString
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.SmallTest
import org.junit.Rule
import org.junit.Test

@SmallTest
class ComposeActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComposeActivity>()

    @Test
    fun composeActivityTest() {
        val defaultText = "AString value"
        composeTestRule.activity.runOnUiThread {
            setDefaultNightMode(MODE_NIGHT_NO)
        }

        composeTestRule.onNode(
            matcher = withString(defaultText),
        ).assertIsDisplayed()

        composeTestRule.onAllNodes(
            matcher = withAnnotatedString(defaultText),
        ).apply {
            this[0].isDisplayed()
            this[1].isDisplayed()
        }

        onView(withText(defaultText))
            .check(matches(isDisplayed()))

        val nightText = "AString ■■■■■"
        composeTestRule.activity.runOnUiThread {
            setDefaultNightMode(MODE_NIGHT_YES)
        }

        composeTestRule.onNode(
            matcher = withString(nightText),
        ).assertIsDisplayed()

        composeTestRule.onAllNodes(
            matcher = withAnnotatedString(nightText),
        ).apply {
            this[0].isDisplayed()
            this[1].isDisplayed()
        }

        onView(withText(nightText))
            .check(matches(isDisplayed()))

        val landText = "eulav AString"
        composeTestRule.activity.requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        composeTestRule.activityRule.scenario.recreate() // trigger AndroidView.update

        composeTestRule.onNode(
            matcher = withString(landText),
        ).assertIsDisplayed()

        composeTestRule.onAllNodes(
            matcher = withAnnotatedString(landText),
        ).apply {
            this[0].isDisplayed()
            this[1].isDisplayed()
        }

        onView(withText(landText))
            .check(matches(isDisplayed()))
    }
}

private fun withString(text: String) = withAnnotatedString(text) {
    it == AnnotatedString(it.toString())
}

private fun withAnnotatedString(text: String) = withAnnotatedString(text) {
    it != AnnotatedString(it.toString())
}

private fun withAnnotatedString(
    text: String,
    filter: (AnnotatedString) -> Boolean,
): SemanticsMatcher {
    val propertyName = "${SemanticsProperties.Text.name} + ${SemanticsProperties.EditableText.name}"
    return SemanticsMatcher("$propertyName contains '$text'") { node ->
        sequence {
            node.config.getOrNull(SemanticsProperties.EditableText)?.let {
                yield(it)
            }
            node.config.getOrNull(SemanticsProperties.Text)?.forEach {
                yield(it)
            }
        }.any {
            filter(it) && it.text.equals(text, false)
        }
    }
}
