package io.chozzle.composemacostheme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class MacColors(
    val primary: Color,
    val highlight: Color,
    val border: Color,
    val borderDark: Color,
    val surfaceBorder: Color,
    val surfaceDark: Color,
    val primary50: Color
)

val macLightPalette = MacColors(
    primary = Color(red = 48, green = 123, blue = 247), // blue
    highlight = Color(0xFF94B2F4), // light blue
    border = Color(0xFFE5E5E5), // light gray
    borderDark = Color(0xFFE3E3E3), // light gray
    surfaceBorder = Color(0xFFC1C1C1), // light gray
    surfaceDark = Color(0xFFEDEDED), // light gray
    primary50 = Color(0xFF797979), // light gray
)


internal val MacDisabledBackgroundColor: Color
    @Composable
    get() = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)


// TODO handle dark theme here
internal val MacDisabledContentColor: Color
    @Composable
    get() = macLightPalette.primary50