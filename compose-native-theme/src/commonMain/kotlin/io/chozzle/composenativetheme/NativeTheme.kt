package io.chozzle.composenativetheme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.chozzle.composemacostheme.MacFonts
import io.chozzle.composemacostheme.MacTheme
import io.chozzle.composemacostheme.macLightPalette
import io.chozzle.composewindowstheme.WindowsFonts
import io.chozzle.composewindowstheme.WindowsTheme

sealed class NativeTheme {

    companion object {
        val theme: NativeTheme
            @Composable
            get() = LocalTheme.current

        val colors: NativeColors
            @Composable
            get() = LocalNativeColors.current
    }
}

object Mac : NativeTheme()
object Windows : NativeTheme()

private val LocalNativeColors = staticCompositionLocalOf<NativeColors> {
    error("No NativeColors provided")
}

val LocalTheme = staticCompositionLocalOf<NativeTheme> {
    error("No Theme provided")
}

@Composable
fun NativeTheme(
    colors: Colors?,
    typography: Typography?,
    shapes: Shapes?,
    content: @Composable () -> Unit
) = when (LocalTheme.current) {
    Mac -> MacTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
    Windows -> WindowsTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}