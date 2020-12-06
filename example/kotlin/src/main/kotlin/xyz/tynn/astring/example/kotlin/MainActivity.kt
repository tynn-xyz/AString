//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.example.kotlin

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getDrawable
import xyz.tynn.astring.*
import xyz.tynn.astring.example.common.*
import xyz.tynn.astring.example.common.databinding.ActivityMainBinding
import xyz.tynn.astring.example.common.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {

    private val tag by aString

    private val errorDrawable: Drawable?
        get() = getDrawable(this, R.drawable.ic_error_icon)?.apply {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate(layoutInflater).setupAStrings()
    }

    private fun ActivityMainBinding.setupAStrings() {
        Log.d(tag.toString(), "in setupAStrings")
        setContentView(root)
        viewText.setText(text)
        viewTextType.setText(textType, TextView.BufferType.SPANNABLE)
        viewAppend.append(append)
        viewAppendRange.append(appendRange, 1, 7)
        viewHint.setHint(hint)
        viewError.setError(error)
        viewErrorIcon.setError(errorIcon, errorDrawable)
        viewView.setAccessibilityPaneTitle(accessibilityPaneTitle)
        viewView.setContentDescription(contentDescription)
        viewView.setStateDescription(stateDescription)
        viewView.setTooltipText(tooltipText)
    }
}
