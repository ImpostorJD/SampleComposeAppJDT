package com.jdt.routinuity.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import android.graphics.Color as AndroidColor

/**
 * Usage: val color = "#FFFFFF".toColor()
 */
fun String.toColor(): Color {
    return try {
        Color(AndroidColor.parseColor(this))
    } catch (e: IllegalArgumentException) {
        Color.Black
    }
}
