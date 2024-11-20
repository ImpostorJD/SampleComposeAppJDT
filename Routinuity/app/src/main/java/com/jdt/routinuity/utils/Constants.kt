package com.jdt.routinuity.utils

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import java.time.format.DateTimeFormatter
import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd, MMM yyyy")

inline fun Color.toHex(): String {
    val red = (this.red * 255).toInt()
    val green = (this.green * 255).toInt()
    val blue = (this.blue * 255).toInt()
    val alpha = (this.alpha * 255).toInt()

    return String.format("#%02X%02X%02X%02X", alpha, red, green, blue)
}


fun String.toColor(): Color {
    return try {
        Color(AndroidColor.parseColor(this))
    } catch (e: IllegalArgumentException) {
        Color.Black
    }
}


@Composable
fun RawImageViewer(
    rawResource: Int
){
    val context = LocalContext.current
    val bitmap = remember {

        BitmapFactory.decodeStream(context.resources.openRawResource(rawResource))
    }

    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Fit
    )

}