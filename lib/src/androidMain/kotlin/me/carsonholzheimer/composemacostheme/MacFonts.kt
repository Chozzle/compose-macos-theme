package me.carsonholzheimer.composemacostheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
actual fun font(alias: String, path: String, weight: FontWeight, style: FontStyle): Font {
    val context = AmbientContext.current
    val id = context.resources.getIdentifier(path, "font", context.packageName)
    return androidx.compose.ui.text.font.font(id, weight, style)
}