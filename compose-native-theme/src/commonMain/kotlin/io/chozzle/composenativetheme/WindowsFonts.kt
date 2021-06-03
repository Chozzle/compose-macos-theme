package io.chozzle.composenativetheme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import io.chozzle.composemacostheme.MacFonts
import io.chozzle.composewindowstheme.WindowsFonts

object NativeFonts {
    @Composable
    fun NativeFont() = when (LocalTheme.current) {
        Mac -> MacFonts.SFPro()
        Windows -> WindowsFonts.SegoeUI()
        Material -> FontFamily.Default
    }
}
