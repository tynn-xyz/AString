//  Copyright 2024-2026 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.integration.compose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import xyz.tynn.astring.AString
import xyz.tynn.astring.TextResource
import xyz.tynn.astring.compose.asAnnotatedString
import xyz.tynn.astring.compose.asString
import xyz.tynn.astring.core.setText

@[Composable PreviewConfig]
@OptIn(ExperimentalTextApi::class)
fun ComposeWidget(aString: AString = TextResource(R.string.astring)) {
    Column(Modifier.fillMaxSize(), SpaceAround, CenterHorizontally) {
        Text(aString.asString())
        Text(aString.asAnnotatedString())
        UriText(aString.asAnnotatedString())
        AndroidTextView { it.setText(aString) }
    }
}

@Composable
@Suppress("DEPRECATION")
private fun UriText(text: AnnotatedString) {
    val uriHandler = LocalUriHandler.current
    ClickableText(text) { offset ->
        text.getLinkAnnotations(
            start = offset,
            end = offset,
        ).firstNotNullOfOrNull { (link) ->
            link as? LinkAnnotation.Url
        }?.url?.let(uriHandler::openUri)
    }
}

@Composable
private fun AndroidTextView(update: (TextView) -> Unit) {
    AndroidView(
        update = update,
        factory = {
            TextView(it).apply {
                setTextColor(android.graphics.Color.BLACK)
                movementMethod = LinkMovementMethod.getInstance()
            }
        },
    )
}

@Preview(widthDp = 180, heightDp = 320, uiMode = UI_MODE_NIGHT_NO)
@Preview(widthDp = 180, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Preview(widthDp = 320, heightDp = 180, uiMode = UI_MODE_NIGHT_YES)
internal annotation class PreviewConfig
