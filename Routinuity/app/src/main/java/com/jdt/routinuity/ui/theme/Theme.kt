package com.jdt.routinuity.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight


private val LightColors = lightColorScheme(
    primary = Color(0xFF1b1b1b),
    secondary = Color(0xFFf3f3f3),
    background = Color(0xFFf3f3f3),
    surface = Color(0xFFFFFFFF),
//    onPrimary = Color(0xFFFFFFFF),
//    primaryContainer = Color(0xFFFFFFFF),
//    onPrimaryContainer = Color(0xFFFFFFFF),
//    inversePrimary = Color(0xFFFFFFFF),
//    onSecondary = Color(0xFFFFFFFF),
//    secondaryContainer = Color(0xFFFFFFFF),
//    onSecondaryContainer = Color(0xFFFFFFFF),
//    tertiary = Color(0xFFFFFFFF),
//    onTertiary = Color(0xFFFFFFFF),
//    tertiaryContainer = Color(0xFFFFFFFF),
//    onTertiaryContainer = Color(0xFFFFFFFF),
//    onBackground = Color(0xFFFFFFFF),
//    onSurface = Color(0xFFFFFFFF),
//    surfaceVariant = Color(0xFFFFFFFF),
//    onSurfaceVariant = Color(0xFFFFFFFF),
//    surfaceTint = Color(0xFFFFFFFF),
//    inverseSurface = Color(0xFFFFFFFF),
//    inverseOnSurface = Color(0xFFFFFFFF)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF60FFFF),
    background = Color(0xFF121212),
    surface = Color(0xFF1F1F1F),
)

// Define your typography for Material3
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
)

@Composable
fun RoutinuityTheme(darkTheme: Boolean = isSystemInDarkTheme(),
     content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content,

    )
}
