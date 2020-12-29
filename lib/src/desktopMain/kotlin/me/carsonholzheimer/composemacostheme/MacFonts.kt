package me.carsonholzheimer.composemacostheme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

actual fun font(alias: String, path: String, weight: FontWeight, style: FontStyle): Font =
    androidx.compose.ui.text.platform.font(alias, path, weight, style)