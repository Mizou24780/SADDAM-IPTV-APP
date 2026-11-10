package com.saddam.iptv

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private val purple = Color.rgb(168, 85, 247)
    private val backgroundColor = Color.rgb(9, 6, 17)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 30, 28, 30)
            setBackgroundColor(backgroundColor)
        }

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
        addMenu(root, "ACTIVATE DEVICE")

        setContentView(root)
    }

    private fun addMenu(root: LinearLayout, label: String) {
        val button = TextView(this).apply {
            text = label
            textSize = 18f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(42, 23, 60))
            isFocusable = true
            isClickable = true
            setPadding(20, 18, 20, 18)
        }

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 12, 0, 12)

        root.addView(button, params)
    }
}
