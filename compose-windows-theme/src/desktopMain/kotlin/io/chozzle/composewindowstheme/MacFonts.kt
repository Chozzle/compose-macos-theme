package io.chozzle.composewindowstheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

private val fontDirectory = "font"

@Composable
actual fun font(path: String, weight: FontWeight, style: FontStyle): Font =
    androidx.compose.ui.text.platform.Font("$fontDirectory/$path.ttf", weight, style)