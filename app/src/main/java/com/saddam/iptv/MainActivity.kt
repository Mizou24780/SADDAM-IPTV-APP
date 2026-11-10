
package com.saddam.iptv

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.provider.Settings
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val purple = Color.rgb(168, 85, 247)
    private val backgroundColor = Color.rgb(9, 6, 17)
    private var showingActivation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun createRoot(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 30, 28, 30)
            setBackgroundColor(backgroundColor)
        }
    }

    private fun addMenu(root: LinearLayout, label: String, action: (() -> Unit)? = null) {
        val button = TextView(this).apply {
            text = label
            textSize = 18f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(42, 23, 60))
            isFocusable = true
            isClickable = true
            setPadding(20, 18, 20, 18)
            setOnClickListener { action?.invoke() }
        }

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 12, 0, 12)
        root.addView(button, params)
    }

    private fun showHome() {
        showingActivation = false
        val root = createRoot()

        val logo = TextView(this).apply {
            text = "SADDAM IPTV"
            textSize = 30f
            setTextColor(purple)
            gravity = Gravity.CENTER
        }
        root.addView(logo)

        val subtitle = TextView(this).apply {
            text = "YOUR ENTERTAINMENT WORLD"
            textSize = 12f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }
        root.addView(subtitle)

        addMenu(root, "LIVE TV")
        addMenu(root, "MOVIES")
        addMenu(root, "SERIES")
        addMenu(root, "ACTIVATE DEVICE") {
            showActivation()
        }

        setContentView(root)
    }

    private fun showActivation() {
        showingActivation = true
        val root = createRoot()

        val title = TextView(this).apply {
            text = "DEVICE ACTIVATION"
            textSize = 25f
            setTextColor(purple)
            gravity = Gravity.CENTER
        }
        root.addView(title)

        val info = TextView(this).apply {
            text = "YOUR DEVICE ID"
            textSize = 16f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            setPadding(0, 35, 0, 15)
        }
        root.addView(info)

        val deviceId = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
        ) ?: "UNAVAILABLE"

        val idText = TextView(this).apply {
            text = deviceId
            textSize = 16f
            setTextColor(purple)
            gravity = Gravity.CENTER
            setPadding(12, 18, 12, 18)
            setBackgroundColor(Color.rgb(42, 23, 60))
        }
        root.addView(idText)

        addMenu(root, "COPY DEVICE ID") {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("Device ID", deviceId))
            Toast.makeText(this, "Device ID copied", Toast.LENGTH_SHORT).show()
        }

        addMenu(root, "BACK") {
            showHome()
        }

        setContentView(root)
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        if (showingActivation) {
            showHome()
        } else {
            super.onBackPressed()
        }
    }
}

