package me.carsonholzheimer.composemacostheme

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
    highlight = Color(0xFF91BBFA), // light blue
    border = Color(0xFFE5E5E5), // light gray
    borderDark = Color(0xFF919191), // light gray
    surfaceBorder = Color(0XFFC1C1C1), // light gray
    surfaceDark = Color(0XFFEDEDED), // light gray
    primary50 = Color(0xFF797979), // light gray
)

