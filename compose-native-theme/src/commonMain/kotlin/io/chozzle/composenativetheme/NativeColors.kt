package io.chozzle.composenativetheme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class NativeColors(
    val baseLow: Color,
    val baseMediumLow: Color,
    val listLow: Color,
    val accent: Color,
    val accentLight: Color,
    val accentDark: Color,
    val border: Color,
    val borderDark: Color,
    val surfaceBorder: Color,
    val surfaceDark: Color,
    val primary50: Color
)

val nativeLightPalette = NativeColors(
    baseLow = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.2f),
    baseMediumLow = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.4f),
    listLow = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.1f),
    accent = Color(0xFF0078D4), // blue
    accentLight = Color(0xFF429CE3), // light blue
    accentDark = Color(0xFF005A9E), // light blue
    border = Color(0xFFE5E5E5), // light gray
    borderDark = Color(0xFF919191), // light gray
    surfaceBorder = Color(0xFFC1C1C1), // light gray
    surfaceDark = Color(0xFFEDEDED), // light gray
    primary50 = Color(0xFF797979), // light gray
)


internal val NativeDisabledBackgroundColor: Color
    @Composable
    get() = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)


// TODO handle dark theme here
internal val NativeDisabledContentColor: Color
    @Composable
    get() = nativeLightPalette.primary50