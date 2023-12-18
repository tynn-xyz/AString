//  Copyright 2023 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.compose

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Parcel
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import io.mockk.called
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain.outerRule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.model.Statement
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import xyz.tynn.astring.AString
import xyz.tynn.astring.asAString
import java.util.Locale
import java.util.Locale.FRENCH
import java.util.Locale.GERMANY
import java.util.Locale.UK

@Config(sdk = [33])
@RunWith(RobolectricTestRunner::class)
internal class AStringTest {

    private val compose = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val provideActivityChain: TestRule = outerRule { it, _ ->
        it.addActivity<ComponentActivity>()
    }.around(compose)

    @Test
    fun `asString should map null to empty string`() = compose.runTest {
        assertEquals(
            "",
            null.asAString().asString(),
        )
    }

    @Test
    fun `asString should get string value`() = compose.runTest {
        assertEquals(
            "value",
            "value".asAString().asString(),
        )
    }

    @Test
    fun `asString should update on configuration change`() {
        val languages = mutableListOf<CharSequence?>()
        compose.updateLocale(UK)
        compose.runTest { languages += LanguageAString.asString() }
        compose.updateLocale(GERMANY)
        compose.updateLocale(FRENCH)

        assertEquals(
            listOf("en", "de", "fr"),
            languages,
        )
    }

    @Test
    fun `aString should map null to empty string`() = compose.runTest {
        assertEquals(
            "",
            aString(AString.Null),
        )
    }

    @Test
    fun `aString should get string value`() = compose.runTest {
        assertEquals(
            "value",
            aString(AString("value")),
        )
    }

    @Test
    fun `aString should update on configuration change`() {
        val languages = mutableListOf<CharSequence?>()
        compose.updateLocale(UK)
        compose.runTest { languages += aString(LanguageAString) }
        compose.updateLocale(GERMANY)
        compose.updateLocale(FRENCH)

        assertEquals(
            listOf("en", "de", "fr"),
            languages,
        )
    }

    @Test
    @ExperimentalTextApi
    fun `asAnnotatedString should map null to empty annotated string`() = compose.runTest {
        mockkStatic(CharSequence::toAnnotatedString) {
            assertEquals(
                AnnotatedString(""),
                null.asAString().asAnnotatedString(),
            )

            verify { any<CharSequence>().toAnnotatedString() wasNot called }
        }
    }

    @Test
    @ExperimentalTextApi
    fun `asAnnotatedString should map spannable to annotated string`() = compose.runTest {
        mockkStatic(CharSequence::toAnnotatedString) {
            val charSequence = mockk<CharSequence>()
            val annotatedString = mockk<AnnotatedString>()
            val addSpan = mockk<AddSpan>()
            every { charSequence.toAnnotatedString(addSpan) } returns annotatedString

            assertSame(
                annotatedString,
                charSequence.asAString().asAnnotatedString(addSpan),
            )
        }
    }

    @Test
    @ExperimentalTextApi
    fun `asAnnotatedString should update on configuration change`() {
        val languages = mutableListOf<CharSequence?>()
        compose.updateLocale(UK)
        compose.runTest { languages += LanguageAString.asAnnotatedString() }
        compose.updateLocale(GERMANY)
        compose.updateLocale(FRENCH)

        assertEquals(
            listOf("en", "de", "fr").map(::AnnotatedString),
            languages,
        )
    }

    private data object LanguageAString : AString {
        override fun writeToParcel(
            dest: Parcel,
            flags: Int,
        ) = Unit

        override fun invoke(
            context: Context
        ) = context.resources.configuration
            .locales[0].language.toString()
    }

    private fun AndroidComposeTestRule<*, *>.updateLocale(
        locale: Locale,
    ) = activity.findViewById<ViewGroup>(
        android.R.id.content,
    ).dispatchConfigurationChanged(
        activity.resources.configuration.apply {
            setLocale(locale)
        }
    ).also { waitForIdle() }

    private inline fun ComposeContentTestRule.runTest(
        crossinline composable: @Composable () -> Unit,
    ) = setContent { composable() }

    private inline fun <reified T : Activity> Statement.addActivity() =
        object : Statement() {
            override fun evaluate() {
                val context = getApplicationContext<Context>()
                shadowOf(context.packageManager).addOrUpdateActivity(
                    ActivityInfo().apply {
                        name = T::class.java.name
                        packageName = context.packageName
                    },
                )
                this@addActivity.evaluate()
            }
        }
}
