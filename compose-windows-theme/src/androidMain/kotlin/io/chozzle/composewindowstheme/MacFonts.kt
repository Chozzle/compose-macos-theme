package io.chozzle.composewindowstheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
actual fun font(path: String, weight: FontWeight, style: FontStyle): Font {
    val context = LocalContext.current
    val id = context.resources.getIdentifier(path, "font", context.packageName)
    return Font(id, weight, style)
}