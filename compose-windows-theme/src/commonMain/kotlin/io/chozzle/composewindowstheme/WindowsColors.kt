package io.chozzle.composewindowstheme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class WindowsColors(
    val primary: Color,
    val highlight: Color,
    val border: Color,
    val borderDark: Color,
    val surfaceBorder: Color,
    val surfaceDark: Color,
    val primary50: Color
)

val windowsLightPalette = WindowsColors(
    primary = Color(0xFF0078D4), // blue
    highlight = Color(0xFF429CE3), // light blue
    border = Color(0xFFE5E5E5), // light gray
    borderDark = Color(0xFF919191), // light gray
    surfaceBorder = Color(0xFFC1C1C1), // light gray
    surfaceDark = Color(0xFFEDEDED), // light gray
    primary50 = Color(0xFF797979), // light gray
)


internal val WindowsDisabledBackgroundColor: Color
    @Composable
    get() = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)


// TODO handle dark theme here
internal val WindowsDisabledContentColor: Color
    @Composable
    get() = windowsLightPalette.primary50