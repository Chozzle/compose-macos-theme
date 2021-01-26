package io.chozzle.composewindowstheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.ResourceFont

private val fontDirectory = "font"

@Composable
actual fun font(path: String, weight: FontWeight, style: FontStyle): Font =
    ResourceFont("$fontDirectory/$path.ttf", weight, style)