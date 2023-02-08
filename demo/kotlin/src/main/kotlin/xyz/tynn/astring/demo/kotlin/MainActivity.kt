//  Copyright 2020 Christian Schmitz
//  SPDX-License-Identifier: Apache-2.0

package xyz.tynn.astring.demo.kotlin

import android.app.AlertDialog
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getDrawable
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar.make
import xyz.tynn.astring.aString
import xyz.tynn.astring.appcompat.*
import xyz.tynn.astring.core.*
import xyz.tynn.astring.demo.shared.*
import xyz.tynn.astring.demo.shared.databinding.ActivityMainBinding
import xyz.tynn.astring.demo.shared.databinding.ActivityMainBinding.inflate
import xyz.tynn.astring.getValue
import xyz.tynn.astring.material.makeSnackbar
import xyz.tynn.astring.material.setAction
import xyz.tynn.astring.material.setText

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
        setupDialogs()
        viewAppId.setText(aStringPackage)
        viewAppVersion.setText(aStringVersion)
        viewAppLocale.setText(aStringLocale)
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

    private fun ActivityMainBinding.setupDialogs() {
        buttonAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this@MainActivity)
                .setPositiveButton(action1, null)
                .setNeutralButton(action2, null)
                .setNegativeButton(action3, null)
                .setMessage(message)
                .create()
                .apply { setTitle(xyz.tynn.astring.demo.shared.title) }
                .show()
        }
        buttonAlertDialogLegacy.setOnClickListener {
            AlertDialog.Builder(this@MainActivity)
                .setTitle(xyz.tynn.astring.demo.shared.title)
                .setMessage(message)
                .setPositiveButton(action1, null)
                .setNeutralButton(action2, null)
                .create()
                .apply { setButton(BUTTON_NEGATIVE, action3) { _, _ -> } }
                .show()
        }
        buttonSnackbar.setOnClickListener {
            makeSnackbar(root, dialog, LENGTH_LONG)
                .show()
        }
        buttonSnackbarUpdate.setOnClickListener {
            make(root, "", LENGTH_LONG)
                .setText(dialog)
                .setAction(action1) {}
                .show()
        }
        buttonToast.setOnClickListener {
            makeToast(this@MainActivity, dialog, LENGTH_SHORT)
                .show()
        }
        buttonToastUpdate.setOnClickListener {
            makeText(this@MainActivity, "", LENGTH_SHORT)
                .apply { setText(aString(dialog)) }
                .show()
        }
        buttonKotlinSupport.setOnClickListener {
            makeToast(this@MainActivity, kotlin, LENGTH_SHORT)
                .show()
        }
    }
}
