package io.chozzle.composenativetheme

import androidx.compose.material.Colors
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import io.chozzle.composemacostheme.MacTheme
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
    colors: Colors = when (LocalTheme.current) {
        Mac -> MacTheme.defaultColors
        Windows -> WindowsTheme.defaultColors
    },
    typography: Typography = when (LocalTheme.current) {
        Mac -> MacTheme.defaultTypography
        Windows -> WindowsTheme.defaultTypography
    },
    shapes: Shapes = when (LocalTheme.current) {
        Mac -> MacTheme.defaultShapes
        Windows -> WindowsTheme.defaultShapes
    },
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalNativeColors provides nativeLightPalette,
    ) {
        when (LocalTheme.current) {
            Mac -> MacTheme(colors, typography, shapes, content)
            Windows -> WindowsTheme(colors, typography, shapes, content)
        }
    }
}