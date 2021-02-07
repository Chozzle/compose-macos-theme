package io.chozzle.composemacostheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

private val fontDirectory = "font"

@Composable
actual fun font(alias: String, path: String, weight: FontWeight, style: FontStyle): Font =
    androidx.compose.ui.text.platform.Font("$fontDirectory/$path.otf", weight, style)